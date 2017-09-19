package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class CreateCardBean {

    /**
     * jsonString : {"card_code":"abccc","user_code":"jsfdkfkdf","base_user_code":"abccc","trade_type":"ONLINE","should_money":"100000",
     * "actual_money":"99800","type":"NEW_CARD","device_ip":"127.0.0.1","device_info":"华为神机","remark":"办张卡玩玩",
     * "author_code":"139908876727277","upgrade_code":"","commission_list":[{"user_code":"111","money":"90000"},{"user_code":"222",
     * "money":"8800"}]}
     */

    private JsonStringBean jsonString;

    public JsonStringBean getJsonString() {
        return jsonString;
    }

    public void setJsonString(JsonStringBean jsonString) {
        this.jsonString = jsonString;
    }

    public static class JsonStringBean {
        /**
         * card_code : abccc
         * user_code : jsfdkfkdf
         * base_user_code : abccc
         * trade_type : ONLINE
         * should_money : 100000
         * actual_money : 99800
         * type : NEW_CARD
         * device_ip : 127.0.0.1
         * device_info : 华为神机
         * remark : 办张卡玩玩
         * author_code : 139908876727277
         * upgrade_code :
         * commission_list : [{"user_code":"111","money":"90000"},{"user_code":"222","money":"8800"}]
         */

        private String card_code;
        private String user_code;
        private String base_user_code;
        private String trade_type;
        private String should_money;
        private String actual_money;
        private String type;
        private String device_ip;
        private String device_info;
        private String useful_date;
        private String remark;
        private String key;
        private String author_code;
        private String upgrade_code;
        private List<CommissionListBean> commission_list;
        private CardBean union_pay;

        public CardBean getUnion_pay() {
            return union_pay;
        }

        public void setUnion_pay(CardBean union_pay) {
            this.union_pay = union_pay;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
        public String getCard_code() {
            return card_code;
        }

        public void setCard_code(String card_code) {
            this.card_code = card_code;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getBase_user_code() {
            return base_user_code;
        }

        public void setBase_user_code(String base_user_code) {
            this.base_user_code = base_user_code;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getShould_money() {
            return should_money;
        }

        public void setShould_money(String should_money) {
            this.should_money = should_money;
        }

        public String getActual_money() {
            return actual_money;
        }

        public void setActual_money(String actual_money) {
            this.actual_money = actual_money;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUseful_date() {
            return useful_date;
        }

        public void setUseful_date(String useful_date) {
            this.useful_date = useful_date;
        }

        public String getDevice_ip() {
            return device_ip;
        }

        public void setDevice_ip(String device_ip) {
            this.device_ip = device_ip;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAuthor_code() {
            return author_code;
        }

        public void setAuthor_code(String author_code) {
            this.author_code = author_code;
        }

        public String getUpgrade_code() {
            return upgrade_code;
        }

        public void setUpgrade_code(String upgrade_code) {
            this.upgrade_code = upgrade_code;
        }

        public List<CommissionListBean> getCommission_list() {
            return commission_list;
        }

        public void setCommission_list(List<CommissionListBean> commission_list) {
            this.commission_list = commission_list;
        }




        public static class CommissionListBean {


            /**
             * is_main : 90000
             * user_code : 111
             * money : 90000
             */
            private String is_main;
            private String user_code;
            private String money;

            public String getIs_main() {
                return is_main;
            }

            public void setIs_main(String is_main) {
                this.is_main = is_main;
            }
            public String getUser_code() {
                return user_code;
            }

            public void setUser_code(String user_code) {
                this.user_code = user_code;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
