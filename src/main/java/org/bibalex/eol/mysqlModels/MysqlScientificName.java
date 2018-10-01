package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class MysqlScientificName implements Serializable{
    BigInteger id;
    Integer resource_id;
    String canonical_form;
    String node_resource_pk;
    String italicized;
    Integer generated_node_id;
    Boolean is_preferred;
    BigInteger node_id;
    BigInteger page_id;
    BigInteger taxonomic_status_id;

    public MysqlScientificName (BigInteger id, Integer resource_id, String canonical_form, String node_resource_pk, String italicized, Integer generated_node_id, Boolean is_preferred, BigInteger node_id, BigInteger page_id, BigInteger taxonomic_status_id){
        this.id=id;
        this.resource_id=resource_id;
        this.canonical_form=canonical_form;
        this.node_resource_pk=node_resource_pk;
        this.italicized=italicized;
        this.generated_node_id=generated_node_id;
        this.is_preferred=is_preferred;
        this.node_id=node_id;
        this.page_id=page_id;
        this.taxonomic_status_id=taxonomic_status_id;
    }

    public MysqlScientificName(){

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

    public String getCanonical_form() {
        return canonical_form;
    }

    public void setCanonical_form(String canonical_form) {
        this.canonical_form = canonical_form;
    }

    public String getNode_resource_pk() {
        return node_resource_pk;
    }

    public void setNode_resource_pk(String node_resource_pk) {
        this.node_resource_pk = node_resource_pk;
    }

    public String getItalicized() {
        return italicized;
    }

    public void setItalicized(String italicized) {
        this.italicized = italicized;
    }

    public Integer getGenerated_node_id() {
        return generated_node_id;
    }

    public void setGenerated_node_id(Integer generated_node_id) {
        this.generated_node_id = generated_node_id;
    }

    public Boolean getIs_preferred() {
        return is_preferred;
    }

    public void setIs_preferred(Boolean is_preferred) {
        this.is_preferred = is_preferred;
    }

    public BigInteger getNode_id() {
        return node_id;
    }

    public void setNode_id(BigInteger node_id) {
        this.node_id = node_id;
    }

    public BigInteger getPage_id() {
        return page_id;
    }

    public void setPage_id(BigInteger page_id) {
        this.page_id = page_id;
    }

    public BigInteger getTaxonomic_status_id() {
        return taxonomic_status_id;
    }

    public void setTaxonomic_status_id(BigInteger taxonomic_status_id) {
        this.taxonomic_status_id = taxonomic_status_id;
    }
}
