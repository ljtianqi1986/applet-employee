package com.sk.meikelai.entity;

/**
 * Created by sk on 2017/8/11.
 */

public class CardBean {


    /**
     * acqId : 03100000
     * authCode : 969794
     * amount : 000000000001
     * batch : 000001
     * name : 消费
     * pan : 6217920470497533
     * referenceNo : 288128130442
     * time : 130442
     * trace : 000009
     */

    private String traceNo = "";
    private String referenceNo = "";
    private String cardNo = "";
    private String type = "";
    private String issue = "";
    private String batchNo = "";
    private String terminalId = "";
    private String merchantId = "";
    private String merchantName = "";


    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
