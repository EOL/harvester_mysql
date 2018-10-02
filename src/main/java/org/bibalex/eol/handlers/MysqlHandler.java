package org.bibalex.eol.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.bibalex.eol.models.*;
import org.bibalex.eol.mysqlModels.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json4s.jackson.Json;
import scala.util.parsing.json.JSON;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.*;

public class MysqlHandler {

    private EntityManager entityManager;
    private int resourceID;

    public MysqlHandler(EntityManager entityManager, int resourceID){
        this.entityManager=entityManager;
        this.resourceID=resourceID;
    }

    public MysqlHandler(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    public int insertRankToMysql(NodeRecord tableRecord){
        System.out.println("insert new rank");
        StoredProcedureQuery insertRank = entityManager
                .createStoredProcedureQuery("insertRank")
                .registerStoredProcedureParameter(
                        "name_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "rank_id", Integer.class, ParameterMode.OUT);
        insertRank.setParameter("name_p", tableRecord.getTaxon().getTaxonRank());
        insertRank.setParameter("created_at_p", new Date());
        insertRank.setParameter("updated_at_p", new Date());

        try {
            insertRank.execute();
            return (int) insertRank.getOutputParameterValue("rank_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }

    }

    public int insertNodeToMysql(NodeRecord tableRecord, int rank_id){
        System.out.println("insert new node");
        GlobalNamesHandler globalNamesHandler = new GlobalNamesHandler();
        Taxon taxon = tableRecord.getTaxon();
        StoredProcedureQuery insertNode = entityManager
                .createStoredProcedureQuery("insertNode")
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "scientific_name_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "canonical_form_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "generated_node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "resource_pk_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "rank_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "node_id", Integer.class, ParameterMode.OUT);

        insertNode.setParameter("resource_id_p", resourceID);
        insertNode.setParameter("scientific_name_p", taxon.getScientificName());
        insertNode.setParameter("canonical_form_p", globalNamesHandler.getCanonicalForm(taxon.getScientificName()));
        insertNode.setParameter("generated_node_id_p", Integer.valueOf(tableRecord.getGeneratedNodeId()));
        insertNode.setParameter("resource_pk_p", taxon.getIdentifier());
        insertNode.setParameter("rank_id_p", rank_id);
        insertNode.setParameter("created_at_p", new Date());
        insertNode.setParameter("updated_at_p", new Date());

        try {
            insertNode.execute();
            return (int) insertNode.getOutputParameterValue("node_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }
    }

    public void insertPageToMysql(NodeRecord tableRecord, int node_id){
        System.out.println("insert new page");
        StoredProcedureQuery insertPage = entityManager
                .createStoredProcedureQuery("insertPage")
                .registerStoredProcedureParameter(
                        "id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN);

        insertPage.setParameter("id_p", Integer.valueOf(tableRecord.getTaxon().getPageEolId()));
        insertPage.setParameter("node_id_p", node_id);
        insertPage.setParameter("created_at_p", new Date());
        insertPage.setParameter("updated_at_p", new Date());

        try {
            insertPage.execute();
        }catch (Exception e){
            System.out.println("duplicate line");
        }
    }

    public void insertPagesNodesToMysql(int node_id, int page_id) {
        System.out.println("insert new page_node");
        StoredProcedureQuery insertPagesNode = entityManager
                .createStoredProcedureQuery("insertPagesNode")
                .registerStoredProcedureParameter(
                        "page_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "is_native_p", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN);

        insertPagesNode.setParameter("page_id_p", page_id);
        insertPagesNode.setParameter("node_id_p", node_id);
        if(resourceID == Integer.valueOf(PropertiesHandler.getProperty("DWHId")))
            insertPagesNode.setParameter("is_native_p", true);
        else
            insertPagesNode.setParameter("is_native_p", false);
        insertPagesNode.setParameter("created_at_p", new Date());
        insertPagesNode.setParameter("updated_at_p", new Date());

        try {
            insertPagesNode.execute();
        }catch (Exception e){
            System.out.println("duplicate line");
        }
    }

    public void insertScientificNameToMysql(NodeRecord tableRecord, int node_id) {
        System.out.println("insert new scientific name");
        Taxon taxon = tableRecord.getTaxon();
        GlobalNamesHandler globalNamesHandler = new GlobalNamesHandler();
        StoredProcedureQuery insertScientificName = entityManager
                .createStoredProcedureQuery("insertScientificName")
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "canonical_form_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "italicized_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "node_resource_pk_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "generated_node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "page_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "taxonomic_status_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN);

        insertScientificName.setParameter("resource_id_p", resourceID);
        insertScientificName.setParameter("canonical_form_p", globalNamesHandler.getCanonicalForm(taxon.getScientificName()));
        insertScientificName.setParameter("italicized_p", globalNamesHandler.getCanonicalForm(taxon.getScientificName()));
        insertScientificName.setParameter("node_resource_pk_p", taxon.getIdentifier());
        insertScientificName.setParameter("generated_node_id_p", Integer.valueOf(tableRecord.getGeneratedNodeId()));
        insertScientificName.setParameter("page_id_p", Integer.valueOf(tableRecord.getTaxon().getPageEolId()));
        insertScientificName.setParameter("node_id_p", node_id);
        insertScientificName.setParameter("taxonomic_status_id_p", 1);
        insertScientificName.setParameter("created_at_p", new Date());
        insertScientificName.setParameter("updated_at_p", new Date());

        try {
            insertScientificName.execute();
        }catch (Exception e){
            System.out.println("duplicate line");
        }
    }

    public void insertVernacularsToMysql(NodeRecord tableRecord, int node_id) {
        ArrayList<VernacularName> vernaculars = tableRecord.getVernaculars();
        for(VernacularName vernacular : vernaculars){
            int language_id = insertLanguageToMysql(vernacular.getLanguage());
            insertVernacularToMysql(vernacular, language_id, node_id, Integer.valueOf(tableRecord.getTaxon().getPageEolId()), Integer.valueOf(tableRecord.getGeneratedNodeId()));
        }
    }

    private void insertVernacularToMysql(VernacularName vernacular, int language_id, int node_id, int page_id, int generated_node_id){

        System.out.println("insert new vernacular");
        StoredProcedureQuery insertVernacular = entityManager
                .createStoredProcedureQuery("insertVernacular")
                .registerStoredProcedureParameter(
                        "string_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "is_prefered_by_resource_p", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "generated_node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "page_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "node_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "language_id_p", Integer.class, ParameterMode.IN);

        insertVernacular.setParameter("string_p", vernacular.getName());
        insertVernacular.setParameter("resource_id_p", resourceID);
        insertVernacular.setParameter("is_prefered_by_resource_p", vernacular.getIsPreferred()=="1"? true:false);
        insertVernacular.setParameter("generated_node_id_p", generated_node_id);
        insertVernacular.setParameter("page_id_p", page_id);
        insertVernacular.setParameter("node_id_p", node_id);
        insertVernacular.setParameter("created_at_p", new Date());
        insertVernacular.setParameter("updated_at_p", new Date());
        insertVernacular.setParameter("language_id_p", language_id);


        try {
            insertVernacular.execute();
        }catch (Exception e){
            System.out.println("duplicate line");
        }
    }

    public void insertMediaToMysql(NodeRecord tableRecord){
        ArrayList<Media> media = tableRecord.getMedia();
        for(Media medium : media){
            int language_id = insertLanguageToMysql(medium.getLanguage());
            int license_id = insertLicenseToMysql(medium.getLicense());
            int location_id = medium.getLocationCreated() != null ? insertLocationToMysql(medium) : -1;
            int medium_id = insertMediumToMysql(medium, language_id, license_id, location_id);
            insertPageContentToMysql(Integer.valueOf(tableRecord.getTaxon().getPageEolId()), medium_id);
            if(medium.getAgents()!= null)
                insertAgentstoMysql(medium.getAgents(), medium_id, medium.getMediaId());
            if(tableRecord.getReferences() != null && medium.getReferenceId() != null)
                insertReferencesToMysql(tableRecord.getReferences(), medium.getReferenceId(), medium_id);
        }
    }

    private int insertMediumToMysql(Media medium, int language_id, int license_id, int location_id){

        System.out.println("insert new medium");
        StoredProcedureQuery insertMedium = entityManager
                .createStoredProcedureQuery("insertMedium")
                .registerStoredProcedureParameter(
                        "format_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "description_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "owner_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "guid_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "resource_pk_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "source_page_url_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "language_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "license_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "location_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "base_url_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "medium_id", Integer.class, ParameterMode.OUT);

        insertMedium.setParameter("format_p", medium.getFormat());
        insertMedium.setParameter("description_p", medium.getDescription());
        insertMedium.setParameter("owner_p", medium.getOwner());
        insertMedium.setParameter("resource_id_p", resourceID);
        insertMedium.setParameter("guid_p", String.valueOf(generateMediaGUID()));
        insertMedium.setParameter("resource_pk_p", medium.getMediaId());
        insertMedium.setParameter("source_page_url_p", PropertiesHandler.getProperty("storageLayerIp")+medium.getFurtherInformationURI());
        insertMedium.setParameter("language_id_p", language_id);
        insertMedium.setParameter("license_id_p", license_id);
        insertMedium.setParameter("location_id_p", location_id);
        insertMedium.setParameter("base_url_p", medium.getStorageLayerPath());
        insertMedium.setParameter("created_at_p", new Date());
        insertMedium.setParameter("updated_at_p", new Date());

        try {
            insertMedium.execute();
            return (int) insertMedium.getOutputParameterValue("medium_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }
    }

    private void insertPageContentToMysql(int page_id, int medium_id){

        System.out.println("insert new page_content");
        StoredProcedureQuery insertPageContent = entityManager
                .createStoredProcedureQuery("insertPageContent")
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "page_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "source_page_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "content_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "content_type_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN);

        insertPageContent.setParameter("resource_id_p", resourceID);
        insertPageContent.setParameter("page_id_p", page_id);
        insertPageContent.setParameter("source_page_id_p", page_id);
        insertPageContent.setParameter("content_id_p", medium_id);
        insertPageContent.setParameter("content_type_p", "Medium");
        insertPageContent.setParameter("created_at_p", new Date());
        insertPageContent.setParameter("updated_at_p", new Date());

        try {
            insertPageContent.execute();
        }catch (Exception e){
            System.out.println("duplicate line");
        }
    }

    private void insertAgentstoMysql(ArrayList<Agent> agents, int medium_id, String content_resource_fk){

        for(Agent agent : agents){
            System.out.println("insert new agent");
            String role_name = agent.getRole() == null ? "roletest" : agent.getRole();
            StoredProcedureQuery insertAgent = entityManager
                    .createStoredProcedureQuery("insertAgent")
                    .registerStoredProcedureParameter(
                            "resource_id_p", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "content_id_p", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "content_type_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "role_name_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "url_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "resource_pk_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "value_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "content_resource_fk_p", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "created_at_p", Date.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(
                            "updated_at_p", Date.class, ParameterMode.IN);

            insertAgent.setParameter("resource_id_p", resourceID);
            insertAgent.setParameter("content_id_p", medium_id);
            insertAgent.setParameter("content_type_p", "Medium");
            insertAgent.setParameter("role_name_p", role_name);
            insertAgent.setParameter("url_p", agent.getHomepage());
            insertAgent.setParameter("resource_pk_p", agent.getAgentId());
            insertAgent.setParameter("value_p", agent.getFullName());
            insertAgent.setParameter("content_resource_fk_p", content_resource_fk);
            insertAgent.setParameter("created_at_p", new Date());
            insertAgent.setParameter("updated_at_p", new Date());

            try {
                insertAgent.execute();
            }catch (Exception e){
                System.out.println("duplicate line");
            }
        }
    }

    private int insertLanguageToMysql(String code){
        System.out.println("insert new language");
        if(code == null)
            code="eng";
        StoredProcedureQuery insertLanguage = entityManager
                .createStoredProcedureQuery("insertLanguage")
                .registerStoredProcedureParameter(
                        "code_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "group_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "language_id", Integer.class, ParameterMode.OUT);

        insertLanguage.setParameter("code_p", code);
        insertLanguage.setParameter("group_p", code);
        insertLanguage.setParameter("created_at_p", new Date());
        insertLanguage.setParameter("updated_at_p", new Date());

        try {
            insertLanguage.execute();
            return (int) insertLanguage.getOutputParameterValue("language_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }
    }

    private int insertLicenseToMysql(String source_url){
        System.out.println("insert new license");
        if(source_url == null)
            source_url="test";
        StoredProcedureQuery insertLicense = entityManager
                .createStoredProcedureQuery("insertLicense")
                .registerStoredProcedureParameter(
                        "source_url_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "name_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "license_id", Integer.class, ParameterMode.OUT);

        insertLicense.setParameter("source_url_p", source_url);
        insertLicense.setParameter("name_p", "license");
        insertLicense.setParameter("created_at_p", new Date());
        insertLicense.setParameter("updated_at_p", new Date());

        try {
            insertLicense.execute();
            return (int) insertLicense.getOutputParameterValue("license_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }
    }

    private int insertLocationToMysql(Media medium){

        System.out.println("insert new location");
        StoredProcedureQuery insertLocation = entityManager
                .createStoredProcedureQuery("insertLocation")
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "location_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "longitude_p", Float.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "latitude_p", Float.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "altitude_p", Float.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "spatial_location_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "location_id", Integer.class, ParameterMode.OUT);

        insertLocation.setParameter("resource_id_p", resourceID);
        insertLocation.setParameter("location_p", medium.getLocationCreated());
        insertLocation.setParameter("longitude_p", medium.getLongitude());
        insertLocation.setParameter("latitude_p", Float.valueOf(medium.getLatitude()));
        insertLocation.setParameter("altitude_p", Float.valueOf(medium.getAltitude()));
        insertLocation.setParameter("spatial_location_p", Float.valueOf(medium.getGenericLocation()));
        insertLocation.setParameter("created_at_p", new Date());
        insertLocation.setParameter("updated_at_p", new Date());

        try {
            insertLocation.execute();
            return (int) insertLocation.getOutputParameterValue("location_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }
    }

    private void insertReferencesToMysql(ArrayList<Reference> references, String reference_id, int medium_id) {
        String [] media_references = reference_id.split(";");
        for(int i=0; i< media_references.length; i++){
            for(Reference reference : references){
                if(reference.getReferenceId().equals(media_references[i])){
                    String body = reference.getPrimaryTitle()+" "+reference.getSecondaryTitle()+" "+reference.getPages()+" "+reference.getPageStart()+" "+
                            reference.getPageEnd()+" "+reference.getVolume()+" "+reference.getEditorsList()+" "+reference.getPublisher()+" "+
                            reference.getAuthorsList()+" "+reference.getEditorsList()+" "+reference.getDateCreated()+" "+reference.getDoi();
                    int referent_id = insertReferentToMysql(body);
                    insertReferenceToMysql(referent_id, medium_id);
                }
            }
        }
    }

    private int insertReferentToMysql(String body) {
        System.out.println("insert new referent");
        StoredProcedureQuery insertReferent = entityManager
                .createStoredProcedureQuery("insertReferent")
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "body_p", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "referent_id", Integer.class, ParameterMode.OUT);

        insertReferent.setParameter("resource_id_p", resourceID);
        insertReferent.setParameter("body_p", body);
        insertReferent.setParameter("created_at_p", new Date());
        insertReferent.setParameter("updated_at_p", new Date());

        try {
            insertReferent.execute();
            return (int) insertReferent.getOutputParameterValue("referent_id");
        }catch (Exception e){
            System.out.println("duplicate line");
            return -1;
        }
    }

    private void insertReferenceToMysql(int referent_id, int medium_id) {
        System.out.println("insert new reference");
        StoredProcedureQuery insertReference = entityManager
                .createStoredProcedureQuery("insertReference")
                .registerStoredProcedureParameter(
                        "parent_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "referent_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "resource_id_p", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "parent_type_id", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "created_at_p", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "updated_at_p", Date.class, ParameterMode.IN);

        insertReference.setParameter("parent_id_p", medium_id);
        insertReference.setParameter("referent_id_p", referent_id);
        insertReference.setParameter("resource_id_p", resourceID);
        insertReference.setParameter("parent_type_id", "Medium");
        insertReference.setParameter("created_at_p", new Date());
        insertReference.setParameter("updated_at_p", new Date());

        try {
            insertReference.execute();
        }catch (Exception e){
            System.out.println("duplicate line");
        }
    }

    private UUID generateMediaGUID()
    {
        return UUID.randomUUID();
    }

    public ArrayList<MysqlRank> getRanks(Date startDate, Date endDate){

        StoredProcedureQuery getRanks = entityManager
                .createStoredProcedureQuery("getRanks")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getRanks.setParameter("start_date", startDate);
        getRanks.setParameter("end_date", endDate);

        List<Object[]> res = getRanks.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlRank> ranks = new ArrayList<>();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlRank rank = new MysqlRank((BigInteger) line[0], (String) line[1]);
            ranks.add(rank);
        }

        return ranks;

    }

    public ArrayList<MysqlNode> getNodes(Date startDate, Date endDate) {

        StoredProcedureQuery getNodes = entityManager
                .createStoredProcedureQuery("getNodes")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getNodes.setParameter("start_date", startDate);
        getNodes.setParameter("end_date", endDate);

        List<Object[]> res = getNodes.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlNode> nodes = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlNode node= new MysqlNode((BigInteger)line[0], (Integer) line[1], (String)line[2], (String)line[3], (Integer)line[4], (String)line[5], (Integer)line[6], (BigInteger)line[7]);
            nodes.add(node);
        }

        return nodes;
    }

    public ArrayList<MysqlPage> getPages(Date startDate, Date endDate){

        StoredProcedureQuery getPages = entityManager
                .createStoredProcedureQuery("getPages")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getPages.setParameter("start_date", startDate);
        getPages.setParameter("end_date", endDate);

        List<Object[]> res = getPages.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlPage> pages = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlPage page= new MysqlPage((Integer) line[0], (Integer)line[1], (BigInteger)line[2]);
            pages.add(page);
        }

        return pages;
    }

    public ArrayList<MysqlPagesNode> getPagesNodes(Date startDate, Date endDate){

        StoredProcedureQuery getPagesNodes = entityManager
                .createStoredProcedureQuery("getPagesNodes")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getPagesNodes.setParameter("start_date", startDate);
        getPagesNodes.setParameter("end_date", endDate);

        List<Object[]> res = getPagesNodes.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlPagesNode> pages_nodes = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlPagesNode pages_node= new MysqlPagesNode((BigInteger) line[0], (BigInteger)line[1], (BigInteger)line[2], (Boolean)line[3]);
            pages_nodes.add(pages_node);
        }

        return pages_nodes;
    }

    public ArrayList<MysqlScientificName> getScientificNames(Date startDate, Date endDate){

        StoredProcedureQuery getScientificNames = entityManager
                .createStoredProcedureQuery("getScientificNames")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getScientificNames.setParameter("start_date", startDate);
        getScientificNames.setParameter("end_date", endDate);

        List<Object[]> res = getScientificNames.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlScientificName> scientific_names = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlScientificName scientific_name= new MysqlScientificName((BigInteger) line[0], (Integer)line[1], (String)line[2], (String)line[3], (String) line[4], (Integer) line[5], (Boolean)line[6], (BigInteger)line[7], (BigInteger)line[8], (BigInteger)line[9]);
            scientific_names.add(scientific_name);
        }

        return scientific_names;
    }

    public ArrayList<MysqlLanguage> getLanguages (Date startDate, Date endDate){

        StoredProcedureQuery getLanguages = entityManager
                .createStoredProcedureQuery("getLanguages")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getLanguages.setParameter("start_date", startDate);
        getLanguages.setParameter("end_date", endDate);

        List<Object[]> res = getLanguages.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlLanguage> languages = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlLanguage language= new MysqlLanguage((BigInteger) line[0], (String) line[1], (String) line[2]);
            languages.add(language);
        }

        return languages;
    }

    public ArrayList<MysqlVernacular> getVernaculars(Date startDate, Date endDate){

        StoredProcedureQuery getVernaculars = entityManager
                .createStoredProcedureQuery("getVernaculars")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getVernaculars.setParameter("start_date", startDate);
        getVernaculars.setParameter("end_date", endDate);

        List<Object[]> res = getVernaculars.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlVernacular> vernaculars = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlVernacular vernacular= new MysqlVernacular((BigInteger) line[0], (String) line[1], (Integer)line[2], (Boolean) line[3], (Integer) line[4], (BigInteger)line[5], (BigInteger)line[6], (BigInteger)line[7]);
            vernaculars.add(vernacular);
        }

        return vernaculars;
    }

    public ArrayList<MysqlLicense> getLicenses(Date startDate, Date endDate){

        StoredProcedureQuery getLicenses = entityManager
                .createStoredProcedureQuery("getLicenses")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getLicenses.setParameter("start_date", startDate);
        getLicenses.setParameter("end_date", endDate);

        List<Object[]> res = getLicenses.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlLicense> licenses = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlLicense license= new MysqlLicense((BigInteger) line[0], (String) line[1], (String) line[2]);
            licenses.add(license);
        }

        return licenses;
    }

    public ArrayList<MysqlLocation> getLocations(Date startDate, Date endDate){

        StoredProcedureQuery getLocations = entityManager
                .createStoredProcedureQuery("getLocations")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getLocations.setParameter("start_date", startDate);
        getLocations.setParameter("end_date", endDate);

        List<Object[]> res = getLocations.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlLocation> locations = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlLocation location= new MysqlLocation((BigInteger) line[0], (Integer) line[1], (String) line[2], (float) line[3], (float) line[4], (float) line[5], (String) line[6]);
            locations.add(location);
        }

        return locations;
    }

    public ArrayList<MysqlMedium> getMedia(Date startDate, Date endDate){

        StoredProcedureQuery getMedia = entityManager
                .createStoredProcedureQuery("getMedia")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getMedia.setParameter("start_date", startDate);
        getMedia.setParameter("end_date", endDate);

        List<Object[]> res = getMedia.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlMedium> media = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlMedium location= new MysqlMedium((BigInteger) line[0], (Integer) line[1], (String) line[2], (String) line[3], (Integer) line[4], (String) line[5], (String) line[6], (String) line[7], (String) line[8],
            (Integer)line[9], (Integer)line[10], (String)line[11], (String)line[12], (String)line[13], (BigInteger)line[14],
                    (BigInteger)line[15], (BigInteger)line[16], (BigInteger)line[17]);
            media.add(location);
        }

        return media;
    }

    public ArrayList<MysqlPageContent> getPageContents(Date startDate, Date endDate){

        StoredProcedureQuery getPageContents = entityManager
                .createStoredProcedureQuery("getPageContents")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getPageContents.setParameter("start_date", startDate);
        getPageContents.setParameter("end_date", endDate);

        List<Object[]> res = getPageContents.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlPageContent> page_contents = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlPageContent page_content= new MysqlPageContent((BigInteger) line[0], (Integer) line[1], (String) line[2], (Integer) line[3], (Integer) line[4], (Boolean) line[5], (Boolean) line[6],
                    (Boolean) line[7], (Boolean)line[8], (BigInteger)line[9], (Integer)line[10]);
            page_contents.add(page_content);
        }

        return page_contents;
    }

    public ArrayList<MysqlAgent> getAgents(Date startDate, Date endDate){

        StoredProcedureQuery getAgents = entityManager
                .createStoredProcedureQuery("getAgents")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getAgents.setParameter("start_date", startDate);
        getAgents.setParameter("end_date", endDate);

        List<Object[]> res = getAgents.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlAgent> agents = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlAgent agent= new MysqlAgent((BigInteger) line[0], (Integer) line[1], (String) line[2], (String) line[3], (String) line[4], (String) line[5], (Integer) line[6], (String) line[7], (String) line[8]);
            agents.add(agent);
        }

        return agents;
    }

    public ArrayList<MysqlReferent> getReferents(Date startDate, Date endDate){

        StoredProcedureQuery getReferents = entityManager
                .createStoredProcedureQuery("getReferents")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getReferents.setParameter("start_date", startDate);
        getReferents.setParameter("end_date", endDate);

        List<Object[]> res = getReferents.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlReferent> referents = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlReferent referent= new MysqlReferent((BigInteger) line[0], (String) line[1], (String) line[2], (Integer) line[3], (Integer) line[4], (Integer) line[5], (String) line[6], (String) line[7], (String) line[8],
                    (String) line[9], (String) line[10], (Date)line[11], (String)line[12], (String)line[13], (Integer)line[14], (String) line[15]);
            referents.add(referent);
        }

        return referents;
    }

    public ArrayList<MysqlReference> getReferences(Date startDate, Date endDate){

        StoredProcedureQuery getReferences = entityManager
                .createStoredProcedureQuery("getReferences")
                .registerStoredProcedureParameter(
                        "start_date", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "end_date", Date.class, ParameterMode.IN);
        getReferences.setParameter("start_date", startDate);
        getReferences.setParameter("end_date", endDate);

        List<Object[]> res = getReferences.getResultList();
        Iterator it = res.iterator();
        ArrayList<MysqlReference> references = new ArrayList<>();
        while(it.hasNext()) {
            Object[] line = (Object[]) it.next();
            MysqlReference reference= new MysqlReference((BigInteger) line[0], (Integer) line[1], (String) line[2], (Integer) line[3], (Integer) line[4]);
            references.add(reference);
        }

        return references;
    }

    public Date getEndTime() {
        StoredProcedureQuery getEndTime = entityManager
                .createStoredProcedureQuery("getEndTime")
                .registerStoredProcedureParameter(
                        "end_time", Date.class, ParameterMode.OUT);

        getEndTime.execute();
        return (Date) getEndTime.getOutputParameterValue("end_time");
    }

    public void updateHarvestTime() {
        StoredProcedureQuery updateHarvestTime = entityManager
                .createStoredProcedureQuery("updateHarvestTime")
                .registerStoredProcedureParameter(
                        "harvest_time", Date.class, ParameterMode.IN);
        updateHarvestTime.setParameter("harvest_time", new Date());
        updateHarvestTime.execute();
    }
}
