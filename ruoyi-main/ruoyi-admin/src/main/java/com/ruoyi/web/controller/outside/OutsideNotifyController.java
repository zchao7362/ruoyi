package com.ruoyi.web.controller.outside;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.OrgAccount;
import com.ruoyi.system.domain.OrgChannelMerchant;
import com.ruoyi.system.domain.OrgOrderInfo;
import com.ruoyi.system.service.*;
import com.ruoyi.web.controller.outside.domain.CallBackModel;
import com.ruoyi.web.controller.outside.domain.OutsideOrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


@Controller
@RequestMapping("/outside/notify")
public class OutsideNotifyController extends BaseController {

    private String prefix = "system/order";

    @Autowired
    private IOrgMerchantService orgMerchantService;

    @Autowired
    private PayZftServer payZftServer;

    @Autowired
    private  IOrgOrderInfoService orgOrderInfoService;
    @Autowired
    private IOrgPayChannelService orgPayChannelService;

    @Autowired
    private IOrgChannelMerchantService orgChannelMerchantService;

    private static final Logger logger = LoggerFactory.getLogger(OutsideNotifyController.class);

    @PostMapping("/callback")
    @ResponseBody
    public String createOrder(@RequestBody CallBackModel callBackModel){

        OrgChannelMerchant ocm = orgChannelMerchantService.selectOrgChannelMerchantByMerchantNo(callBackModel.getMerchantBaseId());
        String  afterSign = ocm.getMerchantNo()+callBackModel.getOutTradeNo()+ocm.getMerchanKey();
        String sign = Md5Utils.hash(afterSign);
        if(sign.equals(callBackModel.getSign())){
            OrgOrderInfo order = orgOrderInfoService.selectorderByOrderId(callBackModel.getOutTradeNo());
            if("TRADE_SUCCESS".equals(callBackModel.getTradeState())){

                String res = payZftServer.queryOrder(ocm.getMerchantNo(),callBackModel.getOutTradeNo(),sign);
                if("success".equals(res)){
                    order.setOrderStatus(1L);
                    order.setCompletionTime(new Date());
                    orgOrderInfoService.updateOrgOrderInfo(order);
                    asyncThread(order);//异步回调
                    return "success";
                }else{
                    logger.info("--------------------------------不正常请求！-------------------------------");
                    return "fail";
                }
            }else{
                logger.info("--------------------------------未支付成功！-------------------------------");
                return "fail";
            }
        }else{
            return "fail";
        }
    }

    @Async
    public void asyncThread(OrgOrderInfo orderInfo){
        logger.info("异步回调");
        orgOrderInfoService.callbackOrder(orderInfo);
    }

    @GetMapping("/returnUrl")
    @ResponseBody
    public String payOrder(@RequestBody CallBackModel callBackModel){



        return "调用失败";
    }

}
