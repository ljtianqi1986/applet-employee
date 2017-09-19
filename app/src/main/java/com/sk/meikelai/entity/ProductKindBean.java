package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by sk on 2017/7/18.
 */

public class ProductKindBean {


    /**
     * return_data : [{"code":"bd2e2d5438484bb08d06e697b491a07c","name":"222222"},{"code":"ce9c55edcf64407fab0e3bb069d5a431","name":"美甲11"},{"code":"2e9c55edcf64407fab0e3bb069d5a431","name":"眉睫"}]
     * return_code : 1
     * return_info : 查找成功!
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
         * code : bd2e2d5438484bb08d06e697b491a07c
         * name : 222222
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
