package org.bibalex.eol.models;

import java.io.Serializable;

/**
 * Created by Amr Morad
 * This class will be used to cover the kingdom phylum... file format
 */
public class AncestorNode implements Serializable {
    String rank;
    String scientificName;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
}
