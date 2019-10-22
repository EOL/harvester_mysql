package org.bibalex.eol.mysqlModels;

import java.math.BigInteger;

public class MysqlLightMedium {

    BigInteger id;
    Integer format;
    Integer resource_id;
    String guid;
    String resource_pk;
    String base_url;
    Integer subclass;
    String name;
    String sizes;

    public MysqlLightMedium(BigInteger id, Integer format, Integer resource_id, String guid, String resource_pk, String base_url, Integer subclass,
    String name, String sizes){
        this.id = id;
        this.format = format;
        this.resource_id = resource_id;
        this.guid = guid;
        this.resource_pk = resource_pk ;
        this.base_url = base_url ;
        this.subclass = subclass;
        this.name = name;
        this.sizes = sizes;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getResource_pk() {
        return resource_pk;
    }

    public void setResource_pk(String resource_pk) {
        this.resource_pk = resource_pk;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public Integer getSubclass() {
        return subclass;
    }

    public void setSubclass(Integer subclass) {
        this.subclass = subclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}

