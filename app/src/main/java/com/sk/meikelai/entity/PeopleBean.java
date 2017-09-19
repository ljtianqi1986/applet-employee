package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */

public class PeopleBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"authorizer_appid":"","flag":"","notes":"","role":null,"type":2,"user_code":"9ddf848cefff44bf8bf50bd2a639d9e4","identity":4,"isdel":0,"email":"","islock":0,"today_commission":0,"create_time":"2017-06-06 20:02:09.0","area_code":"","identity_name":"","role_code":"b94e46fac1fe4160a499456f2b18ed13","person_name":"zxzxzx01","sort":99,"month_commission":0,"role_name":"","identity_code":"e2913b8513e14328b9f6d82aac641de7","login_name":"zxzxzx01","phone":"","coefficient":0,"discount_percent":0,"pwd":"c4ca4238a0b923820dcc509a6f75849b","org_code":""}]
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
         * authorizer_appid :
         * flag :
         * notes :
         * role : null
         * type : 2
         * user_code : 9ddf848cefff44bf8bf50bd2a639d9e4
         * identity : 4
         * isdel : 0
         * email :
         * islock : 0
         * today_commission : 0
         * create_time : 2017-06-06 20:02:09.0
         * area_code :
         * identity_name :
         * role_code : b94e46fac1fe4160a499456f2b18ed13
         * person_name : zxzxzx01
         * sort : 99
         * month_commission : 0
         * role_name :
         * identity_code : e2913b8513e14328b9f6d82aac641de7
         * login_name : zxzxzx01
         * phone :
         * coefficient : 0
         * discount_percent : 0
         * pwd : c4ca4238a0b923820dcc509a6f75849b
         * org_code :
         */

        private String authorizer_appid;
        private String flag;
        private String notes;
        private Object role;
        private int type;
        private String user_code;
        private int identity;
        private int isdel;
        private String email;
        private int islock;
        private int today_commission;
        private String create_time;
        private String area_code;
        private String identity_name;
        private String role_code;
        private String person_name;
        private int sort;
        private int month_commission;
        private String role_name;
        private String identity_code;
        private String login_name;
        private String phone;
        private int coefficient;
        private int discount_percent;
        private String pwd;
        private String org_code;

        public String getAuthorizer_appid() {
            return authorizer_appid;
        }

        public void setAuthorizer_appid(String authorizer_appid) {
            this.authorizer_appid = authorizer_appid;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIslock() {
            return islock;
        }

        public void setIslock(int islock) {
            this.islock = islock;
        }

        public int getToday_commission() {
            return today_commission;
        }

        public void setToday_commission(int today_commission) {
            this.today_commission = today_commission;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getArea_code() {
            return area_code;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public String getIdentity_name() {
            return identity_name;
        }

        public void setIdentity_name(String identity_name) {
            this.identity_name = identity_name;
        }

        public String getRole_code() {
            return role_code;
        }

        public void setRole_code(String role_code) {
            this.role_code = role_code;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getMonth_commission() {
            return month_commission;
        }

        public void setMonth_commission(int month_commission) {
            this.month_commission = month_commission;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public String getIdentity_code() {
            return identity_code;
        }

        public void setIdentity_code(String identity_code) {
            this.identity_code = identity_code;
        }

        public String getLogin_name() {
            return login_name;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getDiscount_percent() {
            return discount_percent;
        }

        public void setDiscount_percent(int discount_percent) {
            this.discount_percent = discount_percent;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getOrg_code() {
            return org_code;
        }

        public void setOrg_code(String org_code) {
            this.org_code = org_code;
        }
    }
}
