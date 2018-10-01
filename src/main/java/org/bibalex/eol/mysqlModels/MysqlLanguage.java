package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlLanguage implements Serializable{
    BigInteger id;
    String code;
    String group;

    public MysqlLanguage (BigInteger id, String code, String group){
        this.id=id;
        this.code=code;
        this.group=group;
    }

    public MysqlLanguage(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
