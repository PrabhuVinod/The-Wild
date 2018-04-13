package com.prabhu.thewild.utils;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NatGeoPictures implements Serializable, Parcelable
{

    private String caption;
    private String credit;
    private String profileUrl;
    private String fullPathUrl;
    private String url;
    private String originalUrl;
    private Double aspectRatio;
    private Sizes sizes;
    private Boolean internal;
    public final static Parcelable.Creator<NatGeoPictures> CREATOR = new Creator<NatGeoPictures>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NatGeoPictures createFromParcel(Parcel in) {
            return new NatGeoPictures(in);
        }

        public NatGeoPictures[] newArray(int size) {
            return (new NatGeoPictures[size]);
        }

    }
            ;
    private final static long serialVersionUID = 158156662329839548L;

    protected NatGeoPictures(Parcel in) {
        this.caption = ((String) in.readValue((String.class.getClassLoader())));
        this.credit = ((String) in.readValue((String.class.getClassLoader())));
        this.profileUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.fullPathUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.originalUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.aspectRatio = ((Double) in.readValue((Double.class.getClassLoader())));
        this.sizes = ((Sizes) in.readValue((Sizes.class.getClassLoader())));
        this.internal = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public NatGeoPictures() {
    }

    /**
     *
     * @param sizes
     * @param originalUrl
     * @param profileUrl
     * @param fullPathUrl
     * @param aspectRatio
     * @param caption
     * @param credit
     * @param internal
     * @param url
     */
    public NatGeoPictures(String caption, String credit, String profileUrl, String fullPathUrl, String url, String originalUrl, Double aspectRatio, Sizes sizes, Boolean internal) {
        super();
        this.caption = caption;
        this.credit = credit;
        this.profileUrl = profileUrl;
        this.fullPathUrl = fullPathUrl;
        this.url = url;
        this.originalUrl = originalUrl;
        this.aspectRatio = aspectRatio;
        this.sizes = sizes;
        this.internal = internal;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getFullPathUrl() {
        return fullPathUrl;
    }

    public void setFullPathUrl(String fullPathUrl) {
        this.fullPathUrl = fullPathUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(caption);
        dest.writeValue(credit);
        dest.writeValue(profileUrl);
        dest.writeValue(fullPathUrl);
        dest.writeValue(url);
        dest.writeValue(originalUrl);
        dest.writeValue(aspectRatio);
        dest.writeValue(sizes);
        dest.writeValue(internal);
    }

    public int describeContents() {
        return 0;
    }

}
