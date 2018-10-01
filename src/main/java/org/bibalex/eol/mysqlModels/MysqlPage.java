package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlPage implements Serializable{
    int id;
    Integer page_richness;
    BigInteger node_id;

    public MysqlPage (int id, Integer page_richness, BigInteger node_id){
        this.id=id;
        this.page_richness=page_richness;
        this.node_id=node_id;
    }

    public MysqlPage(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPage_richness() {
        return page_richness;
    }

    public void setPage_richness(Integer page_richness) {
        this.page_richness = page_richness;
    }

    public BigInteger getNode_id() {
        return node_id;
    }

    public void setNode_id(BigInteger node_id) {
        this.node_id = node_id;
    }
}
