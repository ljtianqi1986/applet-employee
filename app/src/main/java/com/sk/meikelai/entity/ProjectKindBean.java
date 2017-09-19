package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by sk on 2017/7/18.
 */

public class ProjectKindBean {

    /**
     * return_data : [{"code":"a9b658b0248648319f553b8340ab574d","name":"SPA"},{"code":"2","name":"美体"},{"code":"1","name":"美甲"}]
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
         * code : a9b658b0248648319f553b8340ab574d
         * name : SPA
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
