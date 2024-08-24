package com.ruoyi.web.controller.outside.domain;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2023年02月09日 15:03
 */
public class CallBackModel {

        private String buyerId;

        private String createTime;

        private String merchantBaseId;

        private String outTradeNo;

        private String payTime;

        private String sign;

        private String totalAmount;

        private String tradeState;

        private String transactionId;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMerchantBaseId() {
        return merchantBaseId;
    }

    public void setMerchantBaseId(String merchantBaseId) {
        this.merchantBaseId = merchantBaseId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
