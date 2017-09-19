package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by lenovo on 2017/7/13.
 */

public class OrderFlowBean {


    /**
     * return_code : 1
     * return_info : 获取订单记录成功
     * return_data : [{"order_code":"1111","type":1,"trade_type":"CASH","base_user_code":"abc","base_user_name":"我叫金三顺","base_user_phone":"18851610000","user_code":"收银员code","person_name":"收银员名称","create_time":"2017-07-12 12:00:00","pay_time":"2017-07-12 12:00:00","recharge_money":100000,"gift_money":100000,"should_money":100000,"actual_money":99800,"recharge_type":"NEW_CARD","precentage":1000,"card_name":"9折卡","upgrade_card_name":""},{"order_code":"2222","type":0,"trade_type":"CASH","base_user_code":"abc","base_user_name":"我叫金三顺","base_user_phone":"18851610000","user_code":"收银员code","person_name":"收银员名称","create_time":"2017-07-12 12:00:00","pay_time":"2017-07-12 12:00:00","order_total":100000,"should_total":100000,"actual_total":100000,"discount_total":100000,"card_total":50000,"cash_total":0,"detail_arr":[{"code":"111","order_code":"2222","service_type":0,"commission":10000,"pay_type":0,"person_name":"技师的名称","project_code":"111","project_name":"美甲","produce_code":"","produce_name":"","is_discount":1},{"code":"222","order_code":"2222","service_type":1,"commission":10000,"pay_type":0,"person_name":"技师的名称","project_code":"","project_name":"","produce_code":"333","produce_name":"洗发水100ML","is_discount":0}]}]
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
         * order_code : 1111
         * type : 1
         * trade_type : CASH
         * base_user_code : abc
         * base_user_name : 我叫金三顺
         * base_user_phone : 18851610000
         * user_code : 收银员code
         * person_name : 收银员名称
         * create_time : 2017-07-12 12:00:00
         * pay_time : 2017-07-12 12:00:00
         * recharge_money : 100000
         * gift_money : 100000
         * should_money : 100000
         * actual_money : 99800
         * recharge_type : NEW_CARD
         * precentage : 1000
         * card_name : 9折卡
         * upgrade_card_name :
         * order_total : 100000
         * should_total : 100000
         * actual_total : 100000
         * discount_total : 100000
         * card_total : 50000
         * cash_total : 0
         * detail_arr : [{"code":"111","order_code":"2222","service_type":0,"commission":10000,"pay_type":0,"person_name":"技师的名称","project_code":"111","project_name":"美甲","produce_code":"","produce_name":"","is_discount":1},{"code":"222","order_code":"2222","service_type":1,"commission":10000,"pay_type":0,"person_name":"技师的名称","project_code":"","project_name":"","produce_code":"333","produce_name":"洗发水100ML","is_discount":0}]
         */

        private String order_code;
        private int type;
        private String trade_type;
        private String base_user_code;
        private String base_user_name;
        private String base_user_phone;
        private String user_code;
        private String person_name;
        private String create_time;
        private String pay_time;
        private int recharge_money;
        private int gift_money;
        private int should_money;
        private int actual_money;
        private String recharge_type;
        private int precentage;
        private String card_name;
        private String upgrade_card_name;
        private int order_total;
        private int should_total;
        private int actual_total;
        private int discount_total;
        private int card_total;
        private int cash_total;
        private int commission;
        private String cardSysPersonName;

        public String getCardSysPersonName() {
            return cardSysPersonName;
        }

        public void setCardSysPersonName(String cardSysPersonName) {
            this.cardSysPersonName = cardSysPersonName;
        }

        private List<DetailArrBean> detail_arr;

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getBase_user_code() {
            return base_user_code;
        }

        public void setBase_user_code(String base_user_code) {
            this.base_user_code = base_user_code;
        }

        public String getBase_user_name() {
            return base_user_name;
        }

        public void setBase_user_name(String base_user_name) {
            this.base_user_name = base_user_name;
        }

        public String getBase_user_phone() {
            return base_user_phone;
        }

        public void setBase_user_phone(String base_user_phone) {
            this.base_user_phone = base_user_phone;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public int getRecharge_money() {
            return recharge_money;
        }

        public void setRecharge_money(int recharge_money) {
            this.recharge_money = recharge_money;
        }

        public int getGift_money() {
            return gift_money;
        }

        public void setGift_money(int gift_money) {
            this.gift_money = gift_money;
        }

        public int getShould_money() {
            return should_money;
        }

        public void setShould_money(int should_money) {
            this.should_money = should_money;
        }

        public int getActual_money() {
            return actual_money;
        }

        public void setActual_money(int actual_money) {
            this.actual_money = actual_money;
        }

        public String getRecharge_type() {
            return recharge_type;
        }

        public void setRecharge_type(String recharge_type) {
            this.recharge_type = recharge_type;
        }

        public int getPrecentage() {
            return precentage;
        }

        public void setPrecentage(int precentage) {
            this.precentage = precentage;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getUpgrade_card_name() {
            return upgrade_card_name;
        }

        public void setUpgrade_card_name(String upgrade_card_name) {
            this.upgrade_card_name = upgrade_card_name;
        }

        public int getOrder_total() {
            return order_total;
        }

        public void setOrder_total(int order_total) {
            this.order_total = order_total;
        }

        public int getShould_total() {
            return should_total;
        }

        public void setShould_total(int should_total) {
            this.should_total = should_total;
        }

        public int getActual_total() {
            return actual_total;
        }

        public void setActual_total(int actual_total) {
            this.actual_total = actual_total;
        }

        public int getDiscount_total() {
            return discount_total;
        }

        public void setDiscount_total(int discount_total) {
            this.discount_total = discount_total;
        }

        public int getCard_total() {
            return card_total;
        }

        public void setCard_total(int card_total) {
            this.card_total = card_total;
        }

        public int getCash_total() {
            return cash_total;
        }

        public void setCash_total(int cash_total) {
            this.cash_total = cash_total;
        }

        public List<DetailArrBean> getDetail_arr() {
            return detail_arr;
        }

        public void setDetail_arr(List<DetailArrBean> detail_arr) {
            this.detail_arr = detail_arr;
        }

        public static class DetailArrBean {
            /**
             * code : 111
             * order_code : 2222
             * service_type : 0
             * commission : 10000
             * pay_type : 0
             * person_name : 技师的名称
             * project_code : 111
             * project_name : 美甲
             * produce_code :
             * produce_name :
             * is_discount : 1
             */

            private String code;
            private String order_code;
            private int service_type;
            private int commission;
            private int pay_type;
            private String person_name;
            private String project_code;
            private String project_name;
            private String produce_code;
            private String produce_name;
            private int is_discount;
            private int actual_price;
            private String person_code;

            public String getPerson_code() {
                return person_code;
            }

            public void setPerson_code(String person_code) {
                this.person_code = person_code;
            }

            public int getActual_price() {
                return actual_price;
            }

            public void setActual_price(int actual_price) {
                this.actual_price = actual_price;
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

            public int getService_type() {
                return service_type;
            }

            public void setService_type(int service_type) {
                this.service_type = service_type;
            }

            public int getCommission() {
                return commission;
            }

            public void setCommission(int commission) {
                this.commission = commission;
            }

            public int getPay_type() {
                return pay_type;
            }

            public void setPay_type(int pay_type) {
                this.pay_type = pay_type;
            }

            public String getPerson_name() {
                return person_name;
            }

            public void setPerson_name(String person_name) {
                this.person_name = person_name;
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

            public int getIs_discount() {
                return is_discount;
            }

            public void setIs_discount(int is_discount) {
                this.is_discount = is_discount;
            }
        }
    }
}
