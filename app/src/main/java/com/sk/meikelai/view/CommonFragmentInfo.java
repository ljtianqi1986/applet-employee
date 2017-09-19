package com.sk.meikelai.view;


import com.sk.meikelai.R;
import com.sk.meikelai.activity.mine.HandOverFragment;
import com.sk.meikelai.activity.mine.TodayAppointmentFragment;

public enum CommonFragmentInfo {


    HANDOVER(1, R.drawable.title_bar_back, 0, R.string.mine_today_handover, HandOverFragment.class),
    TODAYAPPOINTMENT(4, R.drawable.title_bar_back, 0, R.string.mine_today_appointment, TodayAppointmentFragment.class);
//    CONFIRM(5, R.drawable.title_bar_back, 0, R.string.card_open_card, ConfirmFragment.class),
//    WORKERLOGIN(0, R.drawable.title_bar_back, 0, R.string.login, WorkerLoginFragment.class);


    private int mKey;
    private int mTitle;
    private int mLeftIcon;
    private int mRightIcon;
    private Class<?> mClz;

    CommonFragmentInfo(int key, int leftIcon, int rightIcon, int title, Class<?> clz) {
        this.mKey = key;
        this.mLeftIcon = leftIcon;
        this.mRightIcon = rightIcon;
        this.mTitle = title;
        this.mClz = clz;
    }

    public int getmKey() {
        return mKey;
    }

    public void setmKey(int mKey) {
        this.mKey = mKey;
    }

    public static CommonFragmentInfo getCommonFragmentBeanByKey(int key) {
        for (CommonFragmentInfo commonFragmentInfo : values()) {
            if (commonFragmentInfo.getmKey() == key) {
                return commonFragmentInfo;
            }
        }
        return null;
    }

    public int getmTitle() {
        return mTitle;
    }

    public void setmTitle(int mTitle) {
        this.mTitle = mTitle;
    }

    public int getmLeftIcon() {
        return mLeftIcon;
    }

    public void setmLeftIcon(int mLeftIcon) {
        this.mLeftIcon = mLeftIcon;
    }

    public int getmRightIcon() {
        return mRightIcon;
    }

    public void setmRightIcon(int mRightIcon) {
        this.mRightIcon = mRightIcon;
    }

    public Class<?> getmClz() {
        return mClz;
    }

    public void setmClz(Class<?> mClz) {
        this.mClz = mClz;
    }
}
