package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */

public class SearchListBean {
    /**
     * return_code : 1
     * return_info : 检索成功！
     * return_data : [{"code":"cc6eab3916e04bfd8d55607bba7da89e","agent_name":"","create_time":{"date":19,"hours":21,"seconds":27,
     * "month":6,"timezoneOffset":-480,"year":117,"minutes":52,"time":1500472347000,"day":3},"name":"美睫","agent_select":"",
     * "agent_code":"1001","isdel":0},{"code":"588fc34c93a64abeaf4b861ce9e784d0","agent_name":"","create_time":{"date":19,"hours":21,
     * "seconds":17,"month":6,"timezoneOffset":-480,"year":117,"minutes":52,"time":1500472337000,"day":3},"name":"综合","agent_select":"",
     * "agent_code":"1001","isdel":0},{"code":"011b7c461bab4f708f1f8555c9de6d9c","agent_name":"","create_time":{"date":19,"hours":21,
     * "seconds":0,"month":6,"timezoneOffset":-480,"year":117,"minutes":52,"time":1500472320000,"day":3},"name":"手足护理","agent_select":"",
     * "agent_code":"1001","isdel":0},{"code":"5a9a963daef6448e871a4d33db488318","agent_name":"","create_time":{"date":19,"hours":21,
     * "seconds":36,"month":6,"timezoneOffset":-480,"year":117,"minutes":51,"time":1500472296000,"day":3},"name":"美容","agent_select":"",
     * "agent_code":"1001","isdel":0},{"code":"8dbcbcfdbef24ba7b3a7d937243ed11b","agent_name":"","create_time":{"date":28,"hours":10,
     * "seconds":12,"month":5,"timezoneOffset":-480,"year":117,"minutes":30,"time":1498617012000,"day":3},"name":"美甲","agent_select":"",
     * "agent_code":"1001","isdel":0}]
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
         * code : cc6eab3916e04bfd8d55607bba7da89e
         * agent_name :
         * create_time : {"date":19,"hours":21,"seconds":27,"month":6,"timezoneOffset":-480,"year":117,"minutes":52,"time":1500472347000,
         * "day":3}
         * name : 美睫
         * agent_select :
         * agent_code : 1001
         * isdel : 0
         */

        private String code;
        private String agent_name;
        private CreateTimeBean create_time;
        private String name;
        private String agent_select;
        private String agent_code;
        private int isdel;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAgent_name() {
            return agent_name;
        }

        public void setAgent_name(String agent_name) {
            this.agent_name = agent_name;
        }

        public CreateTimeBean getCreate_time() {
            return create_time;
        }

        public void setCreate_time(CreateTimeBean create_time) {
            this.create_time = create_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAgent_select() {
            return agent_select;
        }

        public void setAgent_select(String agent_select) {
            this.agent_select = agent_select;
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

        public static class CreateTimeBean {
            /**
             * date : 19
             * hours : 21
             * seconds : 27
             * month : 6
             * timezoneOffset : -480
             * year : 117
             * minutes : 52
             * time : 1500472347000
             * day : 3
             */

            private int date;
            private int hours;
            private int seconds;
            private int month;
            private int timezoneOffset;
            private int year;
            private int minutes;
            private long time;
            private int day;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }
    }
}
