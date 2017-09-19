package com.sk.meikelai.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by sk on 2017/7/19.
 */

public class ProductListBean {

    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"remark":"","is_gift":1,"price":190,"number":"22222","agent_code":"1001","code":"70056ec0f6424dd39bc968264ecb8b47","card_code":"","name":"11111111","userName":"","produceTypeName":"","create_time":"2017-07-18 13:42:00.0","cardName":"","user_code":"","standard":"","isdel":0,"type_code":"ce9c55edcf64407fab0e3bb069d5a431","unit":"个","procuctprice":"","agent_name":"","state":"1","agent_select":"","name_pinyin":"11111111","commission":0},{"userName":"","user_code":"","number":"11123","name_pinyin":"321","standard":"","state":"0","unit":"个","name":"321","agent_name":"","produceTypeName":"","code":"ee808bafc7ea4763b553b0db0830e166","isdel":0,"agent_select":"","is_gift":1,"commission":11,"card_code":"717bc5ca007142d5beac7e0f271ae30a","cardName":"","price":199,"create_time":"2017-07-06 17:23:19.0","remark":"","agent_code":"1001","type_code":"ce9c55edcf64407fab0e3bb069d5a431","procuctprice":""},{"is_gift":1,"code":"951c20b8dd374ac3989831e36ff4c0e7","number":"22222","procuctprice":"","name_pinyin":"11111111","user_code":"","card_code":"717bc5ca007142d5beac7e0f271ae30a","cardName":"","type_code":"ce9c55edcf64407fab0e3bb069d5a431","price":199,"isdel":0,"unit":"个","produceTypeName":"","name":"11111111","commission":3,"userName":"","agent_name":"","standard":"","agent_code":"1001","state":"0","create_time":"2017-07-06 17:22:20.0","remark":"","agent_select":""},{"agent_name":"","name_pinyin":"11111111","state":"0","standard":"","userName":"","card_code":"717bc5ca007142d5beac7e0f271ae30a","price":11100,"is_gift":1,"agent_code":"1001","produceTypeName":"","number":"1111","name":"11111111","unit":"个","procuctprice":"","cardName":"","type_code":"ce9c55edcf64407fab0e3bb069d5a431","commission":19,"remark":"11","user_code":"","create_time":"2017-07-05 11:19:30.0","agent_select":"","code":"ce5e649883514448a7683096ce3af0ca","isdel":0},{"is_gift":1,"price":299,"agent_code":"1001","userName":"","user_code":"1","remark":"1","code":"1","agent_select":"","type_code":"ce9c55edcf64407fab0e3bb069d5a431","card_code":"594a763e21e74efd8dcf511cb2fd9356","agent_name":"","name":"1","create_time":"2017-07-04 15:21:09.0","number":"1","name_pinyin":"1","standard":"","produceTypeName":"","isdel":0,"unit":"元","commission":50,"cardName":"","procuctprice":"","state":"0"}]
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
         * remark :
         * is_gift : 1
         * price : 190
         * number : 22222
         * agent_code : 1001
         * code : 70056ec0f6424dd39bc968264ecb8b47
         * card_code :
         * name : 11111111
         * userName :
         * produceTypeName :
         * create_time : 2017-07-18 13:42:00.0
         * cardName :
         * user_code :
         * standard :
         * isdel : 0
         * type_code : ce9c55edcf64407fab0e3bb069d5a431
         * unit : 个
         * procuctprice :
         * agent_name :
         * state : 1
         * agent_select :
         * name_pinyin : 11111111
         * commission : 0
         */

        private String remark;
        private int is_gift;
        private int price;
        private String number;
        private String agent_code;
        private String code;
        private String card_code;
        private String name;
        private String userName;
        private String produceTypeName;
        private String create_time;
        private String cardName;
        private String user_code;
        private String standard;
        private int isdel;
        private String type_code;
        private String unit;
        private String procuctprice;
        private String agent_name;
        private String state;
        private String agent_select;
        private String name_pinyin;
        private int commission;
        private int count = 0;
        private boolean isDiscount = false;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIs_gift() {
            return is_gift;
        }

        public void setIs_gift(int is_gift) {
            this.is_gift = is_gift;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getAgent_code() {
            return agent_code;
        }

        public void setAgent_code(String agent_code) {
            this.agent_code = agent_code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCard_code() {
            return card_code;
        }

        public void setCard_code(String card_code) {
            this.card_code = card_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getProduceTypeName() {
            return produceTypeName;
        }

        public void setProduceTypeName(String produceTypeName) {
            this.produceTypeName = produceTypeName;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getType_code() {
            return type_code;
        }

        public void setType_code(String type_code) {
            this.type_code = type_code;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getProcuctprice() {
            return procuctprice;
        }

        public void setProcuctprice(String procuctprice) {
            this.procuctprice = procuctprice;
        }

        public String getAgent_name() {
            return agent_name;
        }

        public void setAgent_name(String agent_name) {
            this.agent_name = agent_name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAgent_select() {
            return agent_select;
        }

        public void setAgent_select(String agent_select) {
            this.agent_select = agent_select;
        }

        public String getName_pinyin() {
            return name_pinyin;
        }

        public void setName_pinyin(String name_pinyin) {
            this.name_pinyin = name_pinyin;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public ReturnDataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.remark);
            dest.writeInt(this.is_gift);
            dest.writeInt(this.price);
            dest.writeString(this.number);
            dest.writeString(this.agent_code);
            dest.writeString(this.code);
            dest.writeString(this.card_code);
            dest.writeString(this.name);
            dest.writeString(this.userName);
            dest.writeString(this.produceTypeName);
            dest.writeString(this.create_time);
            dest.writeString(this.cardName);
            dest.writeString(this.user_code);
            dest.writeString(this.standard);
            dest.writeInt(this.isdel);
            dest.writeString(this.type_code);
            dest.writeString(this.unit);
            dest.writeString(this.procuctprice);
            dest.writeString(this.agent_name);
            dest.writeString(this.state);
            dest.writeString(this.agent_select);
            dest.writeString(this.name_pinyin);
            dest.writeInt(this.commission);
            dest.writeInt(this.count);
            dest.writeByte(this.isDiscount ? (byte) 1 : (byte) 0);
            dest.writeString(this.pepCode);
            dest.writeInt(this.actual_price);
        }

        protected ReturnDataBean(Parcel in) {
            this.remark = in.readString();
            this.is_gift = in.readInt();
            this.price = in.readInt();
            this.number = in.readString();
            this.agent_code = in.readString();
            this.code = in.readString();
            this.card_code = in.readString();
            this.name = in.readString();
            this.userName = in.readString();
            this.produceTypeName = in.readString();
            this.create_time = in.readString();
            this.cardName = in.readString();
            this.user_code = in.readString();
            this.standard = in.readString();
            this.isdel = in.readInt();
            this.type_code = in.readString();
            this.unit = in.readString();
            this.procuctprice = in.readString();
            this.agent_name = in.readString();
            this.state = in.readString();
            this.agent_select = in.readString();
            this.name_pinyin = in.readString();
            this.commission = in.readInt();
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
