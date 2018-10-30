package org.bibalex.eol.mysql;

import org.bibalex.eol.handlers.FileHandler;
import org.bibalex.eol.handlers.MysqlHandler;
import org.bibalex.eol.handlers.PropertiesHandler;
import org.bibalex.eol.models.NodeRecord;
import org.bibalex.eol.mysqlModels.MysqlData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Date;

@Service
public class MysqlService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean addEntries(NodeRecord[] nodeRecords) {
        try {
            PropertiesHandler.initializeProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean done =true;
        FileHandler fileHandler = new FileHandler(entityManager, nodeRecords[0].getResourceId());
        for(int i=0; i< nodeRecords.length;i++){
            boolean addRecord =addEntry(nodeRecords[i], fileHandler);
            done = done&&addRecord;
        }
        fileHandler.printCounts();
        fileHandler.close();
        MysqlHandler mysqlHandler = new MysqlHandler(entityManager);
        loadFilesToMysql();
        mysqlHandler.updateHarvestTime();

        return done;
    }

    public boolean addEntry(NodeRecord nodeRecord, FileHandler fileHandler) {
        try{
            fileHandler.writeRankToFile(nodeRecord);
            fileHandler.writeNodeToMysql(nodeRecord);
            if(nodeRecord.getTaxon().getPageEolId() != null && nodeRecord.getTaxon().getPageEolId()!= "0" ){

                fileHandler.writePageToFile(nodeRecord);
                fileHandler.writePagesNodesToFile(Integer.valueOf(nodeRecord.getGeneratedNodeId()), Integer.valueOf(nodeRecord.getTaxon().getPageEolId()));
                fileHandler.writeScientificNameToFile(nodeRecord, Integer.valueOf(nodeRecord.getGeneratedNodeId()));

                if(nodeRecord.getVernaculars()!= null)
                    fileHandler.writeVernacularsToFile(nodeRecord, Integer.valueOf(nodeRecord.getGeneratedNodeId()));
                if(nodeRecord.getMedia() != null)
                    fileHandler.writeMediaToFile(nodeRecord);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public MysqlData getLatestUpdates(Date startDate, Date endDate) {
        MysqlHandler mysqlHandler = new MysqlHandler(entityManager);

        MysqlData mysqlData = new MysqlData();
        mysqlData.setRanks(mysqlHandler.getRanks(startDate, endDate));
        mysqlData.setNodes(mysqlHandler.getNodes(startDate, endDate));
        mysqlData.setPages(mysqlHandler.getPages(startDate, endDate));
        mysqlData.setPages_nodes(mysqlHandler.getPagesNodes(startDate, endDate));
        mysqlData.setScientific_names(mysqlHandler.getScientificNames(startDate, endDate));
        mysqlData.setLanguages(mysqlHandler.getLanguages(startDate, endDate));
        mysqlData.setVernaculars(mysqlHandler.getVernaculars(startDate, endDate));
        mysqlData.setLicenses(mysqlHandler.getLicenses(startDate, endDate));
        mysqlData.setLocations(mysqlHandler.getLocations(startDate, endDate));
        mysqlData.setMedia(mysqlHandler.getMedia(startDate, endDate));
        mysqlData.setPage_contents(mysqlHandler.getPageContents(startDate, endDate));
        mysqlData.setAttributions(mysqlHandler.getAgents(startDate, endDate));
        mysqlData.setReferents(mysqlHandler.getReferents(startDate, endDate));
        mysqlData.setReferences(mysqlHandler.getReferences(startDate, endDate));

        return mysqlData;
    }

    public Date getEndTime(){
        MysqlHandler mysqlHandler = new MysqlHandler(entityManager);
        Date endTime = mysqlHandler.getEndTime();
        return endTime;
    }

    public boolean loadFilesToMysql() {
        try {
            PropertiesHandler.initializeProperties();
            MysqlHandler mysqlHandler=new MysqlHandler(entityManager);
            mysqlHandler.loadRanks();
            mysqlHandler.loadNodes();
            mysqlHandler.loadPages();
            mysqlHandler.loadPagesNodes();
            mysqlHandler.loadScientificNames();
            mysqlHandler.loadLanguages();
            mysqlHandler.loadVernaculars();
            mysqlHandler.loadLicenses();
            mysqlHandler.loadLocations();
            mysqlHandler.loadMedia();
            mysqlHandler.loadPageContents();
            mysqlHandler.loadAgents();
            mysqlHandler.loadReferents();
            mysqlHandler.loadReferences();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
