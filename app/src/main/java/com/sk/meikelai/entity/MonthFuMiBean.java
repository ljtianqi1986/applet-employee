package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by sk on 2017/7/26.
 */

public class MonthFuMiBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"dateTime":"07-01","addSum":8080,"deduct":0,"nums":8080},{"dateTime":"07-06","addSum":16780,"deduct":3600,"nums":13180}]
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
         * dateTime : 07-01
         * addSum : 8080
         * deduct : 0
         * nums : 8080
         */

        private String dateTime;
        private int addSum;
        private int deduct;
        private int nums;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public int getAddSum() {
            return addSum;
        }

        public void setAddSum(int addSum) {
            this.addSum = addSum;
        }

        public int getDeduct() {
            return deduct;
        }

        public void setDeduct(int deduct) {
            this.deduct = deduct;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }
    }
}
