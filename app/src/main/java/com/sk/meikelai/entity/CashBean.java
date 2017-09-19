package com.sk.meikelai.entity;

/**
 * Created by sk on 2017/7/11.
 */

public class CashBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data_kkss : 32000
     * return_data_czss : 14400
     * return_data_cpss : 10
     * return_data_xmss : 300
     */

    private int return_code;
    private String return_info;
    private int return_data_kkss;
    private int return_data_czss;
    private int return_data_cpss;
    private int return_data_xmss;

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

    public int getReturn_data_kkss() {
        return return_data_kkss;
    }

    public void setReturn_data_kkss(int return_data_kkss) {
        this.return_data_kkss = return_data_kkss;
    }

    public int getReturn_data_czss() {
        return return_data_czss;
    }

    public void setReturn_data_czss(int return_data_czss) {
        this.return_data_czss = return_data_czss;
    }

    public int getReturn_data_cpss() {
        return return_data_cpss;
    }

    public void setReturn_data_cpss(int return_data_cpss) {
        this.return_data_cpss = return_data_cpss;
    }

    public int getReturn_data_xmss() {
        return return_data_xmss;
    }

    public void setReturn_data_xmss(int return_data_xmss) {
        this.return_data_xmss = return_data_xmss;
    }
}
