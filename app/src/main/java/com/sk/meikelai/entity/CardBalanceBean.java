package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class CardBalanceBean {

    /**
     * return_code : 1
     * return_info : 获取数据成功！
     * return_data : [{"code":"7","card_code":"22222222","card_name":"5折卡","user_code":"3dbc8321a56e48ffa3c3434a5c33925a","discount":5,
     * "balance":100000,"coefficient":1},{"code":"6","card_code":"33333333","card_name":"充1000送1000",
     * "user_code":"3dbc8321a56e48ffa3c3434a5c33925a","discount":10,"balance":100000,"coefficient":0.5}]
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
         * code : 7
         * card_code : 22222222
         * card_name : 5折卡
         * user_code : 3dbc8321a56e48ffa3c3434a5c33925a
         * discount : 5
         * balance : 100000
         * coefficient : 1.0
         */

        private String code;
        private String card_code;
        private String card_name;
        private String user_code;
        private int discount;
        private int balance;
        private int sale_price;
        private double coefficient;
        private boolean isSelected = false;

        public int getSale_price() {
            return sale_price;
        }

        public void setSale_price(int sale_price) {
            this.sale_price = sale_price;
        }
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCard_code() {
            return card_code;
        }

        public void setCard_code(String card_code) {
            this.card_code = card_code;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public double getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(double coefficient) {
            this.coefficient = coefficient;
        }
    }
}
