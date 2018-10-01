package org.bibalex.eol.mysqlModels;

import org.omg.CosNaming.BindingHelper;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlMedium implements Serializable{
    BigInteger id;
    Integer format;
    String description;
    String owner;
    Integer resource_id;
    String guid;
    String resource_pk;
    String source_page_url;
    String base_url;
    Integer mime_type;
    Integer subclass;
    String name;
    String rights_statement;
    String source_url;
    BigInteger bibliographic_citation_id;
    BigInteger language_id;
    BigInteger license_id;
    BigInteger location_id;

    public MysqlMedium(BigInteger id, Integer format, String description, String owner, Integer resource_id, String guid, String resource_pk, String source_page_url,
                       String base_url, Integer mime_type, Integer subclass, String name, String rights_statement, String source_url, BigInteger bibliographic_citation_id,
                       BigInteger language_id, BigInteger license_id, BigInteger location_id){
        this.id=id;
        this.format=format;
        this.description=description;
        this.owner=owner;
        this.resource_id=resource_id;
        this.guid=guid;
        this.resource_pk=resource_pk;
        this.source_page_url=source_page_url;
        this.base_url=base_url;
        this.mime_type=mime_type;
        this.subclass=subclass;
        this.name=name;
        this.rights_statement=rights_statement;
        this.source_url=source_url;
        this.bibliographic_citation_id=bibliographic_citation_id;
        this.language_id=language_id;
        this.license_id=license_id;
        this.location_id=location_id;
    }

    public MysqlMedium(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSource_page_url() {
        return source_page_url;
    }

    public void setSource_page_url(String source_page_url) {
        this.source_page_url = source_page_url;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public Integer getMime_type() {
        return mime_type;
    }

    public void setMime_type(Integer mime_type) {
        this.mime_type = mime_type;
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
}
