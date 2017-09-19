package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by sk on 2017/7/18.
 */

public class SaveCardBean{

    /**
     * return_code : 1
     * return_info : 获取数据成功！
     * return_data : [{"code":"7","card_code":"22222222","card_name":"5折卡","user_code":"3dbc8321a56e48ffa3c3434a5c33925a","discount":5,"balance":100000,"coefficient":1},{"code":"6","card_code":"33333333","card_name":"充1000送1000","user_code":"3dbc8321a56e48ffa3c3434a5c33925a","discount":10,"balance":100000,"coefficient":0.5}]
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

    public static class ReturnDataBean implements Comparable{
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
        private Integer discount;
        private int balance;
        private double coefficient;

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

        @Override
        public int compareTo(Object o) {
            ReturnDataBean bean = (ReturnDataBean)o;

            int  discount = bean.getDiscount();
            // note: enum-type's comparation depend on types' list order of enum method
            // so, if compared property is enum-type ,then its comparationfollow ObjEnum.objType order
            return this.discount.compareTo(discount);
        }
    }
}
