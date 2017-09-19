package com.sk.meikelai.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by lenovo on 2017/7/13.
 */

public class FuMiBean
{


    /**
     * return_code : 1
     * return_info : 检索成功!
     * return_data : [{"code":"9","user_code":"mklshop","type":3,"order_main_code":"111","order_detail_code":"222","order_recharge_code":"333","subject_code":"444","card_code":"fccabe076298430a854abc77fcc1a2d8","card_user_code":"666","base_user_code":"123","commission":200,"cash_total":200,"achieve_total":20,"money_type":2,"isdel":0,"create_time":"2017-07-06 15:50:33.0","person_name":"美客来东山镇店","card_name":"","project_name":"SPA","produce_name":"","base_user_name":""}]
     */

    private int return_code;
    private String return_info;
    public List<ReturnDataBean> return_data;

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

    public static class ReturnDataBean implements MultiItemEntity {
        /**
         * code : 9
         * user_code : mklshop
         * type : 3
         * order_main_code : 111
         * order_detail_code : 222
         * order_recharge_code : 333
         * subject_code : 444
         * card_code : fccabe076298430a854abc77fcc1a2d8
         * card_user_code : 666
         * base_user_code : 123
         * commission : 200
         * cash_total : 200
         * achieve_total : 20
         * money_type : 2
         * isdel : 0
         * create_time : 2017-07-06 15:50:33.0
         * person_name : 美客来东山镇店
         * card_name :
         * project_name : SPA
         * produce_name :
         * base_user_name :
         */

        private String code;
        private String user_code;
        private int type;
        private String order_main_code;
        private String order_detail_code;
        private String order_recharge_code;
        private String subject_code;
        private String card_code;
        private String card_user_code;
        private String base_user_code;
        private int commission;
        private int cash_total;
        private int achieve_total;
        private int money_type;
        private int isdel;
        private String create_time;
        private String person_name;
        private String card_name;
        private String project_name;
        private String produce_name;
        private String base_user_name;
        private int itemType;
        private String mainCode;

        public String getMainCode() {
            return mainCode;
        }

        public void setMainCode(String mainCode) {
            this.mainCode = mainCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOrder_main_code() {
            return order_main_code;
        }

        public void setOrder_main_code(String order_main_code) {
            this.order_main_code = order_main_code;
        }

        public String getOrder_detail_code() {
            return order_detail_code;
        }

        public void setOrder_detail_code(String order_detail_code) {
            this.order_detail_code = order_detail_code;
        }

        public String getOrder_recharge_code() {
            return order_recharge_code;
        }

        public void setOrder_recharge_code(String order_recharge_code) {
            this.order_recharge_code = order_recharge_code;
        }

        public String getSubject_code() {
            return subject_code;
        }

        public void setSubject_code(String subject_code) {
            this.subject_code = subject_code;
        }

        public String getCard_code() {
            return card_code;
        }

        public void setCard_code(String card_code) {
            this.card_code = card_code;
        }

        public String getCard_user_code() {
            return card_user_code;
        }

        public void setCard_user_code(String card_user_code) {
            this.card_user_code = card_user_code;
        }

        public String getBase_user_code() {
            return base_user_code;
        }

        public void setBase_user_code(String base_user_code) {
            this.base_user_code = base_user_code;
        }

        public int getCommission() {
            return commission;
        }

        public void setCommission(int commission) {
            this.commission = commission;
        }

        public int getCash_total() {
            return cash_total;
        }

        public void setCash_total(int cash_total) {
            this.cash_total = cash_total;
        }

        public int getAchieve_total() {
            return achieve_total;
        }

        public void setAchieve_total(int achieve_total) {
            this.achieve_total = achieve_total;
        }

        public int getMoney_type() {
            return money_type;
        }

        public void setMoney_type(int money_type) {
            this.money_type = money_type;
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

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public String getProduce_name() {
            return produce_name;
        }

        public void setProduce_name(String produce_name) {
            this.produce_name = produce_name;
        }

        public String getBase_user_name() {
            return base_user_name;
        }

        public void setBase_user_name(String base_user_name) {
            this.base_user_name = base_user_name;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType){
            this.itemType = itemType;
        }
    }
}
