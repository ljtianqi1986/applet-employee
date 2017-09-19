package com.sk.meikelai.Adapter;

import java.util.List;

/**
 * Created by sk on 2017/7/27.
 */

public class HandOverHistoryBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"code":"1","yes_imprest":0,"today_cash":0,"today_imprest":0,"ought_connect":1000,"reality_connect":600,"file_url":"http://ceshi.file.do","remark":"备注","sys_user_code":"mklshop","shop_code":"1","declare_time":"2017-05-08 17:24:04.0","declare_style":1,"auditor_code":"mklbrand","auditor_style":1,"isdel":0,"create_time":"2017-05-08 17:24:04.0"}]
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
         * code : 1
         * yes_imprest : 0
         * today_cash : 0
         * today_imprest : 0
         * ought_connect : 1000
         * reality_connect : 600
         * file_url : http://ceshi.file.do
         * remark : 备注
         * sys_user_code : mklshop
         * shop_code : 1
         * declare_time : 2017-05-08 17:24:04.0
         * declare_style : 1
         * auditor_code : mklbrand
         * auditor_style : 1
         * isdel : 0
         * create_time : 2017-05-08 17:24:04.0
         */

        private String code;
        private int yes_imprest;
        private int today_cash;
        private int today_imprest;
        private int ought_connect;
        private int reality_connect;
        private String file_url;
        private String remark;
        private String sys_user_code;
        private String shop_code;
        private String declare_time;
        private int declare_style;
        private String auditor_code;
        private int auditor_style;
        private int isdel;
        private String create_time;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getYes_imprest() {
            return yes_imprest;
        }

        public void setYes_imprest(int yes_imprest) {
            this.yes_imprest = yes_imprest;
        }

        public int getToday_cash() {
            return today_cash;
        }

        public void setToday_cash(int today_cash) {
            this.today_cash = today_cash;
        }

        public int getToday_imprest() {
            return today_imprest;
        }

        public void setToday_imprest(int today_imprest) {
            this.today_imprest = today_imprest;
        }

        public int getOught_connect() {
            return ought_connect;
        }

        public void setOught_connect(int ought_connect) {
            this.ought_connect = ought_connect;
        }

        public int getReality_connect() {
            return reality_connect;
        }

        public void setReality_connect(int reality_connect) {
            this.reality_connect = reality_connect;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSys_user_code() {
            return sys_user_code;
        }

        public void setSys_user_code(String sys_user_code) {
            this.sys_user_code = sys_user_code;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getDeclare_time() {
            return declare_time;
        }

        public void setDeclare_time(String declare_time) {
            this.declare_time = declare_time;
        }

        public int getDeclare_style() {
            return declare_style;
        }

        public void setDeclare_style(int declare_style) {
            this.declare_style = declare_style;
        }

        public String getAuditor_code() {
            return auditor_code;
        }

        public void setAuditor_code(String auditor_code) {
            this.auditor_code = auditor_code;
        }

        public int getAuditor_style() {
            return auditor_style;
        }

        public void setAuditor_style(int auditor_style) {
            this.auditor_style = auditor_style;
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
