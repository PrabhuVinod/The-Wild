package com.prabhu.thewild.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sizes implements Serializable, Parcelable {

    public final static Parcelable.Creator<Sizes> CREATOR = new Creator<Sizes>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sizes createFromParcel(Parcel in) {
            return new Sizes(in);
        }

        public Sizes[] newArray(int size) {
            return (new Sizes[size]);
        }

    };
    private final static long serialVersionUID = -7823396505202494427L;
    @SerializedName("240")
    private String _240;
    @SerializedName("320")
    private String _320;
    @SerializedName("500")
    private String _500;
    @SerializedName("640")
    private String _640;
    @SerializedName("800")
    private String _800;
    @SerializedName("1024")
    private String _1024;
    @SerializedName("1600")
    private String _1600;
    @SerializedName("2048")
    private String _2048;

    protected Sizes(Parcel in) {
        this._240 = ((String) in.readValue((String.class.getClassLoader())));
        this._320 = ((String) in.readValue((String.class.getClassLoader())));
        this._500 = ((String) in.readValue((String.class.getClassLoader())));
        this._640 = ((String) in.readValue((String.class.getClassLoader())));
        this._800 = ((String) in.readValue((String.class.getClassLoader())));
        this._1024 = ((String) in.readValue((String.class.getClassLoader())));
        this._1600 = ((String) in.readValue((String.class.getClassLoader())));
        this._2048 = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Sizes() {
    }

    /**
     * @param _1600
     * @param _2048
     * @param _320
     * @param _240
     * @param _1024
     * @param _800
     * @param _500
     * @param _640
     */
    public Sizes(String _240, String _320, String _500, String _640, String _800, String _1024, String _1600, String _2048) {
        super();
        this._240 = _240;
        this._320 = _320;
        this._500 = _500;
        this._640 = _640;
        this._800 = _800;
        this._1024 = _1024;
        this._1600 = _1600;
        this._2048 = _2048;
    }

    public String get240() {
        return _240;
    }

    public void set240(String _240) {
        this._240 = _240;
    }

    public String get320() {
        return _320;
    }

    public void set320(String _320) {
        this._320 = _320;
    }

    public String get500() {
        return _500;
    }

    public void set500(String _500) {
        this._500 = _500;
    }

    public String get640() {
        return _640;
    }

    public void set640(String _640) {
        this._640 = _640;
    }

    public String get800() {
        return _800;
    }

    public void set800(String _800) {
        this._800 = _800;
    }

    public String get1024() {
        return _1024;
    }

    public void set1024(String _1024) {
        this._1024 = _1024;
    }

    public String get1600() {
        return _1600;
    }

    public void set1600(String _1600) {
        this._1600 = _1600;
    }

    public String get2048() {
        return _2048;
    }

    public void set2048(String _2048) {
        this._2048 = _2048;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_240);
        dest.writeValue(_320);
        dest.writeValue(_500);
        dest.writeValue(_640);
        dest.writeValue(_800);
        dest.writeValue(_1024);
        dest.writeValue(_1600);
        dest.writeValue(_2048);
    }

    public int describeContents() {
        return 0;
    }

}