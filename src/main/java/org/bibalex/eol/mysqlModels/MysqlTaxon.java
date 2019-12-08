package org.bibalex.eol.mysqlModels;

import java.io.Serializable;

public class MysqlTaxon implements Serializable {
    Integer generated_node_id;
    Integer page_eol_id;
    String scientific_name;
    String dataset_id;
    String source;
    String occurrences;
    Integer resource_id;

    public MysqlTaxon(Integer generated_node_id, Integer page_eol_id, String scientific_name, String dataset_id, String source, String occurrences, Integer resource_id) {
        this.generated_node_id = generated_node_id;
        this.page_eol_id = page_eol_id;
        this.scientific_name = scientific_name;
        this.dataset_id = dataset_id;
        this.source = source;
        this.occurrences = occurrences;
        this.resource_id = resource_id;
    }

    public MysqlTaxon() {

    }

    public Integer getGenerated_node_id() {
        return generated_node_id;
    }

    public void setGenerated_node_id(Integer generated_node_id) {
        this.generated_node_id = generated_node_id;
    }

    public Integer getPage_eol_id() {
        return page_eol_id;
    }

    public void setPage_eol_id(Integer page_eol_id) {
        this.page_eol_id = page_eol_id;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getDataset_id() {
        return dataset_id;
    }

    public void setDataset_id(String dataset_id) {
        this.dataset_id = dataset_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(String occurrences) {
        this.occurrences = occurrences;
    }

    public Integer getResource_id() {
        return resource_id;
    }
    public void setResource_id(){this.resource_id = resource_id;}
}
