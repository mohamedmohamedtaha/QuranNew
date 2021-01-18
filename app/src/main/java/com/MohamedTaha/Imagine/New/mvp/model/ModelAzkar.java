package com.MohamedTaha.Imagine.New.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.inject.Inject;

public class ModelAzkar implements Parcelable {
    protected ModelAzkar(Parcel in) {
        name_azkar = in.readString();
        describe_azkar = in.readString();
        position = in.readInt();
    }


    public static final Creator<ModelAzkar> CREATOR = new Creator<ModelAzkar>() {
        @Override
        public ModelAzkar createFromParcel(Parcel in) {
            return new ModelAzkar(in);
        }

        @Override
        public ModelAzkar[] newArray(int size) {
            return new ModelAzkar[size];
        }
    };

    @Inject
    public ModelAzkar() {

    }

    public String getName_azkar() {
        return name_azkar;
    }

    public void setName_azkar(String name_azkar) {
        this.name_azkar = name_azkar;
    }

    private String name_azkar;

    private String describe_azkar;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescribe_azkar() {
        return describe_azkar;
    }

    public void setDescribe_azkar(String describe_azkar) {
        this.describe_azkar = describe_azkar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name_azkar);
        dest.writeString(describe_azkar);
        dest.writeInt(position);
    }
}
