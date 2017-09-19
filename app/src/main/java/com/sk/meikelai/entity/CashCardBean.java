package com.sk.meikelai.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class CashCardBean {

    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"project_name":"项目1","card_name":"ceshikazu","project_number":"IOSA","times":8,"create_time":"2017-06-15
     * 14:11:04.0","project_price":10000,"project_code":"04a494c63e3a4784949b64f556ea5276","card_code":"1","surplus_times":8,"isdel":0,
     * "project_name_pinyin":"xiangmu1","person_name":"测试12346","card_useful":"12","user_code":"123","code":"1"}]
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
         * project_name : 项目1
         * card_name : ceshikazu
         * project_number : IOSA
         * times : 8
         * create_time : 2017-06-15 14:11:04.0
         * project_price : 10000
         * project_code : 04a494c63e3a4784949b64f556ea5276
         * card_code : 1
         * surplus_times : 8
         * isdel : 0
         * project_name_pinyin : xiangmu1
         * person_name : 测试12346
         * card_useful : 12
         * user_code : 123
         * code : 1
         */

        private String project_name;
        private String card_name;
        private String project_number;
        private int times;
        private String create_time;
        private int project_price;
        private String project_code;
        private String card_code;
        private String card_user_code;
        private int surplus_times;
        private int isdel;
        private String endTime;
        private String project_name_pinyin;
        private String person_name;
        private String card_useful;
        private String user_code;
        private String code;
        private int count = 0;//goumaide shuliang
        private int cardType;
        private String pepCode;

        public String getCard_user_code() {
            return card_user_code;
        }

        public void setCard_user_code(String card_user_code) {
            this.card_user_code = card_user_code;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPepCode() {
            return pepCode;
        }

        public void setPepCode(String pepCode) {
            this.pepCode = pepCode;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getProject_number() {
            return project_number;
        }

        public void setProject_number(String project_number) {
            this.project_number = project_number;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getProject_price() {
            return project_price;
        }

        public void setProject_price(int project_price) {
            this.project_price = project_price;
        }

        public String getProject_code() {
            return project_code;
        }

        public void setProject_code(String project_code) {
            this.project_code = project_code;
        }

        public String getCard_code() {
            return card_code;
        }

        public void setCard_code(String card_code) {
            this.card_code = card_code;
        }

        public int getSurplus_times() {
            return surplus_times;
        }

        public void setSurplus_times(int surplus_times) {
            this.surplus_times = surplus_times;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getProject_name_pinyin() {
            return project_name_pinyin;
        }

        public void setProject_name_pinyin(String project_name_pinyin) {
            this.project_name_pinyin = project_name_pinyin;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getCard_useful() {
            return card_useful;
        }

        public void setCard_useful(String card_useful) {
            this.card_useful = card_useful;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public ReturnDataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.project_name);
            dest.writeString(this.card_name);
            dest.writeString(this.project_number);
            dest.writeInt(this.times);
            dest.writeString(this.create_time);
            dest.writeInt(this.project_price);
            dest.writeString(this.project_code);
            dest.writeString(this.card_code);
            dest.writeString(this.card_user_code);
            dest.writeInt(this.surplus_times);
            dest.writeInt(this.isdel);
            dest.writeString(this.endTime);
            dest.writeString(this.project_name_pinyin);
            dest.writeString(this.person_name);
            dest.writeString(this.card_useful);
            dest.writeString(this.user_code);
            dest.writeString(this.code);
            dest.writeInt(this.count);
            dest.writeInt(this.cardType);
            dest.writeString(this.pepCode);
        }

        protected ReturnDataBean(Parcel in) {
            this.project_name = in.readString();
            this.card_name = in.readString();
            this.project_number = in.readString();
            this.times = in.readInt();
            this.create_time = in.readString();
            this.project_price = in.readInt();
            this.project_code = in.readString();
            this.card_code = in.readString();
            this.card_user_code = in.readString();
            this.surplus_times = in.readInt();
            this.isdel = in.readInt();
            this.endTime = in.readString();
            this.project_name_pinyin = in.readString();
            this.person_name = in.readString();
            this.card_useful = in.readString();
            this.user_code = in.readString();
            this.code = in.readString();
            this.count = in.readInt();
            this.cardType = in.readInt();
            this.pepCode = in.readString();
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
