package com.sk.meikelai.entity;

/**
 * Created by sk on 2017/8/16.
 */

public class gg {


    /**
     * deviceSerial : 449147067
     * channelNo : 1
     * permission : 1,1,0,0,0
     * shareTime : {"startTime":"00:00","endTime":"n00:00","sharePeriod":"0,1,2,3,4,5,6"}
     */

    private String deviceSerial;
    private int channelNo;
    private String permission;
    private ShareTimeBean shareTime;

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public ShareTimeBean getShareTime() {
        return shareTime;
    }

    public void setShareTime(ShareTimeBean shareTime) {
        this.shareTime = shareTime;
    }

    public static class ShareTimeBean {
        /**
         * startTime : 00:00
         * endTime : n00:00
         * sharePeriod : 0,1,2,3,4,5,6
         */

        private String startTime;
        private String endTime;
        private String sharePeriod;

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

        public String getSharePeriod() {
            return sharePeriod;
        }

        public void setSharePeriod(String sharePeriod) {
            this.sharePeriod = sharePeriod;
        }
    }
}
