package org.bibalex.eol.mysqlModels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


@Entity
@Table(name = "nodes")
public class MysqlNode implements Serializable {
    @Column(name="id")
    BigInteger id;
    @Column(name="resource_id")
    Integer resource_id;
    @Column(name="scientific_name")
    String scientific_name;
    @Column(name="canonical_form")
    String canonical_form;
    @Column(name="generated_node_id")
    Integer generated_node_id;
    @Column(name="resource_pk")
    String resource_pk;
    @Column(name="parent_id")
    Integer parent_id;
    @Column(name="rank_id")
    BigInteger rank_id;

    public MysqlNode (BigInteger id, Integer resource_id, String scientific_name, String canonical_form, Integer generated_node_id, String resource_pk,
                       Integer parent_id, BigInteger rank_id){
        this.id=id;
        this.resource_id =resource_id;
        this.scientific_name =scientific_name;
        this.canonical_form = canonical_form;
        this.generated_node_id = generated_node_id;
        this.resource_pk =resource_pk;
        this.parent_id = parent_id;
        this.rank_id = rank_id;
    }

    public MysqlNode(){

    }

    public BigInteger getId() {
        return id;
    }

    public String getCanonical_form() {
        return canonical_form;
    }

    public Integer getGenerated_node_id() {
        return generated_node_id;
    }

    public String getResource_pk() {
        return resource_pk;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setResource_id(Integer resource_id) {
        this.resource_id = resource_id;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public void setCanonical_form(String canonical_form) {
        this.canonical_form = canonical_form;
    }

    public void setGenerated_node_id(Integer generated_node_id) {
        this.generated_node_id = generated_node_id;
    }

    public void setResource_pk(String resource_pk) {
        this.resource_pk = resource_pk;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public void setRank_id(BigInteger rank_id) {
        this.rank_id = rank_id;
    }

    public BigInteger getRank_id() {
        return rank_id;

    }

    public Integer getResource_id() {

        return resource_id;
    }

    public void setId(BigInteger id) {

        this.id = id;
    }

    public String getScientific_name() {
        return scientific_name;
    }
}
