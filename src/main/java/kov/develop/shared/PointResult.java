package kov.develop.shared;

import kov.develop.shared.Point;
import kov.develop.shared.PointType;

import java.io.Serializable;

/**
 * DTO class
 */
public class PointResult implements Serializable{

    private int id;
    private String country;
    private String sity;
    private String adress;
    private String name;
    private String phone;
    private PointType type;

    public PointResult() {
    }

    public PointResult(String country, String sity, String adress, String name, String phone, PointType type) {
        this.country = country;
        this.sity = sity;
        this.adress = adress;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public PointResult(int id, String country, String sity, String adress, String name, String phone, PointType type) {
        this.id = id;
        this.country = country;
        this.sity = sity;
        this.adress = adress;
        this.name = name;
        this.phone = phone;
        this.type = type;
    }

    public PointResult(Point point){
        this.id = point.getId();
        this.country = point.getCountry();
        this.sity = point.getSity();
        this.adress = point.getAdress();
        this.name = point.getName();
        this.phone = point.getPhone();
        this.type = point.getType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSity() {
        return sity;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PointType getType() {
        return type;
    }

    public void setType(PointType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Point{" +
                "country='" + country + '\'' +
                ", sity='" + sity + '\'' +
                ", adress='" + adress + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                '}';
    }
}
