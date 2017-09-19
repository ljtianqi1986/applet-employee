package com.sk.meikelai.entity;

/**
 * Created by sk on 2017/7/5.
 */

public class HomeCountBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : {"return_month_commission":26100,"return_today_appointment":0,"return_month_achievement":439493000,"return_today_baseUser":0,"return_today_commission":0,"return_today_achievement":0,"return_all_baseUser":0,"return_month_cash":439478000,"return_tomorrow_appointment":0,"return_today_cash":0}
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
         * return_month_commission : 26100
         * return_today_appointment : 0
         * return_month_achievement : 439493000
         * return_today_baseUser : 0
         * return_today_commission : 0
         * return_today_achievement : 0
         * return_all_baseUser : 0
         * return_month_cash : 439478000
         * return_tomorrow_appointment : 0
         * return_today_cash : 0
         */

        private int return_month_commission;
        private int return_today_appointment;
        private int return_month_achievement;
        private int return_today_baseUser;
        private int return_today_commission;
        private int return_today_achievement;
        private int return_all_baseUser;
        private int return_month_cash;
        private int return_tomorrow_appointment;
        private int return_today_cash;

        public int getReturn_month_commission() {
            return return_month_commission;
        }

        public void setReturn_month_commission(int return_month_commission) {
            this.return_month_commission = return_month_commission;
        }

        public int getReturn_today_appointment() {
            return return_today_appointment;
        }

        public void setReturn_today_appointment(int return_today_appointment) {
            this.return_today_appointment = return_today_appointment;
        }

        public int getReturn_month_achievement() {
            return return_month_achievement;
        }

        public void setReturn_month_achievement(int return_month_achievement) {
            this.return_month_achievement = return_month_achievement;
        }

        public int getReturn_today_baseUser() {
            return return_today_baseUser;
        }

        public void setReturn_today_baseUser(int return_today_baseUser) {
            this.return_today_baseUser = return_today_baseUser;
        }

        public int getReturn_today_commission() {
            return return_today_commission;
        }

        public void setReturn_today_commission(int return_today_commission) {
            this.return_today_commission = return_today_commission;
        }

        public int getReturn_today_achievement() {
            return return_today_achievement;
        }

        public void setReturn_today_achievement(int return_today_achievement) {
            this.return_today_achievement = return_today_achievement;
        }

        public int getReturn_all_baseUser() {
            return return_all_baseUser;
        }

        public void setReturn_all_baseUser(int return_all_baseUser) {
            this.return_all_baseUser = return_all_baseUser;
        }

        public int getReturn_month_cash() {
            return return_month_cash;
        }

        public void setReturn_month_cash(int return_month_cash) {
            this.return_month_cash = return_month_cash;
        }

        public int getReturn_tomorrow_appointment() {
            return return_tomorrow_appointment;
        }

        public void setReturn_tomorrow_appointment(int return_tomorrow_appointment) {
            this.return_tomorrow_appointment = return_tomorrow_appointment;
        }

        public int getReturn_today_cash() {
            return return_today_cash;
        }

        public void setReturn_today_cash(int return_today_cash) {
            this.return_today_cash = return_today_cash;
        }
    }
}
