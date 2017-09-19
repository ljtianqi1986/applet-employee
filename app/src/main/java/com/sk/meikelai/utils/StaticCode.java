package com.sk.meikelai.utils;


public class StaticCode {
    //
    public static final int TAG = 0;
    public static final int TAG_SECOND = 1;
    public static final int TAG_CASH_LOGIN = 101;       //收银登录
    public static final int TAG_OPENCARD_LOGIN = 102;   //开卡登录
    public static final int TAG_RECHARGE_LOGIN = 103;   //充值登录
    public static final String RETURN_CODE = "return_code";
    public static final String RETURN_DATA = "return_data";
    public static final String RETURN_INFO = "return_info";
    public static final String RETURN_LIST = "list";
    public static final String VALUE = "value";
    public static final String ERROR_INFO = "return_info";
    public static final int SUCCESS_REQUEST = 1;
    public static final int SERVER_DATA_ERROR = 501;
    public static final String SP_VIP_DATA = "sp_vip_data";
    public static final String SP_USER_DATA = "sp_user_data";
    public static final String SP_WORKER_DATA = "sp_worker_data";
    public static final String TAB_ONCE_PRODUCT = "tab_once_product";
    public static final String SELECT_PRODUCT = "select_product";


    //参数名
    public static final String PHONE = "phone";


    //登陆
    static final String URL_LOGIN = "phone_merchant_login.do";

    //获取卡信息
    static final String Get_Card_List = "phone_getCardList.do";

    //开卡，充值，升级卡接口
    static final String OPEN_CARD = "phone_createCard.do";

    //开卡充值再次验证接口
    static final String Query_Card_Order_Again = "phone_queryCardOrderAgain.do";

    //加载会员次年卡项目接口
    static final String Get_Vip_Card_Detais = "phone_getUserCardProjectMoreDetail.do";

    //加载服务项目接口
    static final String Load_Project_From_Phone = "phone_loadProjectFromPhone.do";

    //加载产品接口
    static final String Load_Produce_From_Phone = "phone_loadProduceFromPhone.do";

    //用户修改手机号接口
    static final String Change_Phone_Number = "phone_updatePhoneNum.do";

    //技师修改密码接口
    static final String Change_Password = "phone_resetPwd.do";

    //获取技师列表
    static final String Get_All_Artificer = "phone_findAllSysUserInShopByLoginIdentityCode.do";

    //技师端 “我的” 首页接口
    static final String Get_Home_Count = "phone_sysUserHomeStatistics.do";

    //用户移动技师端 “我的” 今日预约接口
    static final String Get_My_Order_List = "phone_sysUserHomeAppointment.do";

    //用户移动技师端 “我的” 查询技师对应会员数据接口
    static final String Get_My_Vip_List = "phone_sysUserHomeMember.do";

    //用户移动技师端 “我的” 查询技师今日现金交接申报记录
    static final String Get_Money_Apply_Record = "phone_sysUserHomeCashDeclare.do";

    //用户移动技师端 “我的” 查询技师当月顾客满意度
    static final String Get_My_Score = "phone_sysUserHomeSatisfactionDegree.do";

    //用户移动技师端 “我的” 查询今日实收环装图查询接口
    static final String Get_Cash_Statistics = "phone_sysUserHomeCash.do";

    //用户移动技师端 “我的” 查询今日业绩环装图查询接口
    static final String Get_Achievement_Statistics = "phone_sysUserShopPerformanceCircleMap.do";

    //用户”我的”福米查询接口
    static final String Get_My_FuMi = "phone_sysUserHomeFumi.do";

    //用户"我的"本月福米统计查询接口
    static final String Get_Month_FuMi = "phone_sysUserHomeMonthFumi.do";

    //会员扫码登录
    static final String Member_Login = "phone_getBaseUserByUnionCode.do";

    //会员扫码登录
    static final String Get_Verification_Code = "phone_sendMsg.do";

    //订单流水接口
    static final String Get_My_Order_Flow = "phone_queryOrderList.do";

    //加载会员次年卡项目接口
    static final String Get_Once_Card = "phone_getUserCardProjectMoreDetail.do";

    //加载服务项目接口
    static final String Get_Service_Card = "phone_loadProjectFromPhone.do";

    //加载产品接口
    static final String Get_Goods_Card = "phone_loadProduceFromPhone.do";

    //根据门店code获取产品类型列表
    static final String Get_Product_List_By_Code = "phone_getAllProduceTypeListByShopCode.do";

    //根据门店code获取项目类型列表
    static final String Get_Project_List_By_Code = "phone_getAllProjectTypeListByShopCode.do";

    //获取储值卡列表
    static final String Get_Save_Card_List = "phone_getCardBalanceOfUser.do";

    //获取开卡种类列表
    static final String Get_Open_Card_List = "phone_getCardList.do";

    //获取技师列表
    static final String Get_Worker_List = "phone_findAllSysUserInShopByLoginIdentityCode.do";

    //开卡，充值，升级卡接口
    static final String Create_Card = "phone_createCard.do";

    //获取储值卡剩余金额接口
    static final String Get_Card_Balance = "phone_getCardBalanceOfUser.do";
	
	//消费
    static final String Pay_Order = "phone_createOrder.do";

    //技师列表
    static final String Get_People_List = "phone_findAllSysUserInShopByLoginIdentityCode.do";


    //获取会员二维码
    static final String Get_QD_Code = "phone_toBeVipMember.do";

    //获取到店方式
    static final String Get_Come_Way = "phone_getBaseUserComeWay.do";


    //今日订单审核记录
    static final String Get_Check_Order = "phone_queryOrderAuditCommit.do";

    //提交订单审核接口
    static final String Apply_Order_Check = "phone_orderAuditCommit.do";

    //判断今日是否提交
    static final String Is_Already_Apply = "phone_checkIsCommitOrder.do";

    //获取今日交接信息
    static final String Get_Today_Hand_Info = "phone_sysUserHomeCreateCashDeclare.do";

    //用户现金交接数据保存接口
    static final String Save_Cash_Record = "phone_saveSysUserHomeCashDeclare.do";

    //用户现金交接记录
    static final String Cash_Record_List = "phone_sysUserHomeCashDeclare.do";


    //消费再次验证
    static final String Again_Check_Order = "phone_queryCustomOrderAgain.do";

    //获取可升级到的卡
    static final String Get_Update_Card_List = "phone_sysUserUpdateCardKinds.do";

    //加载卡类型查询接口
    static final String Get_Search_List = "phone_searchSysUserCardsType.do";

    //获取会员登录信息
    static final String Get_Member_Info = "phone_getBaseUserByUserCode.do";

    //上传图片
   public static final String Up_Load_Img = "phone_upload.do";

    //修改技师头像
    public static final String Change_Head = "phone_sysUserSavePicUrl.do";

    //获取会员已有卡的查询接口
    public static final String Find_Repeat_Card = "phone_findCardsNumsForBasuUser.do";

    //开卡充值再次验证接口
    public static final String Query_Order_Again = "phone_queryCardOrderAgain.do";

    //小票查询接口
    public static final String Query_Print_Info = "phone_getTicketPrinterNews.do";
	
    //上传报错信息
    public static final String Upload_Error = "phone_upload_error.do";

    //获取门店口令
    public static final String Get_Shop_Code = "phone_getShopCountersign.do";

}
