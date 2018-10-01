package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlRank implements Serializable{
    BigInteger id;
    String name;

    public MysqlRank (BigInteger id, String name){
        this.id = id;
        this.name = name;
    }

    public MysqlRank(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
