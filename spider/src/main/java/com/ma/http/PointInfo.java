package com.ma.http;

import java.util.List;

public class PointInfo {

    private byte status;

    private String info;

    private String infocode;

    private int count;

    private List<Geocode> geocodes;

    public byte isStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Geocode> getGeocodes() {
        return geocodes;
    }

    public void setGeocodes(List<Geocode> geocodes) {
        this.geocodes = geocodes;
    }

    @Override
    public String toString() {
        return "Poi{" +
                "status=" + status +
                ", info='" + info + '\'' +
                ", infocode='" + infocode + '\'' +
                ", count=" + count +
                ", geocodes=" + geocodes +
                '}';
    }
}
