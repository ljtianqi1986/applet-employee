package com.sk.meikelai.entity;

/**
 * Created by Administrator on 2017/6/26.
 */

public class WorkerInfoBean {

    /**
     * return_code : 1
     * return_info : 登录成功
     * return_data : {"user_code":"pooiii","login_name":"xxdt","pwd":"eerrrrrrreeeee","phone":"18851618899","person_name":"收银1","identity_code":"dfsfsdfsdfdsfd","type":0,"shop_name":"小翼连锁超市","shop_code":"oeiriirofkdsjljf","address":"扬州市声谷1期"}
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
         * user_code : pooiii
         * login_name : xxdt
         * pwd : eerrrrrrreeeee
         * phone : 18851618899
         * person_name : 收银1
         * identity_code : dfsfsdfsdfdsfd
         * type : 0
         * shop_name : 小翼连锁超市
         * shop_code : oeiriirofkdsjljf
         * address : 扬州市声谷1期
         */

        private String user_code;
        private String login_name;
        private String pwd;
        private String phone;
        private String person_name;
        private String identity_code;
        private int type;
        private String shop_name;
        private String shop_code;
        private String address;
        private String logo_url;
        private String pic_url;
        private String agent_name;
        private String jobNumber;

        public String getJobNumber() {
            return jobNumber;
        }

        public void setJobNumber(String jobNumber) {
            this.jobNumber = jobNumber;
        }

        public String getAgent_name() {
            return agent_name;
        }

        public void setAgent_name(String agent_name) {
            this.agent_name = agent_name;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getLogin_name() {
            return login_name;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getIdentity_code() {
            return identity_code;
        }

        public void setIdentity_code(String identity_code) {
            this.identity_code = identity_code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
