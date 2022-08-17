package com.example.userguide10;

public class CustomJavaClass{
    String Name;
    String Address;
    String imgLink;
    String distance;
    String fsqId;
    String latitude;
    String longitude;

    public CustomJavaClass(String name, String address, String imgLink, String distance, String fsqId, String latitude, String longitude) {
        Name = name;
        Address = address;
        this.imgLink = imgLink;
        this.distance = distance;
        this.fsqId = fsqId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFsqId() {
        return fsqId;
    }

    public void setFsqId(String fsqId) {
        this.fsqId = fsqId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
