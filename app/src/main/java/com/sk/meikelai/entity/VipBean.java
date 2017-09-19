package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by lenovo on 2017/7/12.
 */

public class VipBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"code":"21321","create_time":"2017-06-09 08:59:36","phone":"130***5686","recharge_count":0,"count_count":22,"consume_count":0,"person_name":"DASDAS","invite_name":"美客来东山镇店","year_count":0,"sum_actual_total":58000,"min_card_create_time":"2017-07-19 16:50:21","come_time":"2017-07-20","order_counts":1,"count_arr":[{"card_user_code":"7172b53d9d1c44acba6dc463a33f7712","name":"修眉卡","endDate":"1970-01-01","surplus_times":"0"}],"recharge_arr":[{"card_user_code":"7","name":"5折卡","endDate":"1970-01-01","balance":90000},{"card_user_code":"6","name":"充1000送1000","endDate":"1970-01-01","balance":86000}],"year_arr":[{"card_user_code":"c60bfd054eb64faf88b1e05b9be540d0","name":"面部基础护理季卡","endDate":"1970-01-01"},{"card_user_code":"810d5ddc2f404af99831c1801bf4a976","name":"年卡水晶会员","endDate":"1970-01-01"}]}]
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
         * code : 21321
         * create_time : 2017-06-09 08:59:36
         * phone : 130***5686
         * recharge_count : 0
         * count_count : 22
         * consume_count : 0
         * person_name : DASDAS
         * invite_name : 美客来东山镇店
         * year_count : 0
         * sum_actual_total : 58000
         * min_card_create_time : 2017-07-19 16:50:21
         * come_time : 2017-07-20
         * order_counts : 1
         * count_arr : [{"card_user_code":"7172b53d9d1c44acba6dc463a33f7712","name":"修眉卡","endDate":"1970-01-01","surplus_times":"0"}]
         * recharge_arr : [{"card_user_code":"7","name":"5折卡","endDate":"1970-01-01","balance":90000},{"card_user_code":"6","name":"充1000送1000","endDate":"1970-01-01","balance":86000}]
         * year_arr : [{"card_user_code":"c60bfd054eb64faf88b1e05b9be540d0","name":"面部基础护理季卡","endDate":"1970-01-01"},{"card_user_code":"810d5ddc2f404af99831c1801bf4a976","name":"年卡水晶会员","endDate":"1970-01-01"}]
         */

        private String code;
        private String create_time;
        private String phone;
        private int recharge_count;
        private int count_count;
        private int consume_count;
        private String person_name;
        private String invite_name;
        private int year_count;
        private int sum_actual_total;
        private String min_card_create_time;
        private String come_time;
        private int order_counts;
        private List<CountArrBean> count_arr;
        private List<RechargeArrBean> recharge_arr;
        private List<YearArrBean> year_arr;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRecharge_count() {
            return recharge_count;
        }

        public void setRecharge_count(int recharge_count) {
            this.recharge_count = recharge_count;
        }

        public int getCount_count() {
            return count_count;
        }

        public void setCount_count(int count_count) {
            this.count_count = count_count;
        }

        public int getConsume_count() {
            return consume_count;
        }

        public void setConsume_count(int consume_count) {
            this.consume_count = consume_count;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getInvite_name() {
            return invite_name;
        }

        public void setInvite_name(String invite_name) {
            this.invite_name = invite_name;
        }

        public int getYear_count() {
            return year_count;
        }

        public void setYear_count(int year_count) {
            this.year_count = year_count;
        }

        public int getSum_actual_total() {
            return sum_actual_total;
        }

        public void setSum_actual_total(int sum_actual_total) {
            this.sum_actual_total = sum_actual_total;
        }

        public String getMin_card_create_time() {
            return min_card_create_time;
        }

        public void setMin_card_create_time(String min_card_create_time) {
            this.min_card_create_time = min_card_create_time;
        }

        public String getCome_time() {
            return come_time;
        }

        public void setCome_time(String come_time) {
            this.come_time = come_time;
        }

        public int getOrder_counts() {
            return order_counts;
        }

        public void setOrder_counts(int order_counts) {
            this.order_counts = order_counts;
        }

        public List<CountArrBean> getCount_arr() {
            return count_arr;
        }

        public void setCount_arr(List<CountArrBean> count_arr) {
            this.count_arr = count_arr;
        }

        public List<RechargeArrBean> getRecharge_arr() {
            return recharge_arr;
        }

        public void setRecharge_arr(List<RechargeArrBean> recharge_arr) {
            this.recharge_arr = recharge_arr;
        }

        public List<YearArrBean> getYear_arr() {
            return year_arr;
        }

        public void setYear_arr(List<YearArrBean> year_arr) {
            this.year_arr = year_arr;
        }

        public static class CountArrBean {
            /**
             * card_user_code : 7172b53d9d1c44acba6dc463a33f7712
             * name : 修眉卡
             * endDate : 1970-01-01
             * surplus_times : 0
             */

            private String card_user_code;
            private String name;
            private String endDate;
            private String surplus_times;

            public String getCard_user_code() {
                return card_user_code;
            }

            public void setCard_user_code(String card_user_code) {
                this.card_user_code = card_user_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getSurplus_times() {
                return surplus_times;
            }

            public void setSurplus_times(String surplus_times) {
                this.surplus_times = surplus_times;
            }
        }

        public static class RechargeArrBean {
            /**
             * card_user_code : 7
             * name : 5折卡
             * endDate : 1970-01-01
             * balance : 90000
             */

            private String card_user_code;
            private String name;
            private String endDate;
            private int balance;

            public String getCard_user_code() {
                return card_user_code;
            }

            public void setCard_user_code(String card_user_code) {
                this.card_user_code = card_user_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }
        }

        public static class YearArrBean {
            /**
             * card_user_code : c60bfd054eb64faf88b1e05b9be540d0
             * name : 面部基础护理季卡
             * endDate : 1970-01-01
             */

            private String card_user_code;
            private String name;
            private String endDate;

            public String getCard_user_code() {
                return card_user_code;
            }

            public void setCard_user_code(String card_user_code) {
                this.card_user_code = card_user_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }
        }
    }
}
