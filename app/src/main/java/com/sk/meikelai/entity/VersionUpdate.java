package com.sk.meikelai.entity;

/**
 * Created by Administrator on 2017/8/28.
 */

public class VersionUpdate {
    /**
     * return_code : 1
     * version_code : 100
     * version_info : 1、优化部分功能|2、修复已知BUG
     */

    private int return_code;
    private int version_code;
    private String version_info;

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getVersion_info() {
        return version_info;
    }

    public void setVersion_info(String version_info) {
        this.version_info = version_info;
    }
}
