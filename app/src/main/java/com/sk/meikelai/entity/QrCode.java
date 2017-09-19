package com.sk.meikelai.entity;

/**
 * Created by Administrator on 2017/7/26.
 */

public class QrCode {

    /**
     * return_code : 1
     * return_info : 新增会员成功
     * return_data : https://mp.weixin.qq
     * .com/cgi-bin/showqrcode?ticket=gQFK7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycHhEQUl0dTliblAxTFpLdnhwMVMAAgT9YXhZAwQAjScA
     */

    private int return_code;
    private String return_info;
    private String return_data;

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

    public String getReturn_data() {
        return return_data;
    }

    public void setReturn_data(String return_data) {
        this.return_data = return_data;
    }
}
