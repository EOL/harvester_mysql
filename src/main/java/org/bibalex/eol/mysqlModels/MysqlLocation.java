package org.bibalex.eol.mysqlModels;

import java.awt.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlLocation implements Serializable{
    BigInteger id;
    Integer resource_id;
    String location;
    float longitude;
    float latitude;
    float altitude;
    String spatial_location;

    public MysqlLocation (BigInteger id, Integer resource_id, String location, float longitude, float latitude, float altitude, String spatial_location){
        this.id=id;
        this.resource_id=resource_id;
        this.location=location;
        this.longitude=longitude;
        this.latitude=latitude;
        this.altitude=altitude;
        this.spatial_location=spatial_location;
    }

    public MysqlLocation(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public String getSpatial_location() {
        return spatial_location;
    }

    public void setSpatial_location(String spatial_location) {
        this.spatial_location = spatial_location;
    }
}
