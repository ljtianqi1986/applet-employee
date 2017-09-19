package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by lenovo on 2017/7/12.
 */

public class AppointmentBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"business_name":"","appointment_time":"2017-07-28 13:00:00.0","code":"d7cb976092c84e379dd4ef37c0b4fc3e","create_time":"2017-07-28 15:36:50.0","shop_code":"1","sys_user_name":"高淑珍","remark":"备注","sys_user_code":"mklshop1","telephone":"15851196268","content":"","sys_user_remark":"","appointmentTime":"13:00","branch_name":"","base_user_code":"","isdel":0,"project_type_name":"面部护理","base_user_name":"","nums":0,"project_type_code":"156347ae3c8b4c2ca39bd9f37ea5b92a"},{"business_name":"","appointment_time":"2017-07-28 13:00:00.0","code":"68f936abc826472f9554b16c0da3b9de","create_time":"2017-07-28 11:56:11.0","shop_code":"1","sys_user_name":"高淑珍","remark":"","sys_user_code":"mklshop1","telephone":"15851196268","content":"","sys_user_remark":"","appointmentTime":"13:00","branch_name":"","base_user_code":"","isdel":0,"project_type_name":"面部护理","base_user_name":"","nums":0,"project_type_code":"156347ae3c8b4c2ca39bd9f37ea5b92a"},{"business_name":"","appointment_time":"2017-07-28 09:00:00.0","code":"ae60c2a9d1464a44b3d1b682e8438744","create_time":"2017-07-28 11:55:27.0","shop_code":"1","sys_user_name":"高淑珍","remark":"备注信息哦","sys_user_code":"mklshop1","telephone":"15851196268","content":"","sys_user_remark":"","appointmentTime":"09:00","branch_name":"","base_user_code":"","isdel":0,"project_type_name":"其他","base_user_name":"","nums":0,"project_type_code":"6d9a76a4e8284df3adab3452d675d485"},{"business_name":"","appointment_time":"2017-07-28 00:02:00.0","code":"18cc15259cb241a19ba4e78ba6fddc87","create_time":"2017-07-27 17:24:52.0","shop_code":"1","sys_user_name":"高淑珍","remark":"beiz","sys_user_code":"mklshop1","telephone":"13890909090","content":"","sys_user_remark":"","appointmentTime":"00:02","branch_name":"","base_user_code":"","isdel":0,"project_type_name":"美睫,手足护理,化妆,面部护理","base_user_name":"","nums":0,"project_type_code":"156347ae3c8b4c2ca39bd9f37ea5b92a,b78e85e17df24c78a054250341b2fe9f,6a6884b89da34717b10990d491432035,ea3b0c91183b4baf8d27637ca0d03bdd"},{"business_name":"","appointment_time":"2017-07-28 00:00:00.0","code":"007d431c5c9749e387e162c0f648a385","create_time":"2017-07-27 17:23:47.0","shop_code":"1","sys_user_name":"高淑珍","remark":"","sys_user_code":"mklshop1","telephone":"13890909090","content":"","sys_user_remark":"111","appointmentTime":"00:00","branch_name":"","base_user_code":"","isdel":0,"project_type_name":"美睫,化妆","base_user_name":"","nums":0,"project_type_code":"b78e85e17df24c78a054250341b2fe9f,ea3b0c91183b4baf8d27637ca0d03bdd"}]
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
         * business_name :
         * appointment_time : 2017-07-28 13:00:00.0
         * code : d7cb976092c84e379dd4ef37c0b4fc3e
         * create_time : 2017-07-28 15:36:50.0
         * shop_code : 1
         * sys_user_name : 高淑珍
         * remark : 备注
         * sys_user_code : mklshop1
         * telephone : 15851196268
         * content :
         * sys_user_remark :
         * appointmentTime : 13:00
         * branch_name :
         * base_user_code :
         * isdel : 0
         * project_type_name : 面部护理
         * base_user_name :
         * nums : 0
         * project_type_code : 156347ae3c8b4c2ca39bd9f37ea5b92a
         */

        private String business_name;
        private String appointment_time;
        private String code;
        private String create_time;
        private String shop_code;
        private String sys_user_name;
        private String remark;
        private String sys_user_code;
        private String telephone;
        private String content;
        private String sys_user_remark;
        private String appointmentTime;
        private String branch_name;
        private String base_user_code;
        private int isdel;
        private String project_type_name;
        private String base_user_name;
        private int nums;
        private String project_type_code;

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

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

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getSys_user_name() {
            return sys_user_name;
        }

        public void setSys_user_name(String sys_user_name) {
            this.sys_user_name = sys_user_name;
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

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSys_user_remark() {
            return sys_user_remark;
        }

        public void setSys_user_remark(String sys_user_remark) {
            this.sys_user_remark = sys_user_remark;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public String getBase_user_code() {
            return base_user_code;
        }

        public void setBase_user_code(String base_user_code) {
            this.base_user_code = base_user_code;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getProject_type_name() {
            return project_type_name;
        }

        public void setProject_type_name(String project_type_name) {
            this.project_type_name = project_type_name;
        }

        public String getBase_user_name() {
            return base_user_name;
        }

        public void setBase_user_name(String base_user_name) {
            this.base_user_name = base_user_name;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public String getProject_type_code() {
            return project_type_code;
        }

        public void setProject_type_code(String project_type_code) {
            this.project_type_code = project_type_code;
        }
    }
}
