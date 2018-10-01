package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlVernacular implements Serializable{
    BigInteger id;
    String string;
    Integer resource_id;
    Boolean is_prefered_by_resource;
    int generated_node_id;
    BigInteger node_id;
    BigInteger page_id;
    BigInteger language_id;

    public MysqlVernacular (BigInteger id, String string, int resource_id, Boolean is_prefered_by_resource, int generated_node_id, BigInteger node_id, BigInteger page_id, BigInteger language_id){
        this.id=id;
        this.string=string;
        this.resource_id=resource_id;
        this.is_prefered_by_resource=is_prefered_by_resource;
        this.generated_node_id=generated_node_id;
        this.node_id=node_id;
        this.page_id=page_id;
        this.language_id=language_id;
    }

    public MysqlVernacular(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getResource_id() {
        return resource_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public Boolean getIs_prefered_by_resource() {
        return is_prefered_by_resource;
    }

    public void setIs_prefered_by_resource(Boolean is_prefered_by_resource) {
        this.is_prefered_by_resource = is_prefered_by_resource;
    }

    public int getGenerated_node_id() {
        return generated_node_id;
    }

    public void setGenerated_node_id(int generated_node_id) {
        this.generated_node_id = generated_node_id;
    }

    public BigInteger getNode_id() {
        return node_id;
    }

    public void setNode_id(BigInteger node_id) {
        this.node_id = node_id;
    }

    public BigInteger getPage_id() {
        return page_id;
    }

    public void setPage_id(BigInteger page_id) {
        this.page_id = page_id;
    }

    public BigInteger getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(BigInteger language_id) {
        this.language_id = language_id;
    }
}
