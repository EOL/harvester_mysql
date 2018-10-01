package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlReferent implements Serializable{

    BigInteger id;
    String primary_title;
    String secondary_title;
    Integer pages;
    Integer page_start;
    Integer page_end;
    String volume;
    String editor;
    String publisher;
    String authors_list;
    String editors_list;
    Date date_created;
    String doi;
    String body;
    Integer resource_id;
    String resource_pk;

    public MysqlReferent(BigInteger id, String primary_title, String secondary_title, Integer pages, Integer page_start, Integer page_end, String volume,
                         String editor, String publisher, String authors_list, String editors_list, Date date_created, String doi, String body,
                         Integer resource_id, String resource_pk){
        this.id=id;
        this.primary_title=primary_title;
        this.secondary_title=secondary_title;
        this.pages=pages;
        this.page_start=page_start;
        this.page_end=page_end;
        this.volume=volume;
        this.editor=editor;
        this.publisher=publisher;
        this.authors_list=authors_list;
        this.editors_list=editors_list;
        this.date_created=date_created;
        this.doi=doi;
        this.body=body;
        this.resource_id=resource_id;
        this.resource_pk=resource_pk;
    }

    public MysqlReferent(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getPrimary_title() {
        return primary_title;
    }

    public void setPrimary_title(String primary_title) {
        this.primary_title = primary_title;
    }

    public String getSecondary_title() {
        return secondary_title;
    }

    public void setSecondary_title(String secondary_title) {
        this.secondary_title = secondary_title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPage_start() {
        return page_start;
    }

    public void setPage_start(Integer page_start) {
        this.page_start = page_start;
    }

    public Integer getPage_end() {
        return page_end;
    }

    public void setPage_end(Integer page_end) {
        this.page_end = page_end;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthors_list() {
        return authors_list;
    }

    public void setAuthors_list(String authors_list) {
        this.authors_list = authors_list;
    }

    public String getEditors_list() {
        return editors_list;
    }

    public void setEditors_list(String editors_list) {
        this.editors_list = editors_list;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
}
