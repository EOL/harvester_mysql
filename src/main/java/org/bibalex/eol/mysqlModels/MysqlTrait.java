package org.bibalex.eol.mysqlModels;

public class MysqlTrait {
    int generated_node_id;
    String occurrences;
    String associations;
    String measurementOrFacts;
    public MysqlTrait(int generated_node_id, String occurrences, String associations, String measurementOrFacts){
        this.generated_node_id = generated_node_id;
        this.occurrences = occurrences;
        this.associations = associations;
        this.measurementOrFacts = measurementOrFacts;
    }

    public int getGenerated_node_id() {
        return generated_node_id;
    }

    public void setGenerated_node_id(int generated_node_id) {
        this.generated_node_id = generated_node_id;
    }

    public String getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(String occurrences) {
        this.occurrences = occurrences;
    }

    public String getAssociations() {
        return associations;
    }

    public void setAssociations(String associations) {
        this.associations = associations;
    }

    public String getMeasurementOrFacts() {
        return measurementOrFacts;
    }

    public void setMeasurementOrFacts(String measurementOrFacts) {
        this.measurementOrFacts = measurementOrFacts;
    }
}
