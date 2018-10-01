package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlLicense implements Serializable{
    BigInteger id;
    String source_url;
    String name;

    public MysqlLicense (BigInteger id, String source_url, String name){
        this.id=id;
        this.source_url=source_url;
        this.name=name;
    }

    public MysqlLicense(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
