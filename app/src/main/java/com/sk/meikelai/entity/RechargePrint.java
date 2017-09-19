package com.sk.meikelai.entity;

/**
 * Created by sk on 2017/8/22.
 */

public class RechargePrint {


    /**
     * return_code : 1
     * return_info : 检索成功！
     * return_data : {"brand_name":"江宁区","shop_name":"建邺万达店","order_code":"51503042201024585","order_time":"2017-08-18 15:43","person_name":"孙可","base_user_name":"用户淑珍","name":"充值","card_name":"八折卡-充值","recharge_money":10000,"gift_money":0,"actual_money":10000,"trade_type":"CASH","remark":"","shop_address":"建邺区江东中路98号","shop_telephone":"0519-83581444"}
     */

    private int return_code;
    private String return_info;
    private ReturnDataBean return_data;

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

    public ReturnDataBean getReturn_data() {
        return return_data;
    }

    public void setReturn_data(ReturnDataBean return_data) {
        this.return_data = return_data;
    }

    public static class ReturnDataBean {
        /**
         * brand_name : 江宁区
         * shop_name : 建邺万达店
         * order_code : 51503042201024585
         * order_time : 2017-08-18 15:43
         * person_name : 孙可
         * base_user_name : 用户淑珍
         * name : 充值
         * card_name : 八折卡-充值
         * recharge_money : 10000
         * gift_money : 0
         * actual_money : 10000
         * trade_type : CASH
         * remark :
         * shop_address : 建邺区江东中路98号
         * shop_telephone : 0519-83581444
         */

        private String brand_name;
        private String shop_name;
        private String order_code;
        private String order_time;
        private String person_name;
        private String base_user_name;
        private String name;
        private String card_name;
        private int card_balance;
        private String upgrade_card_name;
        private int recharge_money;
        private int gift_money;
        private int actual_money;
        private String trade_type;
        private String remark;
        private String shop_address;
        private String shop_telephone;


        public int getCard_balance() {
            return card_balance;
        }

        public void setCard_balance(int card_balance) {
            this.card_balance = card_balance;
        }
        public String getUpgrade_card_name() {
            return upgrade_card_name;
        }

        public void setUpgrade_card_name(String upgrade_card_name) {
            this.upgrade_card_name = upgrade_card_name;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public int getRecharge_money() {
            return recharge_money;
        }

        public void setRecharge_money(int recharge_money) {
            this.recharge_money = recharge_money;
        }

        public int getGift_money() {
            return gift_money;
        }

        public void setGift_money(int gift_money) {
            this.gift_money = gift_money;
        }

        public int getActual_money() {
            return actual_money;
        }

        public void setActual_money(int actual_money) {
            this.actual_money = actual_money;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
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
    }
}
