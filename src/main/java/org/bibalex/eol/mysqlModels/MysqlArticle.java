package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;

public class MysqlArticle implements Serializable{
    BigInteger id;
    String owner;
    Integer resource_id;
    String guid;
    String resource_pk;
    Integer mime_type;
    String name;
    String rights_statement;
    String source_url;
    BigInteger bibliographic_citation_id;
    BigInteger language_id;
    BigInteger license_id;
    BigInteger location_id;
    String body;

    public MysqlArticle(BigInteger id, String owner, Integer resource_id, String guid, String resource_pk,
                        Integer mime_type, String name, String rights_statement, String source_url, BigInteger bibliographic_citation_id,
                       BigInteger language_id, BigInteger license_id, BigInteger location_id,String body){
        this.id=id;
        this.owner=owner;
        this.resource_id=resource_id;
        this.guid=guid;
        this.resource_pk=resource_pk;
        this.mime_type=mime_type;
        this.name=name;
        this.rights_statement=rights_statement;
        this.source_url=source_url;
        this.bibliographic_citation_id=bibliographic_citation_id;
        this.language_id=language_id;
        this.license_id=license_id;
        this.location_id=location_id;
        this.setBody(body);
    }

    public MysqlArticle(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Integer getMime_type() {
        return mime_type;
    }

    public void setMime_type(Integer mime_type) {
        this.mime_type = mime_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights_statement() {
        return rights_statement;
    }

    public void setRights_statement(String rights_statement) {
        this.rights_statement = rights_statement;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public BigInteger getBibliographic_citation_id() {
        return bibliographic_citation_id;
    }

    public void setBibliographic_citation_id(BigInteger bibliographic_citation_id) {
        this.bibliographic_citation_id = bibliographic_citation_id;
    }

    public BigInteger getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(BigInteger language_id) {
        this.language_id = language_id;
    }

    public BigInteger getLicense_id() {
        return license_id;
    }

    public void setLicense_id(BigInteger license_id) {
        this.license_id = license_id;
    }

    public BigInteger getLocation_id() {
        return location_id;
    }

    public void setLocation_id(BigInteger location_id) {
        this.location_id = location_id;
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }
}
