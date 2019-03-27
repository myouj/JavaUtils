package com.ma.http;

public class Geocode {

    private String formatted_address;
    private String country;
    private String province;
    private String citycode;
    private String city;
    private String district;
    private String adcode;
    private String location;
    private String level;

    @Override
    public String toString() {
        return "Geocode{" +
                "formatted_address='" + formatted_address + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", citycode='" + citycode + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", adcode='" + adcode + '\'' +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
