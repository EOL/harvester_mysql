package org.bibalex.eol.handlers;

import org.bibalex.eol.helpers.DateHelper;
import org.bibalex.eol.models.*;

import javax.persistence.EntityManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class FileHandler {

    private EntityManager entityManager;
    private int resourceID;
    private FileWriter ranks, nodes, pages, pages_nodes, scientific_names, languages, vernaculars, locations, licenses, media, agents,
    page_contents, references, referents;

    private int ranks_count=0,nodes_count=0,pages_count=0, pages_nodes_count=0, scientific_names_count=0, media_count=0, page_contents_count=0,
    vernaculars_count=0;

    public FileHandler(EntityManager entityManager, int resourceID){
        this.entityManager=entityManager;
        this.resourceID=resourceID;
        try {
            ranks = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"ranks.txt",true);
            nodes = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"nodes.txt",true);
            pages = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"pages.txt", true);
            pages_nodes = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"pages_nodes.txt", true);
            scientific_names=new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"scientific_names.txt",true);
            languages =new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"languages.txt",true);
            vernaculars = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"vernaculars.txt",true);
            locations = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"locations.txt",true);
            licenses = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"licenses.txt",true);
            media = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"media.txt",true);
            agents = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"agents.txt",true);
            page_contents = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"page_contents.txt",true);
            referents = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"referents.txt",true);
            references = new FileWriter(PropertiesHandler.getProperty("mysqlFiles")+"references.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRankToFile(NodeRecord tableRecord){
//        System.out.println("insert new rank");
//        System.out.println(tableRecord.getTaxon().getTaxonRank());

        try
        {
            String date= DateHelper.getDate();
            ranks.write(tableRecord.getTaxon().getTaxonRank()+"\t"+date+"\t"+date+"\n");//appends the string to the file
            ranks_count++;
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void writeNodeToMysql(NodeRecord tableRecord){
//        System.out.println("insert new node");
        GlobalNamesHandler globalNamesHandler = new GlobalNamesHandler();
        Taxon taxon = tableRecord.getTaxon();
        try
        {
            String date= DateHelper.getDate();
            nodes.write(resourceID+"\t"+taxon.getScientificName()+"\t"+globalNamesHandler.getCanonicalForm(taxon.getScientificName())+
                    "\t"+Integer.valueOf(tableRecord.getGeneratedNodeId())+"\t"+taxon.getIdentifier()+"\t"+ date+"\t"+ date+
                    "\t"+tableRecord.getTaxon().getTaxonRank()+"\n");//appends the string to the file
            nodes_count++;

        }
        catch(IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    public void writePageToFile(NodeRecord tableRecord){
//        System.out.println("insert new page");

        try
        {
            String date= DateHelper.getDate();
            pages.write(Integer.valueOf(tableRecord.getTaxon().getPageEolId())+"\t"+date+"\t"+date+"\t"+
                    Integer.valueOf(tableRecord.getGeneratedNodeId())+"\n");
            pages_count++;

        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writePagesNodesToFile(int generated_node_id, int page_id) {
//        System.out.println("insert new page_node");

        try
        {
            String date= DateHelper.getDate();
            boolean is_native = resourceID == Integer.valueOf(PropertiesHandler.getProperty("DWHId"))? true:false;
            pages_nodes.write(page_id+"\t"+is_native+"\t"+date+"\t"+date+"\t"+ generated_node_id+"\n");
            pages_nodes_count++;
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeScientificNameToFile(NodeRecord tableRecord, int generated_node_id) {
//        System.out.println("insert new scientific name");
        Taxon taxon = tableRecord.getTaxon();
        GlobalNamesHandler globalNamesHandler = new GlobalNamesHandler();

        try
        {
            String date= DateHelper.getDate();
            scientific_names.write(resourceID+"\t"+globalNamesHandler.getCanonicalForm(taxon.getScientificName())+"\t"+globalNamesHandler.getCanonicalForm(taxon.getScientificName())+"\t"+taxon.getIdentifier()
                    +"\t"+generated_node_id+"\t"+Integer.valueOf(tableRecord.getTaxon().getPageEolId())+"\t"+1+"\t"+date+"\t"+date+"\t"+generated_node_id+"\n");
            scientific_names_count++;
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeVernacularsToFile(NodeRecord tableRecord, int generated_node_id) {
        ArrayList<VernacularName> vernaculars = tableRecord.getVernaculars();
        for(VernacularName vernacular : vernaculars){
            writeLanguageToFile(vernacular.getLanguage());
            writeVernacularsToFile(vernacular, Integer.valueOf(tableRecord.getTaxon().getPageEolId()), generated_node_id);
        }
    }

    private void writeVernacularsToFile(VernacularName vernacular, int page_id, int generated_node_id){

//        System.out.println("insert new vernacular");

        try
        {
            String date= DateHelper.getDate();
            boolean is_preferred = vernacular.getIsPreferred()=="1"? true:false;
            vernaculars.write(vernacular.getName()+"\t"+resourceID+"\t"+is_preferred+"\t"+generated_node_id+"\t"+page_id+"\t"+date+"\t"+date+"\t"+vernacular.getLanguage()+"\t"+generated_node_id+"\n");
            vernaculars_count++;
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void writeMediaToFile(NodeRecord tableRecord){
        ArrayList<Media> media = tableRecord.getMedia();
        for(Media medium : media){
            writeLanguageToFile(medium.getLanguage());
            writeLicenseToFile(medium.getLicense());
            if (medium.getLocationCreated() != null)
                writeLocationToFile(medium);
            String guid = String.valueOf(generateMediaGUID());
            writeMediumToFile(medium, guid);
            writePageContentToFile(Integer.valueOf(tableRecord.getTaxon().getPageEolId()), guid);
            if(medium.getAgents()!= null)
                writeAgentsToFile(medium.getAgents(), medium.getMediaId(), guid);
            if(tableRecord.getReferences() != null && medium.getReferenceId() != null)
                writeReferencesToFile(tableRecord.getReferences(), medium.getReferenceId(), guid);
        }
    }

    private void writeMediumToFile(Media medium, String guid){
//        System.out.println("insert new medium");

        try
        {
            String date= DateHelper.getDate();
            media.write(medium.getFormat()+"\t"+medium.getDescription()+"\t"+medium.getOwner()+"\t"+resourceID+"\t"+guid
                    +"\t"+medium.getMediaId()+"\t"+medium.getFurtherInformationURI()+"\t"+
                    PropertiesHandler.getProperty("storageLayerIp")+medium.getStorageLayerPath()+"\t"+date+"\t"+date+"\t"+medium.getLanguage()+"\t"+medium.getLicense()+"\n");
            media_count++;
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writePageContentToFile(int page_id, String guid){

//        System.out.println("insert new page_content");

        try
        {
            String date= DateHelper.getDate();
            page_contents.write(resourceID+"\t"+page_id+"\t"+page_id+"\tMedium\t"+date+"\t"+date+"\t"+guid+"\n");
            page_contents_count++;
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeAgentsToFile(ArrayList<Agent> agentsArr, String content_resource_fk, String guid){

        for(Agent agent : agentsArr){
            System.out.println("insert new agent");
            String role_name = agent.getRole() == null ? "roletest" : agent.getRole();

            try
            {
                String date= DateHelper.getDate();
                agents.write(resourceID+"\tMedium\t"+role_name +"\t"+agent.getHomepage()+"\t"+agent.getAgentId()+"\t"+agent.getFullName()+"\t"+content_resource_fk+"\t"+date+"\t"+date+"\t"+guid+"\n");
            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

    private void writeLanguageToFile(String code){
//        System.out.println("insert new language");
        if(code == null)
            code="eng";

        try
        {
            String date= DateHelper.getDate();
            languages.write(code+"\t"+code+"\t"+date+"\t"+date+"\n");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeLicenseToFile(String source_url){
//        System.out.println("insert new license");
        if(source_url == null)
            source_url="test";

        try
        {
            String date= DateHelper.getDate();
            licenses.write(source_url+"\tlicense\t"+date+"\t"+date+"\n");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeLocationToFile(Media medium){

        System.out.println("insert new location");

        try
        {
            String date= DateHelper.getDate();
            locations.write(resourceID+"\t"+medium.getLocationCreated()+"\t"+medium.getLongitude()+"\t"+medium.getLatitude()+"\t"+medium.getAltitude()
                    +"\t"+medium.getGenericLocation()+"\t"+date+"\t"+date+"\n");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeReferencesToFile(ArrayList<Reference> references, String reference_id, String guid) {
        String [] media_references = reference_id.split(";");
        for(int i=0; i< media_references.length; i++){
            for(Reference reference : references){
                if(reference.getReferenceId().equals(media_references[i])){
                    String body = reference.getPrimaryTitle()+" "+reference.getSecondaryTitle()+" "+reference.getPages()+" "+reference.getPageStart()+" "+
                            reference.getPageEnd()+" "+reference.getVolume()+" "+reference.getEditorsList()+" "+reference.getPublisher()+" "+
                            reference.getAuthorsList()+" "+reference.getEditorsList()+" "+reference.getDateCreated()+" "+reference.getDoi();
                    writeReferentToFile(body);
                    writeReferenceToFile(guid, body);
                }
            }
        }
    }

    private void writeReferentToFile(String body) {
//        System.out.println("insert new referent");

        try
        {
            String date= DateHelper.getDate();
            referents.write(resourceID+"\t"+body+"\t"+date+"\t"+date+"\n");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private void writeReferenceToFile(String guid, String body) {
//        System.out.println("insert new reference");

        try
        {
            String date= DateHelper.getDate();
            references.write(resourceID+"\tMedium\t"+date+"\t"+date+"\t"+guid+"\t"+body+"\t"+resourceID+"\n");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    private UUID generateMediaGUID()
    {
        return UUID.randomUUID();
    }

    public void close(){
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
            page_contents.close();
            agents.close();
            referents.close();
            references.close();
        } catch (IOException e) {
            System.out.println("erorrrrrrrrrrrrrrrrrrrrrrr");
            e.printStackTrace();
        }
    }

    public void printCounts() {
        System.out.println("ranks: "+ranks_count);
        System.out.println("nodes: "+nodes_count);
        System.out.println("pages: "+pages_count);
        System.out.println("pages_nodes: "+pages_nodes_count);
        System.out.println("scientific_names: "+scientific_names_count);
        System.out.println("media: "+media_count);
        System.out.println("page_contents: "+page_contents_count);
        System.out.println("vernaculars: "+vernaculars_count);

    }
}
