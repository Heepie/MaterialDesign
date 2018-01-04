package com.heepie.matrialdesign.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;

/**
 * Created by Heepie on 2018. 1. 3..
 */

public class Item implements Parcelable {
    @ColorRes
    private int colorResId;
    private String name;

    public Item(int colorResId, String name) {
        this.colorResId = colorResId;
        this.name = name;
    }

    public int getColorResId() {
        return colorResId;
    }

    public void setColorResId(int colorResId) {
        this.colorResId = colorResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.colorResId);
        dest.writeString(this.name);
    }

    protected Item(Parcel in) {
        this.colorResId = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
