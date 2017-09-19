package com.sk.meikelai.entity;

/**
 * Created by sk on 2017/7/19.
 */

public class PayDetail{

    String pay_type;
    String price;
    String actual_price;
    String service_type;
    String project_code;
    String project_name;
    String user_code;
    String is_discount;
    String produce_code;
    String produce_name;
    String card_user_code;
    String card_user_project_code;

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

    public String getIs_discount() {
        return is_discount;
    }

    public void setIs_discount(String is_discount) {
        this.is_discount = is_discount;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getActual_price() {
        return actual_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
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
}
