package com.sk.meikelai.utils;


import com.alibaba.fastjson.JSON;
import com.sk.meikelai.entity.CreateCardBean;

import java.util.HashMap;
import java.util.Map;

public class AppApi {

    //登录
    public static void login(String workername, String password, String device_ver, String device_info, String device_ip,
                             HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("login_name", workername);
        map.put("pwd", MD5.md5(password));
        map.put("device_ver", device_ver);
        map.put("device_info", device_info);
        map.put("device_ip", device_ip);
        map.put("key", MD5.md5(workername + MD5.md5(password) + device_ver + device_info + device_ip));
        HttpUtils.getData(StaticCode.URL_LOGIN, map, callBack, tag);
    }

    //自动登录
    public static void autogin(String workername, String password, String device_ver, String device_info, String device_ip,
                               HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("login_name", workername);
        map.put("pwd", password);
        map.put("device_ver", device_ver);
        map.put("device_info", device_info);
        map.put("device_ip", device_ip);
        map.put("key", MD5.md5(workername + password + device_ver + device_info + device_ip));
        HttpUtils.getData(StaticCode.URL_LOGIN, map, callBack, tag);
    }

    //获取卡信息
    public static void getCardList(String type, String type_code, String name, String name_pinyin, String number, String page,
                                   HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("type_code", type_code);
        map.put("name", name);
        map.put("name_pinyin", name_pinyin);
        map.put("number", number);
        map.put("page", page);
        HttpUtils.getData(StaticCode.Get_Card_List, map, callBack, tag);
    }

    //开卡，充值，升级卡
    public static void openCard(String jsonString, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("jsonString", jsonString);
        HttpUtils.getData(StaticCode.OPEN_CARD, map, callBack, tag);
    }

    //开卡充值再次验证接口
    public static void querCardOrderAgain(String order_code, String user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_code", order_code);
        map.put("user_code", user_code);
        map.put("key", MD5.md5(order_code + user_code));
        HttpUtils.getData(StaticCode.Query_Card_Order_Again, map, callBack, tag);
    }

    //加载会员次年卡项目接口
    public static void getVipCardDetais(String user_code, String type, String type_code, String min_price, String max_price,
                                        String query_word, String page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("type", type);
        map.put("type_code", type_code);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(user_code + type + page));
        HttpUtils.postData(StaticCode.Get_Vip_Card_Detais, map, callBack, tag);
    }

    //加载会员次年卡项目接口
    public static void loadProjectFromPhone(String sys_user_code, String type_code, String min_price, String max_price, String query_word,
                                            String page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("type_code", type_code);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code));
        HttpUtils.postData(StaticCode.Load_Project_From_Phone, map, callBack, tag);
    }

    //加载产品接口
    public static void loadProduceFromPhone(String sys_user_code, String type_code, String min_price, String max_price, String query_word,
                                            String page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("type_code", type_code);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code));
        HttpUtils.postData(StaticCode.Load_Produce_From_Phone, map, callBack, tag);
    }

    //用户修改手机号接口
    public static void changePhoneNumber(String user_code, String phone, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("phone", phone);
        map.put("key", MD5.md5(user_code + phone));
        HttpUtils.postData(StaticCode.Change_Phone_Number, map, callBack, tag);
    }

    //技师修改密码接口
    public static void changePassword(String user_code, String pwd, String new_pwd, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("pwd", MD5.md5(pwd));
        map.put("new_pwd", MD5.md5(new_pwd));
        map.put("key", MD5.md5(user_code + MD5.md5(pwd) + MD5.md5(new_pwd)));
        HttpUtils.postData(StaticCode.Change_Password, map, callBack, tag);
    }

    //获取技师列表
    public static void getAllArtificer(String identity_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("identity_code", identity_code);
        map.put("key", MD5.md5(identity_code));
        HttpUtils.postData(StaticCode.Get_All_Artificer, map, callBack, tag);
    }

    //技师端 “我的” 首页接口
    public static void getHomeCount(String sys_user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("key", MD5.md5(sys_user_code));
        map.put("isCash", 1);
        HttpUtils.postData(StaticCode.Get_Home_Count, map, callBack, tag);
    }

    //用户移动技师端 “我的” 今日预约接口
    public static void getMyOrderList(String sys_user_code, String type, String page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("type", type);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code + type + page));
        HttpUtils.postData(StaticCode.Get_My_Order_List, map, callBack, tag);
    }

    //用户移动技师端 “我的” 查询技师对应会员数据接口
    public static void getMyVipList(String search_name, String sys_user_code, String type, String page, HandleDataCallBack callBack, int
            tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("search_name", search_name);
        map.put("sys_user_code", sys_user_code);
        map.put("type", type);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code + type + page));
        HttpUtils.postData(StaticCode.Get_My_Vip_List, map, callBack, tag);
    }

    //用户移动技师端 “我的” 查询技师今日现金交接申报记录
    public static void getMoneyApplyRecord(String search_name, String sys_user_code, String type, String page, HandleDataCallBack
            callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("search_name", search_name);
        map.put("sys_user_code", sys_user_code);
        map.put("type", type);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code + type + page));
        HttpUtils.postData(StaticCode.Get_Money_Apply_Record, map, callBack, tag);
    }

    //用户移动技师端 “我的” 查询技师当月顾客满意度
    public static void getMyScore(String sys_user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("key", MD5.md5(sys_user_code));
        HttpUtils.postData(StaticCode.Get_My_Score, map, callBack, tag);
    }

    //用户移动技师端 “我的” 查询今日实收环装图查询接口
    public static void getCashStatistics(String sys_user_code, String search_type, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("search_type", search_type);
        map.put("isCash", 1);
        map.put("key", MD5.md5(sys_user_code + search_type));
        HttpUtils.postData(StaticCode.Get_Cash_Statistics, map, callBack, tag);
    }

    //用户移动技师端 “我的” 查询今日业绩环装图查询接口
    public static void getAchievementStatistics(String sys_user_code, String search_type, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("search_type", search_type);
        map.put("isCash", 1);
        map.put("key", MD5.md5(sys_user_code + search_type));
        HttpUtils.postData(StaticCode.Get_Achievement_Statistics, map, callBack, tag);
    }

    //用户”我的”福米查询接口
    public static void getMyFuMi(String sys_user_code, String type, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("type", type);
        map.put("key", MD5.md5(sys_user_code + type));
        HttpUtils.postData(StaticCode.Get_My_FuMi, map, callBack, tag);
    }

    //用户"我的"本月福米统计查询接口
    public static void getMonthFuMi(String sys_user_code, String search_time, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("key", MD5.md5(sys_user_code));
        map.put("search_time", search_time);
        HttpUtils.postData(StaticCode.Get_Month_FuMi, map, callBack, tag);
    }


    //用户”我的”订单流水
    public static void getMyOrderFlow(String user_code, String page, String beginDate, String endDate, int isCash, HandleDataCallBack
            callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("page", page);
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        if (isCash > 0) {
            map.put("isCash", isCash);
        }
        map.put("key", MD5.md5(user_code + page));
        HttpUtils.getData(StaticCode.Get_My_Order_Flow, map, callBack, tag);
    }

    //会员扫码登录
    public static void memberLogin(String member_id, String shop_id, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("union_code", member_id);
        map.put("shop_code", shop_id);
        map.put("key", MD5.md5(shop_id));
        HttpUtils.getData(StaticCode.Member_Login, map, callBack, tag);
    }


    //会员手机登录
    public static void memberPhoneLogin(String phone,String shop_id, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("shop_code", shop_id);
        map.put("key", MD5.md5(shop_id));
        HttpUtils.getData(StaticCode.Member_Login, map, callBack, tag);
    }


    //发送验证码
    public static void getVerificationCode(String phone, String date, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("key", MD5.md5(phone + date));
        HttpUtils.getData(StaticCode.Get_Verification_Code, map, callBack, tag);
    }

    //加载会员次年卡项目
    public static void getOnceCard(String user_code, String shop_code, String type_code, String min_price, String max_price, String
            query_word, int page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("shop_code", shop_code);
        map.put("type_code", type_code);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(user_code + page));

        HttpUtils.postData(StaticCode.Get_Once_Card, map, callBack, tag);
    }

    //加载服务项目接口
    public static void getServiceCard(String sys_user_code, String type_code, String min_price, String max_price, String query_word, int
            page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("type_code", type_code);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code));

        HttpUtils.postData(StaticCode.Get_Service_Card, map, callBack, tag);
    }

    //加载产品接口
    public static void getGoodsCard(String sys_user_code, String type_code, String min_price, String max_price, String query_word, int
            page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("type_code", type_code);
        map.put("min_price", min_price);
        map.put("max_price", max_price);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code));
        HttpUtils.postData(StaticCode.Get_Goods_Card, map, callBack, tag);
    }

    //根据门店code获取产品类型列表
    public static void getProductByCode(String shop_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("shop_code", shop_code);
        map.put("key", MD5.md5(shop_code));
        HttpUtils.postData(StaticCode.Get_Product_List_By_Code, map, callBack, tag);
    }

    //根据门店code获取项目类型列表
    public static void getPrpjectByCode(String shop_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("shop_code", shop_code);
        map.put("key", MD5.md5(shop_code));
        HttpUtils.postData(StaticCode.Get_Project_List_By_Code, map, callBack, tag);
    }

    //获取用户储值卡接口
    public static void getSaveCardList(String shop_code, String user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("key", MD5.md5(user_code));
        map.put("shop_code", shop_code);
        HttpUtils.postData(StaticCode.Get_Save_Card_List, map, callBack, tag);
    }

    //获取开卡种类接口
    public static void getOpenCardList(String shop_code, String type, String type_code, String query_word, int page, HandleDataCallBack
            callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("shop_code", shop_code);
        map.put("type", type);
        map.put("type_code", type_code);
        map.put("query_word", query_word);
        map.put("page", page);
        map.put("key", MD5.md5(shop_code + page + type));
        HttpUtils.getData(StaticCode.Get_Open_Card_List, map, callBack, tag);
    }

    //消费接口
    public static void payOrder(String jsonString, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("jsonString", jsonString);
        HttpUtils.postData(StaticCode.Pay_Order, map, callBack, tag);
    }

    //获取技师列表
    public static void getPeopleList(String identity_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("identity_code", identity_code);
        map.put("key", MD5.md5(identity_code));
        HttpUtils.postData(StaticCode.Get_People_List, map, callBack, tag);
    }

    //获取技师列表
    public static void getWorkerList(String shop_id, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("identity_code", shop_id);
        map.put("key", MD5.md5(shop_id));
        HttpUtils.postData(StaticCode.Get_Worker_List, map, callBack, tag);
    }

    //开卡充值
    public static void createCard(CreateCardBean.JsonStringBean cardBean, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("jsonString", JSON.toJSONString(cardBean));
        HttpUtils.getData(StaticCode.Create_Card, map, callBack, tag);
    }

    //获取储值卡列表
    public static void getCardBalance(String shop_code, String user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("key", MD5.md5(user_code));
        map.put("shop_code", shop_code);
        HttpUtils.getData(StaticCode.Get_Card_Balance, map, callBack, tag);
    }


    //开卡获取新用户二维码地址
    public static void getQdCode(String person_name, String phone, String come_way, String user_code, String shop_code, String
            invite_phone, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("person_name", person_name);
        map.put("phone", phone);
        map.put("come_way", come_way);
        map.put("user_code", user_code);
        map.put("shop_code", shop_code);
        map.put("invite_phone", invite_phone);
        map.put("key", MD5.md5(user_code + shop_code + phone));
        HttpUtils.getData(StaticCode.Get_QD_Code, map, callBack, tag);
    }

    //开卡获取新用户二维码地址
    public static void getComeWay(String shop_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("shop_code", shop_code);
        map.put("key", MD5.md5(shop_code));
        HttpUtils.getData(StaticCode.Get_Come_Way, map, callBack, tag);
    }


    //获取今日订单审核记录
    public static void getCheckOrder(String user_code, String date, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("date", date);
        map.put("key", MD5.md5(user_code + date));
        HttpUtils.getData(StaticCode.Get_Check_Order, map, callBack, tag);
    }

    //提交订单审核接口
    public static void applyOrderCheck(String remark, String user_code, int isClean, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("remark", remark);
        map.put("user_code", user_code);
        map.put("isClean", isClean);
        map.put("key", MD5.md5(user_code));
        HttpUtils.getData(StaticCode.Apply_Order_Check, map, callBack, tag);
    }

    //是否提交过
    public static void isAlreadyApply(String user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("key", MD5.md5(user_code));
        HttpUtils.getData(StaticCode.Is_Already_Apply, map, callBack, tag);
    }

    //获取今日交接信息
    public static void getToadyHandInfo(String sys_user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("key", MD5.md5(sys_user_code));
        HttpUtils.getData(StaticCode.Get_Today_Hand_Info, map, callBack, tag);
    }

    //用户现金交接数据保存接口
    public static void saveCashRecord(String sys_user_code, int yes_imprest, int today_cash, int today_imprest, int ought_connect,
                                      int reality_connect, String file_url, String remark, String declare_time, HandleDataCallBack
                                              callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("yes_imprest", yes_imprest);
        map.put("today_cash", today_cash);
        map.put("today_imprest", today_imprest);
        map.put("ought_connect", ought_connect);
        map.put("reality_connect", reality_connect);
        map.put("file_url", file_url);
        map.put("remark", remark);
        map.put("declare_time", declare_time);
        map.put("key", MD5.md5((sys_user_code + yes_imprest + today_cash + today_imprest + ought_connect + reality_connect +
                declare_time)));
        HttpUtils.getData(StaticCode.Save_Cash_Record, map, callBack, tag);
    }

    //用户现金交接记录
    public static void cashRecordList(String sys_user_code, int page, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("page", page);
        map.put("key", MD5.md5(sys_user_code + page));
        HttpUtils.getData(StaticCode.Cash_Record_List, map, callBack, tag);
    }


    //消费再次验证
    public static void againCheckOrder(String order_code, String user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_code", order_code);
        map.put("user_code", user_code);
        map.put("key", MD5.md5(order_code + user_code));
        HttpUtils.getData(StaticCode.Again_Check_Order, map, callBack, tag);
    }

    //移动技师端升级卡时选择高级卡的查询接口
    public static void getUpdateCardList(String sys_user_code, String card_code, String base_user_code, HandleDataCallBack callBack, int
            tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("card_code", card_code);
        map.put("base_user_code", base_user_code);
        map.put("key", MD5.md5(sys_user_code + card_code + base_user_code));
        HttpUtils.postData(StaticCode.Get_Update_Card_List, map, callBack, tag);
    }

    //加载卡类型查询接口(新)
    public static void getSearchList(String sys_user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("key", MD5.md5(sys_user_code));
        HttpUtils.postData(StaticCode.Get_Search_List, map, callBack, tag);
    }

    //获取会员信息
    public static void getMemberInfo(String member_id, String shop_id, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", member_id);
        map.put("shop_code", shop_id);
        map.put("key", MD5.md5(member_id + shop_id));
        HttpUtils.getData(StaticCode.Get_Member_Info, map, callBack, tag);
    }

    //修改技师头像
    public static void changeHead(String sys_user_code, String pic_url, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("sys_user_code", sys_user_code);
        map.put("pic_url", pic_url);
        map.put("key", MD5.md5(sys_user_code + pic_url));
        HttpUtils.getData(StaticCode.Change_Head, map, callBack, tag);
    }

    //获取会员已有卡的查询接口
    public static void findRepeatCard(String base_user_code, String card_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("base_user_code", base_user_code);
        map.put("card_code", card_code);
        map.put("key", MD5.md5(base_user_code + card_code));
        HttpUtils.postData(StaticCode.Find_Repeat_Card, map, callBack, tag);
    }

    //开卡充值再次验证接口
    public static void queryOrderAgain(String order_code, String user_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_code", order_code);
        map.put("user_code", user_code);
        map.put("key", MD5.md5(order_code + user_code));
        HttpUtils.postData(StaticCode.Query_Order_Again, map, callBack, tag);
    }

    //获取打印信息
    public static void queryPrintInfo(String order_code, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_code", order_code);
        map.put("key", MD5.md5(order_code));
        HttpUtils.postData(StaticCode.Query_Print_Info, map, callBack, tag);
    }


    //获取门店口令
    public static void getShopCode(String user_code	, HandleDataCallBack callBack, int tag) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", user_code);
        map.put("key", MD5.md5(user_code));
        HttpUtils.postData(StaticCode.Get_Shop_Code, map, callBack, tag);
    }

 
}
