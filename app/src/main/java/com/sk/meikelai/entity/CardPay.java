package com.sk.meikelai.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sk on 2017/7/19.
 */

public class CardPay implements Parcelable {

    String card_user_code;
    String card_total;
    String actual_total;
    String is_discount;

    public String getCard_user_code() {
        return card_user_code;
    }

    public void setCard_user_code(String card_user_code) {
        this.card_user_code = card_user_code;
    }

    public String getCard_total() {
        return card_total;
    }

    public void setCard_total(String card_total) {
        this.card_total = card_total;
    }

    public String getActual_total() {
        return actual_total;
    }

    public void setActual_total(String actual_total) {
        this.actual_total = actual_total;
    }

    public String getIs_discount() {
        return is_discount;
    }

    public void setIs_discount(String is_discount) {
        this.is_discount = is_discount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.card_user_code);
        dest.writeString(this.card_total);
        dest.writeString(this.actual_total);
        dest.writeString(this.is_discount);
    }

    public CardPay() {
    }

    protected CardPay(Parcel in) {
        this.card_user_code = in.readString();
        this.card_total = in.readString();
        this.actual_total = in.readString();
        this.is_discount = in.readString();
    }

    public static final Parcelable.Creator<CardPay> CREATOR = new Parcelable.Creator<CardPay>() {
        @Override
        public CardPay createFromParcel(Parcel source) {
            return new CardPay(source);
        }

        @Override
        public CardPay[] newArray(int size) {
            return new CardPay[size];
        }
    };
}
