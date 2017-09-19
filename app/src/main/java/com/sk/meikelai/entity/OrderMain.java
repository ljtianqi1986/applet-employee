package com.sk.meikelai.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */
public class OrderMain {
    private String code="";
    /**
     *  总金额 （项目价格计算总金额）
     */
    private double order_total=0;
    /**
     *  应付金额 （除去次年卡金额）
     */
    private double should_total=0;
    /**
     *  实际需要支付金额
     */
    private double actual_total=0;
    /**
     *  储值卡能抵扣的金额
     */
    private double discount_total=0;
    /**
     *  储值卡实际扣款金额
     */
    private double card_total=0;
    /**
     *  实付金额 （现金）
     */
    private double cash_total =0;
    /**
     * 抹零金额
     */
    private double odd_total=0;
    /**
     *  客户
     */
    private String base_user_code="";
    /**
     * 收银员
     */
    private String user_code="";
    /**
     *  门店
     */
    private String shop_code = "";
    /**
     *  商户
     */
    private String brand_code="";
    /**
     *  代理商
     */
    private String agent_code="";
    /**
     *  交易状态 0:未生效1:交易成功2:错误3:已退款
     */
    private int state=0;
    /**
     *  支付时间
     */
    private String pay_time="";
    /**
     * 支付信息
     */
    private String error_pay_msg="";
    /**
     *  备注
     */
    private String remark="";
    /**
     *  支付方式 （RECHARGE 储值卡, CASH 现金,ONLINE 线上支付,CARD 刷卡）
     */
    private String trade_type="";
    /**
     * 提成金额
     */
    private int precentage=0;
    private int isdel = 0;
    private String create_time ="";

    //card_count
    private int card_count;

    public int getCard_count() {
        return card_count;
    }

    public void setCard_count(int card_count) {
        this.card_count = card_count;
    }
//**********************前端页面展示使用----------------begin

    /**
     * 门店名称
     */
    private String business_name="";
    /**
     * 分店名称
     */
    private String branch_name="";

    //**********************前端页面展示使用----------------end

    private List<OrderDetail> detailList=new ArrayList<OrderDetail>();

    public int getPrecentage() {
        return precentage;
    }

    public void setPrecentage(int precentage) {
        this.precentage = precentage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public double getShould_total() {
        return should_total;
    }

    public void setShould_total(double should_total) {
        this.should_total = should_total;
    }

    public double getActual_total() {
        return actual_total;
    }

    public void setActual_total(double actual_total) {
        this.actual_total = actual_total;
    }

    public double getDiscount_total() {
        return discount_total;
    }

    public void setDiscount_total(double discount_total) {
        this.discount_total = discount_total;
    }

    public double getCard_total() {
        return card_total;
    }

    public void setCard_total(double card_total) {
        this.card_total = card_total;
    }

    public double getCash_total() {
        return cash_total;
    }

    public void setCash_total(double cash_total) {
        this.cash_total = cash_total;
    }

    public double getOdd_total() {
        return odd_total;
    }

    public void setOdd_total(double odd_total) {
        this.odd_total = odd_total;
    }

    public String getBase_user_code() {
        return base_user_code;
    }

    public void setBase_user_code(String base_user_code) {
        this.base_user_code = base_user_code;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getBrand_code() {
        return brand_code;
    }

    public void setBrand_code(String brand_code) {
        this.brand_code = brand_code;
    }

    public String getAgent_code() {
        return agent_code;
    }

    public void setAgent_code(String agent_code) {
        this.agent_code = agent_code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getError_pay_msg() {
        return error_pay_msg;
    }

    public void setError_pay_msg(String error_pay_msg) {
        this.error_pay_msg = error_pay_msg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public int getIsdel() {
        return isdel;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }


    public List<OrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OrderDetail> detailList) {
        this.detailList = detailList;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
}
