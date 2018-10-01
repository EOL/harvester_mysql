package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlPageContent implements Serializable{

    BigInteger id;
    Integer resource_id;
    String content_type;
    Integer content_id;
    Integer trust;
    Boolean is_incorrect;
    Boolean is_misidentified;
    Boolean is_hidden;
    Boolean is_duplicate;
    BigInteger page_id;
    Integer source_page_id;

    public MysqlPageContent(BigInteger id, Integer resource_id, String content_type, Integer content_id, Integer trust,
                            Boolean is_incorrect, Boolean is_misidentified, Boolean is_hidden, Boolean is_duplicate, BigInteger page_id, Integer source_page_id){
        this.id=id;
        this.resource_id=resource_id;
        this.content_type=content_type;
        this.content_id=content_id;
        this.trust=trust;
        this.is_incorrect=is_incorrect;
        this.is_misidentified=is_misidentified;
        this.is_hidden=is_hidden;
        this.is_duplicate=is_duplicate;
        this.page_id=page_id;
        this.source_page_id=source_page_id;
    }

    public MysqlPageContent(){

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

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Integer getTrust() {
        return trust;
    }

    public void setTrust(Integer trust) {
        this.trust = trust;
    }

    public Boolean getIs_incorrect() {
        return is_incorrect;
    }

    public void setIs_incorrect(Boolean is_incorrect) {
        this.is_incorrect = is_incorrect;
    }

    public Boolean getIs_misidentified() {
        return is_misidentified;
    }

    public void setIs_misidentified(Boolean is_misidentified) {
        this.is_misidentified = is_misidentified;
    }

    public Boolean getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(Boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public Boolean getIs_duplicate() {
        return is_duplicate;
    }

    public void setIs_duplicate(Boolean is_duplicate) {
        this.is_duplicate = is_duplicate;
    }

    public BigInteger getPage_id() {
        return page_id;
    }

    public void setPage_id(BigInteger page_id) {
        this.page_id = page_id;
    }

    public Integer getSource_page_id() {
        return source_page_id;
    }

    public void setSource_page_id(Integer source_page_id) {
        this.source_page_id = source_page_id;
    }
}
