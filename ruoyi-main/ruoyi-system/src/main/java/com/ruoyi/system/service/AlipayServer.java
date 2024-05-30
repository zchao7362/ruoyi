package com.ruoyi.system.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.OrgOrderInfo;
import com.ruoyi.system.domain.OrgTradeComplain;
import com.ruoyi.system.domain.SysAlipayConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface AlipayServer {

    AjaxResult aliPayment(OrgOrderInfo orderInfo);

    String aliPaymentReString(OrgOrderInfo orderInfo);

    public String aliPayCallback(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException;

    public String queryOrder(OrgOrderInfo orderInfo, SysAlipayConfig alipayCnf) throws AlipayApiException;
    String refund(OrgOrderInfo orderInfo);
    String refundQuery(OrgOrderInfo orderInfo);

    /**
     * 定时同步查询交易投诉
     */
    String synctradecomplain(OrgTradeComplain orgTradeComplain) throws Exception;

    String aliPayTradecomplainChanged(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException;


    String alipayTradeRefund(OrgOrderInfo orderInfo) throws  AlipayApiException;


    String alipayMerchantTradecomplainFeedbackSubmit(OrgTradeComplain orgTradeComplain,AlipayClient alipayClient) throws AlipayApiException;

    String loginCallBack(String code,String appid) throws  AlipayApiException;


    //查询投诉列表
    void alipayMerchantTradecomplainBatchquery() throws AlipayApiException;
}
