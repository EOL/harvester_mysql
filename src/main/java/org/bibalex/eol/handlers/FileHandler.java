package org.bibalex.eol.handlers;

import org.bibalex.eol.helpers.DateHelper;
import org.bibalex.eol.models.*;

import javax.persistence.EntityManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class FileHandler {

    private EntityManager entityManager;
    private int resourceID;
    private FileWriter ranks, nodes, pages, pages_nodes, scientific_names, languages, vernaculars, locations, licenses, media, agents,
            page_contents, references, referents, articles, traits, taxa;

    private int ranks_count = 0, nodes_count = 0, pages_count = 0, pages_nodes_count = 0, scientific_names_count = 0, media_count = 0, page_contents_count = 0,
            vernaculars_count = 0, article_count = 0, traits_count = 0, taxa_count = 0;

    public FileHandler(EntityManager entityManager, int resourceID) {
        this.entityManager = entityManager;
        this.resourceID = resourceID;
        try {
            ranks = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "ranks.txt", true);
            nodes = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "nodes.txt", true);
            pages = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "pages.txt", true);
            pages_nodes = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "pages_nodes.txt", true);
            scientific_names = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "scientific_names.txt", true);
            languages = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "languages.txt", true);
            vernaculars = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "vernaculars.txt", true);
            locations = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "locations.txt", true);
            licenses = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "licenses.txt", true);
            media = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "media.txt", true);
            articles = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "articles.txt", true);
            agents = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "agents.txt", true);
            page_contents = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "page_contents.txt", true);
            referents = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "referents.txt", true);
            references = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "references.txt", true);
            traits = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "traits.txt", true);
            taxa = new FileWriter(PropertiesHandler.getProperty("mysqlFiles") + "taxa.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRankToFile(NodeRecord tableRecord) {
//        System.out.println("insert new rank");
//        System.out.println("ranks "+tableRecord.getTaxon().getTaxonRank());

        try {
            String date = DateHelper.getDate();
            ranks.write(tableRecord.getTaxon().getTaxonRank() + "\t" + date + "\t" + date + "\n");//appends the string to the file
            ranks_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void writeNodeToMysql(NodeRecord tableRecord) {
//        System.out.println("insert new node");
        GlobalNamesHandler globalNamesHandler = new GlobalNamesHandler();
        Taxon taxon = tableRecord.getTaxon();
        try {
            String date = DateHelper.getDate();
            nodes.write(resourceID + "\t" + taxon.getScientificName() + "\t" + globalNamesHandler.getCanonicalForm(taxon.getScientificName()) +
                    "\t" + Integer.valueOf(tableRecord.getGeneratedNodeId()) + "\t" + taxon.getIdentifier() + "\t" + date + "\t" + date +
                    "\t" + tableRecord.getTaxon().getTaxonRank() + "\n");//appends the string to the file
            nodes_count++;

        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void writePageToFile(NodeRecord tableRecord) {
//        System.out.println("insert new page");

        try {
            String date = DateHelper.getDate();
            pages.write(Integer.valueOf(tableRecord.getTaxon().getPageEolId()) + "\t" + date + "\t" + date + "\t" +
                    Integer.valueOf(tableRecord.getGeneratedNodeId()) + "\n");
            pages_count++;

        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writePagesNodesToFile(int generated_node_id, int page_id) {
//        System.out.println("insert new page_node");

        try {
            String date = DateHelper.getDate();
            boolean is_native = resourceID == Integer.valueOf(PropertiesHandler.getProperty("DWHId")) ? true : false;
            pages_nodes.write(page_id + "\t" + is_native + "\t" + date + "\t" + date + "\t" + generated_node_id + "\n");
            pages_nodes_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeScientificNameToFile(NodeRecord tableRecord, int generated_node_id) {
//        System.out.println("insert new scientific name");
        Taxon taxon = tableRecord.getTaxon();
        GlobalNamesHandler globalNamesHandler = new GlobalNamesHandler();

        try {
            String date = DateHelper.getDate();
            scientific_names.write(resourceID + "\t" + globalNamesHandler.getCanonicalForm(taxon.getScientificName()) + "\t" + globalNamesHandler.getCanonicalForm(taxon.getScientificName()) + "\t" + taxon.getIdentifier()
                    + "\t" + generated_node_id + "\t" + Integer.valueOf(tableRecord.getTaxon().getPageEolId()) + "\t" + 1 + "\t" + date + "\t" + date + "\t" + generated_node_id + "\n");
            scientific_names_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeVernacularsToFile(NodeRecord tableRecord, int generated_node_id) {
        ArrayList<VernacularName> vernaculars = tableRecord.getVernaculars();
        for (VernacularName vernacular : vernaculars) {
            writeLanguageToFile(vernacular.getLanguage());
            writeVernacularsToFile(vernacular, Integer.valueOf(tableRecord.getTaxon().getPageEolId()), generated_node_id);
        }
    }

    private void writeVernacularsToFile(VernacularName vernacular, int page_id, int generated_node_id) {

//        System.out.println("insert new vernacular");

        try {
            String date = DateHelper.getDate();
            boolean is_preferred = vernacular.getIsPreferred() == "1" ? true : false;
            vernaculars.write(vernacular.getName() + "\t" + resourceID + "\t" + is_preferred + "\t" + generated_node_id + "\t" + page_id + "\t" + date + "\t" + date + "\t" + vernacular.getLanguage() + "\t" + generated_node_id + "\n");
            vernaculars_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeTraitsToFile(NodeRecord tableRecord , int generated_node_id)  {
        if(tableRecord.getOccurrences() != null || tableRecord.getAssociations()!=null || tableRecord.getMeasurementOrFacts() != null || tableRecord.getTargetOccurrences() != null) {
            try {
                String date = DateHelper.getDate();
                System.out.println(generated_node_id);
                traits.write(generated_node_id + "\t");
                //            occurrences
                if (tableRecord.getOccurrences() != null) {
                    writeOccurrencesToFile(tableRecord);
                }
                traits.write("\t");
                //            associations
                if (tableRecord.getAssociations() != null)
                    writeAssociationsToFile(tableRecord);
                traits.write("\t");
                //            measurements
                if (tableRecord.getMeasurementOrFacts() != null)
                    writeMeasurementOrFactsToFile(tableRecord);
                traits.write("\t");
                //         targetOccurrences
                if (tableRecord.getTargetOccurrences() != null)
                    writeTargetOccurrencesToFile(tableRecord);
                traits.write("\t"+date+"\t"+date);
                traits.write("\n");
                traits_count++;
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

    private void writeOccurrencesToFile(NodeRecord tableRecord) {
        for (int i = 0; i < tableRecord.getOccurrences().size(); i++) {
            Occurrence occ = tableRecord.getOccurrences().get(i);
            try {
                traits.write("{");
                String occurrenceId = occ.getOccurrenceId() == null ? null : "\"" + occ.getOccurrenceId() + "\"";
                String eventId = occ.getEventId() == null ? null : "\"" + occ.getEventId() + "\"";
                String institutionCode = occ.getInstitutionCode() == null ? null : "\"" + occ.getInstitutionCode() + "\"";
                String collectionCode = occ.getCollectionCode() == null ? null : "\"" + occ.getCollectionCode() + "\"";
                String catalogNumber = occ.getCatalogNumber() == null ? null : "\"" + occ.getCatalogNumber() + "\"";
                String sex = occ.getSex() == null ? null : "\"" + occ.getSex() + "\"";
                String lifeStage = occ.getLifeStage() == null ? null : "\"" + occ.getLifeStage() + "\"";
                String reproductiveCondition = occ.getReproductiveCondition() == null ? null : "\"" + occ.getReproductiveCondition() + "\"";
                String behavior = occ.getBehavior() == null ? null : "\"" + occ.getBehavior() + "\"";
                String establishmentMeans = occ.getEstablishmentMeans() == null ? null : "\"" + occ.getEstablishmentMeans() + "\"";
                String remarks = occ.getRemarks() == null ? null : "\"" + occ.getRemarks().replace("\"", "\\\\\"") + "\"";
//                String remarks = occ.getRemarks() == null ? null : "\"" + occ.getRemarks() + "\"";
//                remarks = remarks.replace("\"","\\\\\"");
                String countOfIndividuals = occ.getCountOfIndividuals() == null ? null : "\"" + occ.getCountOfIndividuals() + "\"";
                String preparations = occ.getPreparations() == null ? null : "\"" + occ.getPreparations() + "\"";
                String fieldNotes = occ.getFieldNotes() == null ? null : "\"" + occ.getFieldNotes() + "\"";
                String samplingProtocol = occ.getSamplingProtocol() == null ? null : "\"" + occ.getSamplingProtocol() + "\"";
                String samplingEffort = occ.getSamplingEffort() == null ? null : "\"" + occ.getSamplingEffort() + "\"";
                String recordedBy = occ.getRecordedBy() == null ? null : "\"" + occ.getRecordedBy() + "\"";
                String identifiedBy = occ.getIdentifiedBy() == null ? null : "\"" + occ.getIdentifiedBy() + "\"";
                String dateIdentified = occ.getDateIdentified() == null ? null : "\"" + occ.getDateIdentified() + "\"";
                String eventDate = occ.getEventDate() == null ? null : "\"" + occ.getEventDate() + "\"";
                String modifiedDate = occ.getModifiedDate() == null ? null : "\"" + occ.getModifiedDate() + "\"";
                String locality = occ.getLocality() == null ? null : "\"" + String.valueOf(occ.getLocality()) + "\"";
//                String decimalLatitude = occ.getDecimalLatitude() == null ? null : "\""+occ.getDecimalLatitude().replace("\"","\\\\\"")+"\"";
//                String decimalLongitude = occ.getDecimalLongitude() == null ? null : "\""+occ.getDecimalLongitude().replace("\"","\\\\\"")+"\"";
//                String verbatimLatitude = occ.getVerbatimLatitude() == null ? null : "\""+occ.getVerbatimLatitude().replace("\"","\\\\\"")+"\"";
//                String verbatimLongitude = occ.getVerbatimLongitude() == null ? null : "\""+occ.getVerbatimLongitude().replace("\"","\\\\\"")+"\"";
//                String verbatimElevation = occ.getVerbatimElevation() == null ? null : "\""+occ.getVerbatimElevation().replace("\"","\\\\\"")+"\"";
                String deltaStatus = occ.getDeltaStatus() == null ? null : "\"" + occ.getDeltaStatus() + "\"";

                traits.write("\"occurrenceId\":" + occurrenceId + ",\"eventId\":" + eventId + ",\"institutionCode\":" + institutionCode +
                        ",\"collectionCode\":" + collectionCode + ",\"catalogNumber\":" + catalogNumber + ",\"sex\":" + sex +
                        ",\"lifeStage\":" + lifeStage + ",\"reproductiveCondition\":" + reproductiveCondition + ",\"behavior\":" + behavior +
                        ",\"establishmentMeans\":" + establishmentMeans + ",\"remarks\":" + remarks + ",\"countOfIndividuals\":" + countOfIndividuals +
                        ",\"preparations\":" + preparations + ",\"fieldNotes\":" + fieldNotes + ",\"samplingProtocol\":" + samplingProtocol +
                        ",\"samplingEffort\":" + samplingEffort + ",\"recordedBy\":" + recordedBy + ",\"identifiedBy\":" + identifiedBy +
                        ",\"dateIdentified\":" + dateIdentified + ",\"eventDate\":" + eventDate + ",\"modifiedDate\":" + modifiedDate +
                        ",\"locality\":" + locality /*+ ",\"decimalLatitude\":" + decimalLatitude + ",\"decimalLongitude\":" + decimalLongitude +
                        ",\"verbatimLatitude\":" + verbatimLatitude + ",\"verbatimLongitude\":" + verbatimLongitude + ",\"verbatimElevation\":" + verbatimElevation*/ +
                        ",\"deltaStatus\":" + deltaStatus
                );
                traits.write("}");
                if (i < tableRecord.getOccurrences().size() - 1) {
                    traits.write(",");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }

        }
    }

    private void writeAssociationsToFile(NodeRecord tableRecord) {
        for (int i = 0; i < tableRecord.getAssociations().size(); i++) {
            Association asso = tableRecord.getAssociations().get(i);
            try {
                traits.write("{");
                String associationId = asso.getAssociationId() == null ? null : "\"" + asso.getAssociationId() + "\"";
                String occurrenceId = asso.getTargetOccurrenceId() == null ? null : "\"" + asso.getOccurrenceId() + "\"";
                String associationType = asso.getAssociationType() == null ? null : "\"" + asso.getAssociationType() + "\"";
                String targetOccurrenceId = asso.getTargetOccurrenceId() == null ? null : "\"" + asso.getTargetOccurrenceId() + "\"";
                String determinedDate = asso.getDeterminedDate() == null ? null : "\"" + asso.getDeterminedDate() + "\"";
                String determinedBy = asso.getDeterminedBy() == null ? null : "\"" + asso.getDeterminedBy() + "\"";
//                String measurementMethod = asso.getMeasurementMethod() == null ? null : "\"" +asso.getMeasurementMethod() + "\"";
                String measurementMethod = asso.getMeasurementMethod() == null ? null :"\"" + asso.getMeasurementMethod().replace("\"","\\\\\"") + "\"";
//                String remarks = asso.getRemarks() == null ? null : "\"" + asso.getRemarks() + "\"";
                String remarks = asso.getRemarks() == null ? null :"\"" + asso.getRemarks().replace("\"","\\\\\"") + "\"";
//                String source = asso.getSource() == null ? null : "\"" + asso.getSource() + "\"";
                String source = asso.getSource() == null ? null :"\"" + asso.getSource().replace("\"","\\\\\"") + "\"";
//                String citation = asso.getCitation() == null ? null : "\"" + asso.getCitation() + "\"";
                String citation = asso.getCitation() == null ? null :"\"" + asso.getCitation().replace("\"","\\\\\"") + "\"";
                String contributor = asso.getContributor() == null ? null : "\"" + asso.getContributor() + "\"";
                String referenceId = asso.getReferenceId() == null ? null : "\"" + asso.getReferenceId() + "\"";
                String deltaStatus = asso.getDeltaStatus() == null ? null : "\"" + asso.getDeltaStatus() + "\"";


                traits.write("\"associationId\":" + associationId + ",\"occurrenceId\":" + occurrenceId + ",\"associationType\":" + associationType +
                        ",\"targetOccurrenceId\":" + targetOccurrenceId + ",\"determinedDate\":" + determinedDate + ",\"determinedBy\":" + determinedBy +
                        ",\"measurementMethod\":" + measurementMethod + ",\"remarks\":" + remarks + ",\"source\":" + source +
                        ",\"citation\":" + citation + ",\"contributor\":" + contributor + ",\"referenceId\":" + referenceId +
                        ",\"deltaStatus\":" + deltaStatus
                );
                traits.write("}");
                if (i < tableRecord.getAssociations().size() - 1) {
                    traits.write(",");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }

        }
    }

    private void writeMeasurementOrFactsToFile(NodeRecord tableRecord) {
        for (int i = 0; i < tableRecord.getMeasurementOrFacts().size(); i++) {
            MeasurementOrFact measurement = tableRecord.getMeasurementOrFacts().get(i);
            try {
                traits.write("{");
                String measurementId = measurement.getMeasurementId() == null ? null : "\"" + measurement.getMeasurementId() + "\"";
                String occurrenceId = measurement.getOccurrenceId() == null ? null : "\"" + measurement.getOccurrenceId() + "\"";
                String measurementOfTaxon = measurement.getMeasurementOfTaxon() == null ? null : "\"" + measurement.getMeasurementOfTaxon() + "\"";
                String associationId = measurement.getAssociationId() == null ? null : "\"" + measurement.getAssociationId() + "\"";
                String parentMeasurementId = measurement.getParentMeasurementId() == null ? null : "\"" + measurement.getParentMeasurementId() + "\"";
                String measurementType = measurement.getMeasurementType() == null ? null : "\"" + measurement.getMeasurementType() + "\"";
                String measurementValue = measurement.getMeasurementValue() == null ? null : "\"" +measurement.getMeasurementValue() + "\"";
                String unit = measurement.getUnit() == null ? null : "\"" +measurement.getUnit() + "\"";
                String accuracy = measurement.getAccuracy() == null ? null : "\"" +measurement.getAccuracy() + "\"";
                String statisticalMethod = measurement.getStatisticalMethod() == null ? null : "\"" +measurement.getStatisticalMethod() + "\"";
                String determinedDate = measurement.getDeterminedDate() == null ? null : "\"" +measurement.getDeterminedDate() + "\"";
                String determinedBy = measurement.getDeterminedBy() == null ? null : "\"" +measurement.getDeterminedBy() + "\"";
                String measurementMethod = measurement.getMeasurementMethod() == null ? null :"\"" + measurement.getMeasurementMethod().replace("\"","\\\\\"") + "\"";
//                String measurementMethod = measurement.getMeasurementMethod() == null ? null : "\"" +measurement.getMeasurementMethod() + "\"";
                String remarks = measurement.getRemarks() == null ? null :"\"" + measurement.getRemarks().replace("\"","\\\\\"") + "\"";
                //String remarks = measurement.getRemarks() == null ? null : "\"" + measurement.getRemarks() + "\"";
                String source = measurement.getSource() == null ? null :"\"" + measurement.getSource().replace("\"","\\\\\"") + "\"";
//                String source = measurement.getSource() == null ? null : "\"" + measurement.getSource() + "\"";
                String citation = measurement.getCitation() == null ? null :"\"" + measurement.getCitation().replace("\"","\\\\\"") + "\"";
//                String citation = measurement.getCitation() == null ? null : "\"" + measurement.getCitation() + "\"";
                String contributor = measurement.getContributor() == null ? null : "\"" + measurement.getContributor() + "\"";
                String referenceId = measurement.getReferenceId() == null ? null : "\"" + measurement.getReferenceId() + "\"";
                String deltaStatus = measurement.getDeltaStatus() == null ? null : "\"" + measurement.getDeltaStatus() + "\"";


                traits.write("\"measurementId\":" + measurementId + ",\"occurrenceId\":" + occurrenceId + ",\"measurementOfTaxon\":" + measurementOfTaxon +
                        ",\"associationId\":" + associationId + ",\"parentMeasurementId\":" + parentMeasurementId + ",\"measurementType\":" + measurementType +
                        ",\"statisticalMethod\":" + statisticalMethod + ",\"unit\":" + unit + ",\"accuracy\":" + accuracy +
                        ",\"measurementValue\":" + measurementValue + ",\"determinedDate\":" + determinedDate + ",\"determinedBy\":" + determinedBy +
                        ",\"measurementMethod\":" + measurementMethod + ",\"remarks\":" + remarks + ",\"source\":" + source +
                        ",\"citation\":" + citation + ",\"contributor\":" + contributor + ",\"referenceId\":" + referenceId +
                        ",\"deltaStatus\":" + deltaStatus
                );
                traits.write("}");
                if (i < tableRecord.getMeasurementOrFacts().size() - 1) {
                    traits.write(",");
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }

        }
    }

    public  void writeTargetOccurrencesToFile(NodeRecord tableRecord)
    {
//        ArrayList<String> keys= (ArrayList<String>) tableRecord.getTargetOccurrences().keySet();
//        ArrayList<String> values= (ArrayList<String>) tableRecord.getTargetOccurrences().values();
        Iterator<Map.Entry<String, String>> it = tableRecord.getTargetOccurrences().entrySet().iterator();
        try {
            traits.write("{");
            while (it.hasNext()) {
                Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
    //            System.out.println(pair.getKey() + " = " + pair.getValue());
                String targetOccurrenceId =  "\"" + pair.getKey() + "\":";
                String objectPageId = "\"" + pair.getValue() + "\"";

    //                traits.write("{");
                    traits.write( targetOccurrenceId + objectPageId);
    //                traits.write("}");
                    if(it.hasNext())
                    {
                        traits.write(",");
                    }

            }
            traits.write("}");
        }catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());}

    }

    public void  writeMediaToFile(NodeRecord tableRecord){

        ArrayList<Media> media = tableRecord.getMedia();
        String content_type = "";
        for (Media medium : media) {
            writeLanguageToFile(medium.getLanguage());
            writeLicenseToFile(medium.getLicense());
            if (medium.getLocationCreated() != null)
                writeLocationToFile(medium);
            String guid = String.valueOf(generateMediaGUID());
            if (medium.getFormatValue().equalsIgnoreCase("text_html")) {
                writeArticleToFile(medium, guid);
                content_type = "Article";
            }
//            else if(medium.getSubTypeValue().equalsIgnoreCase("map")){
//                writeMediumToFile(medium, guid);
//                content_type = "Map";
//            }
            else {
                writeMediumToFile(medium, guid);
                content_type = "Medium";
            }
            writePageContentToFile(Integer.valueOf(tableRecord.getTaxon().getPageEolId()), guid, content_type);
            if (medium.getAgents() != null)
                writeAgentsToFile(medium.getAgents(), medium.getMediaId(), guid, content_type);
            if (tableRecord.getReferences() != null && medium.getReferenceId() != null)
                writeReferencesToFile(tableRecord.getReferences(), medium.getReferenceId(), guid, content_type);
        }
    }

    private void writeMediumToFile(Media medium, String guid) {
//        System.out.println("insert new medium");

        try {
            String date = DateHelper.getDate();
            media.write(medium.getFormatIndex() + "\t" + medium.getDescription() + "\t" + medium.getOwner() + "\t" + resourceID + "\t" + guid
                    + "\t" + medium.getMediaId() + "\t" + medium.getFurtherInformationURI() + "\t" +
                    PropertiesHandler.getProperty("storageLayerIp") + medium.getStorageLayerPath() + "\t" + medium.getSubTypeIndex()
                    + "\t" + medium.getTitle() + "\t" + date + "\t" + date + "\t" + medium.getLanguage() + "\t" + medium.getLicense() + "\n");
            media_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeArticleToFile(Media medium, String guid) {
//        System.out.println("insert new article");

        try {
            String date = DateHelper.getDate();
            articles.write(medium.getOwner() + "\t" + resourceID + "\t" + guid
                    + "\t" + medium.getMediaId() + "\t" + medium.getTitle() + "\t" +
                    date + "\t" + date + "\t" + medium.getLanguage() + "\t" + medium.getLicense() + "\t" + medium.getDescription() + "\n");
            article_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writePageContentToFile(int page_id, String guid, String content_type) {

//        System.out.println("insert new page_content");

        try {
            String date = DateHelper.getDate();
            page_contents.write(resourceID + "\t" + page_id + "\t" + page_id + "\t" + content_type + "\t" + date + "\t" + date + "\t" + guid + "\n");
            page_contents_count++;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeAgentsToFile(ArrayList<Agent> agentsArr, String content_resource_fk, String guid, String content_type) {

        for (Agent agent : agentsArr) {
            System.out.println("insert new agent");
            String role_name = agent.getRole() == null ? "roletest" : agent.getRole();

            try {
                String date = DateHelper.getDate();
                agents.write(resourceID + "\t" + content_type + "\t" + role_name + "\t" + agent.getHomepage() + "\t" + agent.getAgentId() + "\t" + agent.getFullName() + "\t" + content_resource_fk + "\t" + date + "\t" + date + "\t" + guid + "\n");
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

    private void writeLanguageToFile(String code) {
//        System.out.println("insert new language");
        if (code == null)
            code = "eng";

        try {
            String date = DateHelper.getDate();
            languages.write(code + "\t" + code + "\t" + date + "\t" + date + "\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeLicenseToFile(String source_url) {
//        System.out.println("insert new license");
        if (source_url == null)
            source_url = "test";

        try {
            String date = DateHelper.getDate();
            licenses.write(source_url + "\tlicense\t" + date + "\t" + date + "\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeLocationToFile(Media medium) {

        System.out.println("insert new location");

        try {
            String date = DateHelper.getDate();
            locations.write(resourceID + "\t" + medium.getLocationCreated() + "\t" + medium.getLongitude() + "\t" + medium.getLatitude() + "\t" + medium.getAltitude()
                    + "\t" + medium.getGenericLocation() + "\t" + date + "\t" + date + "\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeReferencesToFile(ArrayList<Reference> references, String reference_id, String guid, String content_type) {
        String[] media_references = reference_id.split(";");
        for (int i = 0; i < media_references.length; i++) {
            for (Reference reference : references) {
                if (reference.getReferenceId().equals(media_references[i])) {
                    String body = reference.getPrimaryTitle() + " " + reference.getSecondaryTitle() + " " + reference.getPages() + " " + reference.getPageStart() + " " +
                            reference.getPageEnd() + " " + reference.getVolume() + " " + reference.getEditorsList() + " " + reference.getPublisher() + " " +
                            reference.getAuthorsList() + " " + reference.getEditorsList() + " " + reference.getDateCreated() + " " + reference.getDoi();
                    writeReferentToFile(body);
                    writeReferenceToFile(guid, body, content_type);
                }
            }
        }
    }

    private void writeReferentToFile(String body) {
//        System.out.println("insert new referent");

        try {
            String date = DateHelper.getDate();
            referents.write(resourceID + "\t" + body + "\t" + date + "\t" + date + "\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeReferenceToFile(String guid, String body, String content_type) {
//        System.out.println("insert new reference");

        try {
            String date = DateHelper.getDate();
            references.write(resourceID + "\t" + content_type + "\t" + date + "\t" + date + "\t" + guid + "\t" + body + "\t" + resourceID + "\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeTaxonToFile(NodeRecord tableRecord) {
        if (tableRecord.getOccurrences()==null)
            return;
        Taxon taxon = tableRecord.getTaxon();
        try {
            String generatedNodeId = tableRecord.getGeneratedNodeId(),
                    pageEolId = taxon.getPageEolId(),
                    scientificName = taxon.getScientificName(),
                    datasetId = taxon.getDatasetId(),
                    source = taxon.getSource(),
                    date = DateHelper.getDate();
            int resourceId = tableRecord.getResourceId();

            taxa.write( generatedNodeId + "\t"+pageEolId+"\t"+ scientificName +"\t"+
                     datasetId + "\t"+source+"\t"+resourceId+"\t");
            for (int i = 0; i < tableRecord.getOccurrences().size(); i++) {
                Occurrence occ = tableRecord.getOccurrences().get(i);
                String catalogNumber = occ.getCatalogNumber() == null ? null : "\"" + occ.getCatalogNumber() + "\"",
                        recordedBy = occ.getRecordedBy() == null ? null : "\"" + occ.getRecordedBy() + "\"",
                        identifiedBy = occ.getIdentifiedBy() == null ? null : "\"" + occ.getIdentifiedBy() + "\"",
                        eventDate = occ.getEventDate() == null ? null : "\"" + occ.getEventDate() + "\"",
                        decimalLatitude = occ.getDecimalLatitude() == null ? null : "\""+occ.getDecimalLatitude().replace("\"","\\\\\"")+"\"",
                        decimalLongitude = occ.getDecimalLongitude() == null ? null : "\""+occ.getDecimalLongitude().replace("\"","\\\\\"")+"\"";

                    taxa.write("{\"catalogNumber\":" + catalogNumber +
                    ",\"recordedBy\":" + recordedBy + ",\"identifiedBy\":" + identifiedBy + ",\"eventDate\":" + eventDate +
                     ",\"decimalLatitude\":" + decimalLatitude + ",\"decimalLongitude\":" + decimalLongitude+"}");
            if (i < tableRecord.getOccurrences().size() - 1) {
                taxa.write(",");
            }}
            taxa.write("\t"+date+"\t"+date+"\n");
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }


    private UUID generateMediaGUID() {
        return UUID.randomUUID();
    }

    public void close() {
        try {
            ranks.close();
            nodes.close();
            pages.close();
            pages_nodes.close();
            scientific_names.close();
            languages.close();
            vernaculars.close();
            licenses.close();
            locations.close();
            media.close();
            articles.close();
            page_contents.close();
            agents.close();
            referents.close();
            references.close();
            traits.close();
            taxa.close();
        } catch (IOException e) {
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr");
            e.printStackTrace();
        }
    }

    public void printCounts() {
        System.out.println("ranks: " + ranks_count);
        System.out.println("nodes: " + nodes_count);
        System.out.println("pages: " + pages_count);
        System.out.println("pages_nodes: " + pages_nodes_count);
        System.out.println("scientific_names: " + scientific_names_count);
        System.out.println("media: " + media_count);
        System.out.println("article: " + article_count);
        System.out.println("page_contents: " + page_contents_count);
        System.out.println("vernaculars: " + vernaculars_count);
        System.out.println("traits: " + traits_count);

    }
}
