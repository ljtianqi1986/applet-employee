package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class MemberInfoBean {
    /**
     * return_code : 1
     * return_info : 获取信息成功
     * return_data : {"code":"用户code","person_name":"姓名：赵四","cover":"头像","nick_name":"昵称","phone":"189*****9900","recharge_count":1,
     * "count_count":2,"year_count":"3","count_arr":[{"card_user_code":"111","card_name":"次卡","endDate":"2088-10-02"}],
     * "year_arr":[{"card_user_code":"111","card_name":"年卡","endDate":"2088-10-02"}],"recharge_arr":[{"card_user_code":"111",
     * "card_name":"8折卡","balance":8000,"endDate":"2088-10-02"}]}
     */

    private int return_code;
    private String return_info;
    private ReturnDataBean return_data;

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

    public ReturnDataBean getReturn_data() {
        return return_data;
    }

    public void setReturn_data(ReturnDataBean return_data) {
        this.return_data = return_data;
    }

    public static class ReturnDataBean {
        /**
         * code : 用户code
         * person_name : 姓名：赵四
         * cover : 头像
         * nick_name : 昵称
         * phone : 189*****9900
         * recharge_count : 1
         * count_count : 2
         * year_count : 3
         * count_arr : [{"card_user_code":"111","card_name":"次卡","endDate":"2088-10-02"}]
         * year_arr : [{"card_user_code":"111","card_name":"年卡","endDate":"2088-10-02"}]
         * recharge_arr : [{"card_user_code":"111","card_name":"8折卡","balance":8000,"endDate":"2088-10-02"}]
         */

        private String code;
        private String person_name;
        private String cover;
        private String nick_name;
        private String phone;
        private int recharge_count;
        private int count_count;
        private String year_count;
        private List<CountArrBean> count_arr;
        private List<YearArrBean> year_arr;
        private List<RechargeArrBean> recharge_arr;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
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

        public String getYear_count() {
            return year_count;
        }

        public void setYear_count(String year_count) {
            this.year_count = year_count;
        }

        public List<CountArrBean> getCount_arr() {
            return count_arr;
        }

        public void setCount_arr(List<CountArrBean> count_arr) {
            this.count_arr = count_arr;
        }

        public List<YearArrBean> getYear_arr() {
            return year_arr;
        }

        public void setYear_arr(List<YearArrBean> year_arr) {
            this.year_arr = year_arr;
        }

        public List<RechargeArrBean> getRecharge_arr() {
            return recharge_arr;
        }

        public void setRecharge_arr(List<RechargeArrBean> recharge_arr) {
            this.recharge_arr = recharge_arr;
        }

        public static class CountArrBean {
            /**
             * card_user_code : 111
             * card_name : 次卡
             * endDate : 2088-10-02
             */

            private String card_user_code;
            private String card_name;
            private String endDate;
            private String surplus_times;

            public String getSurplus_times() {
                return surplus_times;
            }

            public void setSurplus_times(String surplus_times) {
                this.surplus_times = surplus_times;
            }

            public String getCard_user_code() {
                return card_user_code;
            }

            public void setCard_user_code(String card_user_code) {
                this.card_user_code = card_user_code;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }
        }

        public static class YearArrBean {
            /**
             * card_user_code : 111
             * card_name : 年卡
             * endDate : 2088-10-02
             */

            private String card_user_code;
            private String card_name;
            private String endDate;

            public String getCard_user_code() {
                return card_user_code;
            }

            public void setCard_user_code(String card_user_code) {
                this.card_user_code = card_user_code;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }
        }

        public static class RechargeArrBean {
            /**
             * card_user_code : 111
             * card_name : 8折卡
             * balance : 8000
             * endDate : 2088-10-02
             */

            private String card_user_code;
            private String card_name;
            private int balance;
            private String endDate;
            private String card_isEffective;

            public String getCard_isEffective() {
                return card_isEffective;
            }

            public void setCard_isEffective(String card_isEffective) {
                this.card_isEffective = card_isEffective;
            }


            public String getCard_user_code() {
                return card_user_code;
            }

            public void setCard_user_code(String card_user_code) {
                this.card_user_code = card_user_code;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
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
