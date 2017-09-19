package com.sk.meikelai.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class OpenCardBean {

    /**
     * return_data : [{"code":"905b9afbc3c544e18b20c5ad42c6b6cc","number":"11200","name":"修眉卡","type":0,"sale_price":1000,"gift_price":0,
     * "discount":1,"coefficient":1,"useful":12,"commission":0,"startTime":"2017-07-20","endTime":"2018-07-20",
     * "project_list":[{"project_name":"修眉","times":10,"project_code":"ab66376216e842969a3af5717befd7d2"}]},
     * {"code":"717bc5ca007142d5beac7e0f271ae30a","number":"11001","name":"超值甲油胶卡","type":0,"sale_price":100000,"gift_price":0,
     * "discount":1,"coefficient":1,"useful":24,"commission":15,"startTime":"2017-06-28","endTime":"2019-06-28",
     * "project_list":[{"project_name":"纯色光疗甲","times":10,"project_code":"141eb4402da34f55811e323380eb837c"}]},{"code":"1",
     * "number":"30002","name":"足部护理卡","type":0,"sale_price":100000,"gift_price":0,"discount":1,"coefficient":1,"useful":120,
     * "commission":15,"startTime":"2017-06-07","endTime":"2027-06-07","project_list":[{"project_name":"足部基础护理","times":10,
     * "project_code":"a7b24b341bf84088ae3c6e1d36651956"}]}]
     * return_code : 1
     * return_info : 获取卡信息成功！
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
         * code : 905b9afbc3c544e18b20c5ad42c6b6cc
         * number : 11200
         * name : 修眉卡
         * type : 0
         * sale_price : 1000
         * gift_price : 0
         * discount : 1
         * coefficient : 1
         * useful : 12
         * commission : 0
         * startTime : 2017-07-20
         * endTime : 2018-07-20
         * project_list : [{"project_name":"修眉","times":10,"project_code":"ab66376216e842969a3af5717befd7d2"}]
         */

        private String code;
        private String number;
        private String name;
        private int type;
        private int sale_price;
        private int gift_price;
        private int discount;
        private int coefficient;
        private int useful;
        private int commission;
        private String startTime;
        private String endTime;
        private List<ProjectListBean> project_list;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private boolean isSelected = false;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSale_price() {
            return sale_price;
        }

        public void setSale_price(int sale_price) {
            this.sale_price = sale_price;
        }

        public int getGift_price() {
            return gift_price;
        }

        public void setGift_price(int gift_price) {
            this.gift_price = gift_price;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(int coefficient) {
            this.coefficient = coefficient;
        }

        public int getUseful() {
            return useful;
        }

        public void setUseful(int useful) {
            this.useful = useful;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public List<ProjectListBean> getProject_list() {
            return project_list;
        }

        public void setProject_list(List<ProjectListBean> project_list) {
            this.project_list = project_list;
        }

        public static class ProjectListBean implements Parcelable {
            /**
             * project_name : 修眉
             * times : 10
             * project_code : ab66376216e842969a3af5717befd7d2
             */

            private String project_name;
            private int times;
            private String project_code;

            public String getProject_name() {
                return project_name;
            }

            public void setProject_name(String project_name) {
                this.project_name = project_name;
            }

            public int getTimes() {
                return times;
            }

            public void setTimes(int times) {
                this.times = times;
            }

            public String getProject_code() {
                return project_code;
            }

            public void setProject_code(String project_code) {
                this.project_code = project_code;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.project_name);
                dest.writeInt(this.times);
                dest.writeString(this.project_code);
            }

            public ProjectListBean() {
            }

            protected ProjectListBean(Parcel in) {
                this.project_name = in.readString();
                this.times = in.readInt();
                this.project_code = in.readString();
            }

            public static final Creator<ProjectListBean> CREATOR = new Creator<ProjectListBean>() {
                @Override
                public ProjectListBean createFromParcel(Parcel source) {
                    return new ProjectListBean(source);
                }

                @Override
                public ProjectListBean[] newArray(int size) {
                    return new ProjectListBean[size];
                }
            };
        }

        public ReturnDataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.number);
            dest.writeString(this.name);
            dest.writeInt(this.type);
            dest.writeInt(this.sale_price);
            dest.writeInt(this.gift_price);
            dest.writeInt(this.discount);
            dest.writeInt(this.coefficient);
            dest.writeInt(this.useful);
            dest.writeInt(this.commission);
            dest.writeString(this.startTime);
            dest.writeString(this.endTime);
            dest.writeList(this.project_list);
            dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        }

        protected ReturnDataBean(Parcel in) {
            this.code = in.readString();
            this.number = in.readString();
            this.name = in.readString();
            this.type = in.readInt();
            this.sale_price = in.readInt();
            this.gift_price = in.readInt();
            this.discount = in.readInt();
            this.coefficient = in.readInt();
            this.useful = in.readInt();
            this.commission = in.readInt();
            this.startTime = in.readString();
            this.endTime = in.readString();
            this.project_list = new ArrayList<ProjectListBean>();
            in.readList(this.project_list, ProjectListBean.class.getClassLoader());
            this.isSelected = in.readByte() != 0;
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
