package org.bibalex.eol.mysqlModels;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MysqlData implements Serializable{
    ArrayList<MysqlRank> ranks;
    ArrayList<MysqlNode> nodes;
    ArrayList<MysqlPage> pages;
    ArrayList<MysqlPagesNode> pages_nodes;
    ArrayList<MysqlScientificName> scientific_names;
    ArrayList<MysqlLanguage> languages;
    ArrayList<MysqlVernacular> vernaculars;
    ArrayList<MysqlLicense> licenses;
    ArrayList<MysqlLocation> locations;
    ArrayList<MysqlMedium> media;
    ArrayList<MysqlArticle> articles;
    ArrayList<MysqlPageContent> page_contents;
    ArrayList<MysqlAgent> attributions;
    ArrayList<MysqlReferent> referents;
    ArrayList<MysqlReference> references;
    ArrayList<MysqlTrait> traits;
    ArrayList<MysqlTaxon> taxa;
//    String occurrences;
//    String associations;
//    String measurementOrFacts;
    public MysqlData (){

    }
    public ArrayList<MysqlRank> getRanks() {
        return ranks;
    }

    public void setRanks(ArrayList<MysqlRank> ranks) {
        this.ranks = ranks;
    }

    public ArrayList<MysqlPage> getPages() {
        return pages;
    }

    public void setPages(ArrayList<MysqlPage> pages) {
        this.pages = pages;
    }

    public ArrayList<MysqlPagesNode> getPages_nodes() {
        return pages_nodes;
    }

    public void setPages_nodes(ArrayList<MysqlPagesNode> pages_nodes) {
        this.pages_nodes = pages_nodes;
    }

    public ArrayList<MysqlScientificName> getScientific_names() {
        return scientific_names;
    }

    public void setScientific_names(ArrayList<MysqlScientificName> scientific_names) {
        this.scientific_names = scientific_names;
    }

    public ArrayList<MysqlLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<MysqlLanguage> languages) {
        this.languages = languages;
    }

    public ArrayList<MysqlVernacular> getVernaculars() {
        return vernaculars;
    }

    public void setVernaculars(ArrayList<MysqlVernacular> vernaculars) {
        this.vernaculars = vernaculars;
    }

    public ArrayList<MysqlLicense> getLicenses() {
        return licenses;
    }

    public void setLicenses(ArrayList<MysqlLicense> licenses) {
        this.licenses = licenses;
    }

    public ArrayList<MysqlLocation> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<MysqlLocation> locations) {
        this.locations = locations;
    }

    public ArrayList<MysqlMedium> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<MysqlMedium> media) {
        this.media = media;
    }

    public ArrayList<MysqlArticle> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<MysqlArticle> articles) {
        this.articles = articles;
    }

    public ArrayList<MysqlPageContent> getPage_contents() {
        return page_contents;
    }

    public void setPage_contents(ArrayList<MysqlPageContent> page_contents) {
        this.page_contents = page_contents;
    }

    public ArrayList<MysqlAgent> getAttributions() {
        return attributions;
    }

    public void setAttributions(ArrayList<MysqlAgent> attributions) {
        this.attributions = attributions;
    }

    public ArrayList<MysqlReferent> getReferents() {
        return referents;
    }

    public void setReferents(ArrayList<MysqlReferent> referents) {
        this.referents = referents;
    }

    public ArrayList<MysqlReference> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<MysqlReference> references) {
        this.references = references;
    }


    public void setNodes(ArrayList<MysqlNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<MysqlNode> getNodes() {
        return nodes;
    }

    public ArrayList<MysqlTrait> getTraits() {
        return traits;
    }

    public void setTraits(ArrayList<MysqlTrait> traits) {
        this.traits = traits;
    }
    public void setTaxa(ArrayList<MysqlTaxon> taxa){
        this.taxa = taxa;
    }

    public ArrayList<MysqlTaxon> getTaxa() {
        return taxa;
    }

    //    public void setOccurrences(String occurrences) {
//        this.occurrences = occurrences;
//    }
//
//    public void setAssociations(String associations) {
//        this.associations = associations;
//    }
//
//    public void setMeasurementOrFacts(String measurementOrFacts) {
//        this.measurementOrFacts = measurementOrFacts;
//    }
//
//    public String getOccurrences() {
//        return occurrences;
//    }
//
//    public String getAssociations() {
//        return associations;
//    }
//
//    public String getMeasurementOrFacts() {
//        return measurementOrFacts;
//    }

}
