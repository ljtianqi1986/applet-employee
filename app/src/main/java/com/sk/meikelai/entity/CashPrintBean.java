package com.sk.meikelai.entity;

import java.util.List;

/**
 * Created by sk on 2017/8/21.
 */

public class CashPrintBean {


    /**
     * return_info : 检索成功！
     * return_code : 1
     * return_data : {"brand_name":"邗江区","shop_name":"花-扬州店","order_code":"61502969875590471","order_time":"2017-08-17 19:37","person_name":"花-收银","base_user_name":"汤露露","detail_list":[{"consume_name":"花妆台-手指甲","price":3000,"minor_total":3000},{"price":3000,"minor_total":3000,"consume_name":"花妆台-手指甲"}],"order_total":81000,"no_discount_total":70500,"ckNums":2,"nkNums":1,"card_total":10500,"trade_type":"CASH","cash_total":1000,"remark":"","shop_address":"扬州东站","shop_telephone":"13888888888"}
     */

    private String return_info;
    private int return_code;
    private ReturnDataBean return_data;

    public String getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public ReturnDataBean getReturn_data() {
        return return_data;
    }

    public void setReturn_data(ReturnDataBean return_data) {
        this.return_data = return_data;
    }

    public static class ReturnDataBean {
        /**
         * brand_name : 邗江区
         * shop_name : 花-扬州店
         * order_code : 61502969875590471
         * order_time : 2017-08-17 19:37
         * person_name : 花-收银
         * base_user_name : 汤露露
         * detail_list : [{"consume_name":"花妆台-手指甲","price":3000,"minor_total":3000},{"price":3000,"minor_total":3000,"consume_name":"花妆台-手指甲"}]
         * order_total : 81000
         * no_discount_total : 70500
         * ckNums : 2
         * nkNums : 1
         * card_total : 10500
         * trade_type : CASH
         * cash_total : 1000
         * remark :
         * shop_address : 扬州东站
         * shop_telephone : 13888888888
         */

        private String brand_name;
        private String shop_name;
        private String order_code;
        private String order_time;
        private String person_name;
        private String base_user_name;
        private int order_total;
        private int no_discount_total;
        private int ckNums;
        private int nkNums;
        private int card_total;
        private String trade_type;
        private int cash_total;
        private String remark;
        private String shop_address;
        private String shop_telephone;
        private List<DetailListBean> detail_list;

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getOrder_code() {
            return order_code;
        }

        public void setOrder_code(String order_code) {
            this.order_code = order_code;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getBase_user_name() {
            return base_user_name;
        }

        public void setBase_user_name(String base_user_name) {
            this.base_user_name = base_user_name;
        }

        public int getOrder_total() {
            return order_total;
        }

        public void setOrder_total(int order_total) {
            this.order_total = order_total;
        }

        public int getNo_discount_total() {
            return no_discount_total;
        }

        public void setNo_discount_total(int no_discount_total) {
            this.no_discount_total = no_discount_total;
        }

        public int getCkNums() {
            return ckNums;
        }

        public void setCkNums(int ckNums) {
            this.ckNums = ckNums;
        }

        public int getNkNums() {
            return nkNums;
        }

        public void setNkNums(int nkNums) {
            this.nkNums = nkNums;
        }

        public int getCard_total() {
            return card_total;
        }

        public void setCard_total(int card_total) {
            this.card_total = card_total;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public int getCash_total() {
            return cash_total;
        }

        public void setCash_total(int cash_total) {
            this.cash_total = cash_total;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getShop_telephone() {
            return shop_telephone;
        }

        public void setShop_telephone(String shop_telephone) {
            this.shop_telephone = shop_telephone;
        }

        public List<DetailListBean> getDetail_list() {
            return detail_list;
        }

        public void setDetail_list(List<DetailListBean> detail_list) {
            this.detail_list = detail_list;
        }

        public static class DetailListBean {
            /**
             * consume_name : 花妆台-手指甲
             * price : 3000
             * minor_total : 3000
             */

            private String consume_name;
            private int price;
            private int minor_total;

            public String getConsume_name() {
                return consume_name;
            }

            public void setConsume_name(String consume_name) {
                this.consume_name = consume_name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getMinor_total() {
                return minor_total;
            }

            public void setMinor_total(int minor_total) {
                this.minor_total = minor_total;
            }
        }
    }
}
