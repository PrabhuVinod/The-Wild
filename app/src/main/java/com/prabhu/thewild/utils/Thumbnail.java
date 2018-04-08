package com.prabhu.thewild.utils;

import java.io.Serializable;

public class Thumbnail implements Serializable
{

    private String url;
    private Double aspectRatio;
    private Integer height;
    private Integer width;
    private String mimeType;
    private Boolean internal;
    private String altText;
    private String guid;
    private final static long serialVersionUID = -1420670207777116643L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Thumbnail() {
    }

    /**
     *
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
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}