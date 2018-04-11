package com.prabhu.thewild.utils;

import java.io.Serializable;

public class NatGeoAnimal implements Serializable
{

    private String title;
    private String scientificName;
    private String url;
    private String type;
    private String diet;
    private String groupName;
    private String averageLifeSpanInCaptivity;
    private String size;
    private String weight;
    private String populationTrend;
    private String status;
    private String statusCode;
    private Thumbnail thumbnail;
    private final static long serialVersionUID = 8509365552234056429L;

    /**
     * No args constructor for use in serialization
     *
     */
    public NatGeoAnimal() {
    }

    /**
     *
     * @param averageLifeSpanInCaptivity
     * @param groupName
     * @param scientificName
     * @param weight
     * @param diet
     * @param status
     * @param type
     * @param url
     * @param size
     * @param statusCode
     * @param title
     * @param thumbnail
     * @param populationTrend
     */
    public NatGeoAnimal(String title, String scientificName, String url, String type, String diet, String groupName, String averageLifeSpanInCaptivity, String size, String weight, String populationTrend, String status, String statusCode, Thumbnail thumbnail) {
        super();
        this.title = title;
        this.scientificName = scientificName;
        this.url = url;
        this.type = type;
        this.diet = diet;
        this.groupName = groupName;
        this.averageLifeSpanInCaptivity = averageLifeSpanInCaptivity;
        this.size = size;
        this.weight = weight;
        this.populationTrend = populationTrend;
        this.status = status;
        this.statusCode = statusCode;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        if (title == null)
            return "Unknown";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScientificName() {
        if (scientificName == null)
            return "Unknown";
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getUrl() {
        if (url == null)
            return "Unknown";
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        if (type == null)
            return "Unknown";
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiet() {
        if (diet == null)
            return "Unknown";
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getGroupName() {
        if (groupName == null)
            return "Unknown";
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAverageLifeSpanInCaptivity() {
        if (averageLifeSpanInCaptivity == null)
            return "Unknown";
        return averageLifeSpanInCaptivity;
    }

    public void setAverageLifeSpanInCaptivity(String averageLifeSpanInCaptivity) {
        this.averageLifeSpanInCaptivity = averageLifeSpanInCaptivity;
    }

    public String getSize() {
        if (size == null)
            return "Unknown";
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        if (weight == null)
            return "Unknown";
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPopulationTrend() {
        if (populationTrend == null)
            return "Unknown";
        return populationTrend;
    }

    public void setPopulationTrend(String populationTrend) {
        this.populationTrend = populationTrend;
    }

    public String getStatus() {
        if (status == null)
            return "Unknown";
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        if (statusCode == null)
            return "Unknown";
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

}
