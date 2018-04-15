package com.prabhu.thewild.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thumbnail implements Serializable {

    private final static long serialVersionUID = -1420670207777116643L;
    private String url;
    private Double aspectRatio;
    private Integer height;
    private Integer width;
    private String mimeType;
    private Boolean internal;
    @SerializedName("alt-text")
    private String altText;
    private String guid;

    /**
     * No args constructor for use in serialization
     */
    public Thumbnail() {
    }

    /**
     * @param guid
     * @param height
     * @param width
     * @param aspectRatio
     * @param altText
     * @param internal
     * @param mimeType
     * @param url
     */
    public Thumbnail(String url, Double aspectRatio, Integer height, Integer width, String mimeType, Boolean internal, String altText, String guid) {
        super();
        this.url = url;
        this.aspectRatio = aspectRatio;
        this.height = height;
        this.width = width;
        this.mimeType = mimeType;
        this.internal = internal;
        this.altText = altText;
        this.guid = guid;
    }

    public String getUrl() {
        if (url == null)
            return "Unknown";
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getAspectRatio() {
        if (aspectRatio == null)
            return 1.0;
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Integer getHeight() {
        if (height == null)
            return 100;
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        if (width == null)
            return 100;
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getMimeType() {
        if (mimeType == null)
            return "Unknown";
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Boolean getInternal() {
        if (internal == null)
            return false;
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public String getAltText() {
        if (altText == null)
            return "Unknown";
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getGuid() {
        if (guid == null)
            return "Unknown";
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}