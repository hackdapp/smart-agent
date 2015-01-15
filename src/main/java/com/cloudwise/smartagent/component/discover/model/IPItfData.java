package com.cloudwise.smartagent.component.discover.model;


import java.io.UnsupportedEncodingException;

public class IPItfData {
    /**
     * "country": "中国",
        "country_id": "CN",
        "area": "华北",
        "area_id": "100000",
        "region": "北京市",
        "region_id": "110000",
        "city": "北京市",
        "city_id": "110000",
        "county": "",
        "county_id": "-1",
        "isp": "鹏博士",
        "isp_id": "1000143",
        "ip": "124.207.192.114"
     */
    private String country;
    private String country_id;
    private String area;
    private String area_id;
    private String region;
    private String region_id;
    private String city;
    private String city_id;
    private String county;
    private String county_id;
    private String isp;
    private String isp_id;
    private String ip;
    public String getCountry() {
        try {
            return new String(country.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "unknown";
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getArea() {
        try {
            return new String(area.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "unknown";
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getRegion() {
        try {
            return new String(region.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "unknown";
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCity() {
        try {
            return new String(city.getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "unknown";
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }
    public void setIsp(String isp) {
        this.isp = isp;
    }
    public void setIsp_id(String isp_id) {
        this.isp_id = isp_id;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
    
}
