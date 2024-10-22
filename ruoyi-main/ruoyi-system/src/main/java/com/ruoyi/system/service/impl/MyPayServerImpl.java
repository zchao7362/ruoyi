package com.ruoyi.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.OrgChannelMerchant;
import com.ruoyi.system.domain.OrgOrderInfo;
import com.ruoyi.system.domain.OrgPayChannel;
import com.ruoyi.system.service.*;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyPayServerImpl implements IMyPayServer {

    @Autowired
    private IOrgChannelMerchantService orgChannelMerchantService;

    @Autowired
    private IOrgPayChannelService orgPayChannelService;

    @Autowired
    private IOrgMerchantService orgMerchantService;

    @Autowired
    private IOrgOrderInfoService orgOrderInfoService;

    private static final Logger logger = LoggerFactory.getLogger(MyPayServerImpl.class);

    //
    public AjaxResult tradeOrder(OrgOrderInfo orderInfo) {
        //1、查询商户通道是否可用
        List<OrgChannelMerchant> ocmlist = orgChannelMerchantService.selectOrgChannelMerchantList(null);
        if(ocmlist == null || ocmlist.isEmpty()){
            logger.info("2000：商户通道不可用！请联系管理员！");
            return AjaxResult.error();
        }
        OrgChannelMerchant ocm = ocmlist.get(0);
        OrgPayChannel orgPayChannel = orgPayChannelService.selectOrgPayChannelById(ocm.getChannelId());

        String url = orgPayChannel.getCreaterOrderUrl();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid",ocm.getMerchantNo());  //商户ID
        map.put("amount", orderInfo.getYjamount().toString());  //支付金额
        map.put("payType", "QUICK_WAP_WAY");//唯一随机字符串   支付方式 FAST_INSTANT_TRADE_PAY电脑支付 ,QUICK_WAP_WAY手机支付,可用值:JSAPI_PAY,QUICK_WAP_WAY,FAST_INSTANT_TRADE_PAY,WX_MWEB,WX_NATIVE,WX_APP
        map.put("method","10");//
        map.put("merchantOrderNo",orderInfo.getOrderNo());//平台订单号
        map.put("callbackUrl",orgPayChannel.getCallbackUrl());//回调地址
        map.put("returnUrl","");//支付完成跳转地址
        map.put("subject","游戏充值");//订单描述

        String  afterSign = ocm.getMerchantNo()+orderInfo.getOrderNo()+orgPayChannel.getCallbackUrl()+orderInfo.getYjamount().toString()+System.currentTimeMillis()+ocm.getPrivateKey();
        logger.info("afterSign   : ");
        String sign = Md5Utils.hash(afterSign);
        logger.info("sign        : ");

        map.put("sign",sign);//签名(不参与签名)
        JSONObject respJson = new JSONObject(map);
        String strjson = respJson.toString();
        logger.info("输入参数 ——"+strjson);
        String callbackJson = HttpUtil.createPost(url).body(strjson).execute().body();
        logger.info("输出参数 ——"+callbackJson);
        JSONObject json = JSONObject.parseObject(callbackJson);
        if("200".equals(json.getString("code")) || "0".equals(json.getString("code"))){
            JSONObject jsonData = json.getJSONObject("data");
           // String merchantOrderNo = jsonData.getString("merchantOrderNo");
            String orderPayLink = jsonData.getString("orderPayLink");
            //String orderNo = jsonData.getString("orderNo");
            orderInfo.setPayUrl(orderPayLink);
            orgOrderInfoService.insertOrgOrderInfo(orderInfo);
            Map<String,String > resMap = new HashMap();
            resMap.put("orderPayLink",orderPayLink);
            resMap.put("orderNo",orderInfo.getOrderNo());
            resMap.put("merchantOrderNo",orderInfo.getAccountOrderNo());
            return new AjaxResult(AjaxResult.Type.SUCCESS,null, JSONObject.toJSON(resMap));
        }else{
            logger.info("3000：调用失败！请联系管理员！");
            return AjaxResult.error();
        }
    }

    @Override
    public String queryOrder(String merchantId,String merchantTradeNo,String sign) {
        String url = "http://api.game0571.com/back-jar/open/trade/info";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("merchantBaseId",merchantId);  //商户ID
        map.put("merchantTradeNo",merchantTradeNo);//平台订单号
        map.put("sign",sign);//签名(不参与签名)
        JSONObject respJson = new JSONObject(map);
        String strjson = respJson.toString();
        logger.info("queryOrder 输入参数 ——"+strjson);
        String callbackJson = HttpUtil.createPost(url).body(strjson).execute().body();
        logger.info("queryOrder 输出参数 ——"+callbackJson);
        JSONObject json = JSONObject.parseObject(callbackJson);
        if("200".equals(json.getString("code")) || "0".equals(json.getString("code"))){
            JSONObject jsonData = JSONObject.parseObject(json.getString("data"));
            String tradeState = jsonData.getString("tradeState");
            if("TRADE_SUCCESS".equals(tradeState)){
                return "success";
            }
        }else{
            return "fail";
        }

        return "fail";
    }

    @Override
    public AjaxResult refundOrder(OrgOrderInfo orderInfo) {
        return null;
    }

    @Override
    public AjaxResult notifyOrder() {
        return null;
    }

}
