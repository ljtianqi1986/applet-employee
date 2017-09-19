package com.sk.meikelai.entity;

/*************************************************************************
 * 版本：         V1.0
 *
 * 描述说明 ： 订单支付对应储值卡 实体类
 *
 * 创建信息 : create by 曹德意 on 2017-06-12 上午10:44:14  修订信息 : modify by ( ) on (date) for ( )
 *
 * 版权信息 : Copyright (c) 2015 江苏网之畅科技有限公司
 **************************************************************************/
public class OrderCard {
    private String code="";

    private String order_code="";

    private String card_user_code="";

    private String base_user_code="";

    private double card_total=0;

    private double actual_total=0;

    private int is_discount=0;

    private int isdel = 0;
    private String create_time ="";

    /**********************冗余字段****************/
    /**
     * 0-1 (储值卡专用)
     */
    private double discount=0;
    /**
     * 消费提成系数（0-1）
     */
    private double coefficient = 0;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getCode() {
        return code;
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

    public String getCard_user_code() {
        return card_user_code;
    }

    public void setCard_user_code(String card_user_code) {
        this.card_user_code = card_user_code;
    }

    public String getBase_user_code() {
        return base_user_code;
    }

    public void setBase_user_code(String base_user_code) {
        this.base_user_code = base_user_code;
    }

    public double getCard_total() {
        return card_total;
    }

    public void setCard_total(double card_total) {
        this.card_total = card_total;
    }

    public double getActual_total() {
        return actual_total;
    }

    public void setActual_total(double actual_total) {
        this.actual_total = actual_total;
    }

    public int getIs_discount() {
        return is_discount;
    }

    public void setIs_discount(int is_discount) {
        this.is_discount = is_discount;
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
}
