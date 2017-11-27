package com.moon.samples.messenger_ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * author: moon
 * created on: 17/11/27 下午3:30
 * description:
 */
public class MessengerIPCBean implements Serializable {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.id);
//        dest.writeString(this.name);
//    }
//
//    public MessengerIPCBean() {
//    }
//
//    protected MessengerIPCBean(Parcel in) {
//        this.id = in.readInt();
//        this.name = in.readString();
//    }
//
//    public static final Parcelable.Creator<MessengerIPCBean> CREATOR = new Parcelable.Creator<MessengerIPCBean>() {
//        @Override
//        public MessengerIPCBean createFromParcel(Parcel source) {
//            return new MessengerIPCBean(source);
//        }
//
//        @Override
//        public MessengerIPCBean[] newArray(int size) {
//            return new MessengerIPCBean[size];
//        }
//    };
}
