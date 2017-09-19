package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */

public class ComeWayBean {
    /**
     * return_code : 1
     * return_info : 登录成功
     * return_data : [{"code":"1","name":"自己找的"},{"code":"1","name":"还是自己找的"}]
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
         * name : 自己找的
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
