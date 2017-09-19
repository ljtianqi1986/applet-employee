package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */

public class UpdateCardBean {

    /**
     * return_code : 1
     * return_info : 检索成功！
     * return_data : [{"code":"22222222","number":"cz005","name_pinyin":"5zk","name":"5折卡","type":2,"type_code":"","gift_price":0,
     * "sale_price":500000,"discount":5,"coefficient":1,"useful":120,"commission":3,"agent_code":"1001","state":0,"recharge_price":0,
     * "isdel":0,"create_time":"2017-07-19 16:37:05.0"}]
     */

    private int return_code;
    private String return_info;
    private List<ReturnDataBean> return_data;

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public String getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public List<ReturnDataBean> getReturn_data() {
        return return_data;
    }

    public void setReturn_data(List<ReturnDataBean> return_data) {
        this.return_data = return_data;
    }

    public static class ReturnDataBean {
        /**
         * code : 22222222
         * number : cz005
         * name_pinyin : 5zk
         * name : 5折卡
         * type : 2
         * type_code :
         * gift_price : 0
         * sale_price : 500000
         * discount : 5
         * coefficient : 1
         * useful : 120
         * commission : 3
         * agent_code : 1001
         * state : 0
         * recharge_price : 0
         * isdel : 0
         * create_time : 2017-07-19 16:37:05.0
         */

        private String code;
        private String number;
        private String name_pinyin;
        private String name;
        private int type;
        private String type_code;
        private int gift_price;
        private int sale_price;
        private int discount;
        private int coefficient;
        private int useful;
        private int commission;
        private String agent_code;
        private int state;
        private int recharge_price;
        private int isdel;
        private String create_time;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName_pinyin() {
            return name_pinyin;
        }

        public void setName_pinyin(String name_pinyin) {
            this.name_pinyin = name_pinyin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType_code() {
            return type_code;
        }

        public void setType_code(String type_code) {
            this.type_code = type_code;
        }

        public int getGift_price() {
            return gift_price;
        }

        public void setGift_price(int gift_price) {
            this.gift_price = gift_price;
        }

        public int getSale_price() {
            return sale_price;
        }

        public void setSale_price(int sale_price) {
            this.sale_price = sale_price;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getUseful() {
            return useful;
        }

        public void setUseful(int useful) {
            this.useful = useful;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
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

        public int getRecharge_price() {
            return recharge_price;
        }

        public void setRecharge_price(int recharge_price) {
            this.recharge_price = recharge_price;
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
}
