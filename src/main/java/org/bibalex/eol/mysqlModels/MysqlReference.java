package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlReference implements Serializable{
    BigInteger id;
    Integer parent_id;
    String parent_type;
    Integer resource_id;
    Integer referent_id;

    public MysqlReference (BigInteger id, Integer parent_id, String parent_type, Integer resource_id, Integer referent_id){
        this.id=id;
        this.parent_id=parent_id;
        this.parent_type=parent_type;
        this.resource_id=resource_id;
        this.referent_id=referent_id;
    }

    public MysqlReference(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public Integer getReferent_id() {
        return referent_id;
    }

    public void setReferent_id(Integer referent_id) {
        this.referent_id = referent_id;
    }
}
