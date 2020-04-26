package model;

import java.util.StringJoiner;

public class Location {
    private String kode;
    private String street;
    private double latitude;
    private double longitude;

    public Location() {
        super();
    }
    
    public Location(String id, String name, double latitude, double longitude) {
        super();
        this.kode = id;
        this.street = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public final String getKode() {
        return kode;
    }

    public final void setKode(String kode) {
        this.kode = kode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
