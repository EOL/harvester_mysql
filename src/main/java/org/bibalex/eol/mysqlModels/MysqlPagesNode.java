package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlPagesNode implements Serializable{
    BigInteger id;
    BigInteger page_id;
    BigInteger node_id;
    Boolean is_native;

    public MysqlPagesNode (BigInteger id, BigInteger page_id, BigInteger node_id , Boolean is_native){
        this.id=id;
        this.page_id=page_id;
        this.node_id=node_id;
        this.is_native=is_native;
    }

    public MysqlPagesNode(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getPage_id() {
        return page_id;
    }

    public void setPage_id(BigInteger page_id) {
        this.page_id = page_id;
    }

    public BigInteger getNode_id() {
        return node_id;
    }

    public void setNode_id(BigInteger node_id) {
        this.node_id = node_id;
    }

    public Boolean getIs_native() {
        return is_native;
    }

    public void setIs_native(Boolean is_native) {
        this.is_native = is_native;
    }
}
