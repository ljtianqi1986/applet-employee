package com.sk.meikelai.entity;

/**
 * 版本 version:
 *
 * 说明描述 description:  消费订单子表  实体类
 *
 * 创建信息 : create by 曹德意 on 2017/6/14 10:29
 *
 * 版权信息 : Copyright (c) 2015 江苏网之畅科技有限公司
 *
 * 修订信息 : modify by ( ) on (date) for ( )
 */
public class OrderDetail {
    private String code="";
    /**
     * 主表订单号
     */
    private String order_code="";
    /**
     * 项目code
     */
    private String project_code="";
    /**
     * 项目名称
     */
    private String project_name="";
    /**
     * 产品code
     */
    private String produce_code="";
    /**
     * 产品名称
     */
    private String produce_name="";
    /**
     *  用户的卡code （年次卡项目此字段有值）
     */
    private String card_user_code="";
    /**
     * 用户的卡code 对应的项目 （年次卡项目此字段有值）
     */
    private String card_user_project_code="";
    /**
     * 价格
     */
    private double price=0;
    /**
     * 是否打折 0否 1是
     */
    private int is_discount=0;
    /**
     *  实际价格
     */
    private double actual_price=0;
    /**
     *  储值卡能抵扣的金额
     */
    private double discount_total=0;
    /**
     * 储值卡实际金额
     */
    private double card_total=0;
    /**
     *  参与提成计算的储值卡金额
     */
    private double percentage_total=0;
    /**
     *  实付现金
     */
    private double cash_total=0;
    /**
     * 0项目 1产品
     */
    private int service_type=0;
    /**
     * 提成金额
     */
    private double commission=0;
    /**
     * 技师code
     */
    private String user_code="";
    /**
     * 0:未生效1:交易成功2:错误3:已退款
     */
    private int state=0;
    /**
     * 0消费 1次卡 2年卡
     */
    private int pay_type=0;
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


    private int isdel = 0;
    private String create_time ="";

    //**********************前端页面展示使用----------------begin

    private String person_name="";

    //**********************前端页面展示使用----------------end

    public String getCode() {
        return code;
    }

    public double getPercentage_total() {
        return percentage_total;
    }

    public void setPercentage_total(double percentage_total) {
        this.percentage_total = percentage_total;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProduce_code() {
        return produce_code;
    }

    public void setProduce_code(String produce_code) {
        this.produce_code = produce_code;
    }

    public String getProduce_name() {
        return produce_name;
    }

    public void setProduce_name(String produce_name) {
        this.produce_name = produce_name;
    }

    public String getCard_user_code() {
        return card_user_code;
    }

    public void setCard_user_code(String card_user_code) {
        this.card_user_code = card_user_code;
    }

    public String getCard_user_project_code() {
        return card_user_project_code;
    }

    public void setCard_user_project_code(String card_user_project_code) {
        this.card_user_project_code = card_user_project_code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIs_discount() {
        return is_discount;
    }

    public void setIs_discount(int is_discount) {
        this.is_discount = is_discount;
    }

    public double getActual_price() {
        return actual_price;
    }

    public void setActual_price(double actual_price) {
        this.actual_price = actual_price;
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

    public int getService_type() {
        return service_type;
    }

    public void setService_type(int service_type) {
        this.service_type = service_type;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
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

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
}
