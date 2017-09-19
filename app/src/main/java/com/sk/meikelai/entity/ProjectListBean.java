package com.sk.meikelai.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */

public class ProjectListBean {


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"type_code":"da9f306f7d4e4246bc9d519e4235399c","price":12300,"name":"测试","commission":2,"handworkprice":"","projectTypeName":"","agent_code":"1001","state":"0","code":"34a836996b61464ebf06dd90931b1f9d","about":"","projectprice":"","name_pinyin":"ceshi","number":"111","agent_name":"","unit":"个","user_code":"","isdel":0,"create_time":"2017-07-18 14:55:04.0","notes":"","userName":"","agent_select":"","handwork":23400},{"agent_select":"","userName":"","notes":"","commission":0,"about":"","state":"0","create_time":"2017-07-18 10:02:41.0","unit":"个","handwork":100,"code":"ada612b710d64051aa228c939ae06345","price":190,"agent_name":"","user_code":"","number":"www","name":"王","name_pinyin":"wang","projectprice":"","projectTypeName":"","isdel":0,"agent_code":"1001","type_code":"da9f306f7d4e4246bc9d519e4235399c","handworkprice":""},{"handworkprice":"","user_code":"","projectTypeName":"","type_code":"91e26d93ec754564b51e93955be2a1e7","price":11100,"agent_select":"","number":"222","name":"333","isdel":0,"commission":10,"state":"1","agent_code":"1001","name_pinyin":"333","unit":"ge ","agent_name":"","about":"","projectprice":"","handwork":100,"userName":"","notes":"","code":"ca362ceed1694a75b7b8ae7bbece12ad","create_time":"2017-07-07 09:03:42.0"},{"about":"","create_time":"2017-07-06 17:25:40.0","code":"0347bac34daf418c93f44ec7ad9ad8ac","agent_name":"","isdel":0,"commission":0,"number":"111","user_code":"","name_pinyin":"222","agent_select":"","projectTypeName":"","price":11100,"notes":"","handworkprice":"","userName":"","state":"0","projectprice":"","handwork":100,"name":"222","agent_code":"1001","unit":"个","type_code":"2"},{"agent_select":"","userName":"","number":"IOSB","isdel":0,"projectprice":"","unit":"元","agent_name":"","agent_code":"1001","projectTypeName":"","type_code":"1","name":"SPA","handwork":200,"handworkprice":"","notes":"222","code":"2","create_time":"2017-06-07 10:06:13.0","state":"0","name_pinyin":"SPA","about":"","user_code":"zsy","price":22200,"commission":15},{"isdel":0,"state":"0","name_pinyin":"meijia","handworkprice":"","agent_name":"","about":"","agent_select":"","name":"美甲","type_code":"1","notes":"333","unit":"元","price":33300,"number":"IOSA","projectprice":"","projectTypeName":"","create_time":"2017-06-07 10:04:35.0","commission":10,"user_code":"7f56ac4919ee4cfea54dd32d30429753","code":"6ef0b0d722064937b9b4d34d26220d2e","userName":"","handwork":1000,"agent_code":"1001"}]
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

    public static class ReturnDataBean implements Parcelable {
        /**
         * type_code : da9f306f7d4e4246bc9d519e4235399c
         * price : 12300
         * name : 测试
         * commission : 2
         * handworkprice :
         * projectTypeName :
         * agent_code : 1001
         * state : 0
         * code : 34a836996b61464ebf06dd90931b1f9d
         * about :
         * projectprice :
         * name_pinyin : ceshi
         * number : 111
         * agent_name :
         * unit : 个
         * user_code :
         * isdel : 0
         * create_time : 2017-07-18 14:55:04.0
         * notes :
         * userName :
         * agent_select :
         * handwork : 23400
         */

        private String type_code;
        private int price;
        private String name;
        private int commission;
        private String handworkprice;
        private String projectTypeName;
        private String agent_code;
        private String state;
        private String code;
        private String about;
        private String projectprice;
        private String name_pinyin;
        private String number;
        private String agent_name;
        private String unit;
        private String user_code;
        private int isdel;
        private String create_time;
        private String notes;
        private String userName;
        private String agent_select;
        private int handwork;
        private int count = 0;
        private boolean isDiscount = true;
        private String pepCode;
        private int actual_price;

        public int getActual_price() {
            return actual_price;
        }

        public void setActual_price(int actual_price) {
            this.actual_price = actual_price;
        }

        public String getPepCode() {
            return pepCode;
        }

        public void setPepCode(String pepCode) {
            this.pepCode = pepCode;
        }

        public boolean isDiscount() {
            return isDiscount;
        }

        public void setDiscount(boolean discount) {
            isDiscount = discount;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getType_code() {
            return type_code;
        }

        public void setType_code(String type_code) {
            this.type_code = type_code;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public String getHandworkprice() {
            return handworkprice;
        }

        public void setHandworkprice(String handworkprice) {
            this.handworkprice = handworkprice;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getAgent_code() {
            return agent_code;
        }

        public void setAgent_code(String agent_code) {
            this.agent_code = agent_code;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getProjectprice() {
            return projectprice;
        }

        public void setProjectprice(String projectprice) {
            this.projectprice = projectprice;
        }

        public String getName_pinyin() {
            return name_pinyin;
        }

        public void setName_pinyin(String name_pinyin) {
            this.name_pinyin = name_pinyin;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getAgent_name() {
            return agent_name;
        }

        public void setAgent_name(String agent_name) {
            this.agent_name = agent_name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
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

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAgent_select() {
            return agent_select;
        }

        public void setAgent_select(String agent_select) {
            this.agent_select = agent_select;
        }

        public int getHandwork() {
            return handwork;
        }

        public void setHandwork(int handwork) {
            this.handwork = handwork;
        }

        public ReturnDataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.type_code);
            dest.writeInt(this.price);
            dest.writeString(this.name);
            dest.writeInt(this.commission);
            dest.writeString(this.handworkprice);
            dest.writeString(this.projectTypeName);
            dest.writeString(this.agent_code);
            dest.writeString(this.state);
            dest.writeString(this.code);
            dest.writeString(this.about);
            dest.writeString(this.projectprice);
            dest.writeString(this.name_pinyin);
            dest.writeString(this.number);
            dest.writeString(this.agent_name);
            dest.writeString(this.unit);
            dest.writeString(this.user_code);
            dest.writeInt(this.isdel);
            dest.writeString(this.create_time);
            dest.writeString(this.notes);
            dest.writeString(this.userName);
            dest.writeString(this.agent_select);
            dest.writeInt(this.handwork);
            dest.writeInt(this.count);
            dest.writeByte(this.isDiscount ? (byte) 1 : (byte) 0);
            dest.writeString(this.pepCode);
            dest.writeInt(this.actual_price);
        }

        protected ReturnDataBean(Parcel in) {
            this.type_code = in.readString();
            this.price = in.readInt();
            this.name = in.readString();
            this.commission = in.readInt();
            this.handworkprice = in.readString();
            this.projectTypeName = in.readString();
            this.agent_code = in.readString();
            this.state = in.readString();
            this.code = in.readString();
            this.about = in.readString();
            this.projectprice = in.readString();
            this.name_pinyin = in.readString();
            this.number = in.readString();
            this.agent_name = in.readString();
            this.unit = in.readString();
            this.user_code = in.readString();
            this.isdel = in.readInt();
            this.create_time = in.readString();
            this.notes = in.readString();
            this.userName = in.readString();
            this.agent_select = in.readString();
            this.handwork = in.readInt();
            this.count = in.readInt();
            this.isDiscount = in.readByte() != 0;
            this.pepCode = in.readString();
            this.actual_price = in.readInt();
        }

        public static final Creator<ReturnDataBean> CREATOR = new Creator<ReturnDataBean>() {
            @Override
            public ReturnDataBean createFromParcel(Parcel source) {
                return new ReturnDataBean(source);
            }

            @Override
            public ReturnDataBean[] newArray(int size) {
                return new ReturnDataBean[size];
            }
        };
    }
}
