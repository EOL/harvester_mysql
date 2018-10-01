package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlAgent implements Serializable{

    BigInteger id;
    Integer content_id;
    String content_type;
    String role_name;
    String value;
    String url;
    Integer resource_id;
    String resource_pk;
    String content_resource_fk;

    public MysqlAgent(BigInteger id, Integer content_id, String content_type, String role_name, String value, String url, Integer resource_id,
                      String resource_pk, String content_resource_fk){
        this.id=id;
        this.content_id=content_id;
        this.content_type=content_type;
        this.role_name=role_name;
        this.value=value;
        this.url=url;
        this.resource_id=resource_id;
        this.resource_pk=resource_pk;
        this.content_resource_fk=content_resource_fk;
    }

    public MysqlAgent(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_pk() {
        return resource_pk;
    }

    public void setResource_pk(String resource_pk) {
        this.resource_pk = resource_pk;
    }

    public String getContent_resource_fk() {
        return content_resource_fk;
    }

    public void setContent_resource_fk(String content_resource_fk) {
        this.content_resource_fk = content_resource_fk;
    }
}
