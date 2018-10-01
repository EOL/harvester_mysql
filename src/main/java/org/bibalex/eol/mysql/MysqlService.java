package org.bibalex.eol.mysql;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import org.bibalex.eol.handlers.MysqlHandler;
import org.bibalex.eol.handlers.PropertiesHandler;
import org.bibalex.eol.models.NodeRecord;
import org.bibalex.eol.mysqlModels.MysqlData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Date;

@Service
public class MysqlService {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean addEntry(NodeRecord nodeRecord) {
        try{
            PropertiesHandler.initializeProperties();
            MysqlHandler mysqlHandler = new MysqlHandler(entityManager, nodeRecord.getResourceId());
            int rank_id = mysqlHandler.insertRankToMysql(nodeRecord);
            int node_id = mysqlHandler.insertNodeToMysql(nodeRecord, rank_id);
            if(nodeRecord.getTaxon().getPageEolId()!= "0" || nodeRecord.getTaxon().getPageEolId() != null){

                mysqlHandler.insertPageToMysql(nodeRecord, node_id);
                mysqlHandler.insertPagesNodesToMysql(node_id, Integer.valueOf(nodeRecord.getTaxon().getPageEolId()));
                mysqlHandler.insertScientificNameToMysql(nodeRecord, node_id);

                if(nodeRecord.getVernaculars()!= null)
                    mysqlHandler.insertVernacularsToMysql(nodeRecord, node_id);
                if(nodeRecord.getMedia() != null)
                    mysqlHandler.insertMediaToMysql(nodeRecord);

            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public MysqlData getLatestUpdates(Date startDate) {
        MysqlHandler mysqlHandler = new MysqlHandler(entityManager);

        MysqlData mysqlData = new MysqlData();
        mysqlData.setRanks(mysqlHandler.getRanks(startDate));
        mysqlData.setNodes(mysqlHandler.getNodes(startDate));
        mysqlData.setPages(mysqlHandler.getPages(startDate));
        mysqlData.setPages_nodes(mysqlHandler.getPagesNodes(startDate));
        mysqlData.setScientific_names(mysqlHandler.getScientificNames(startDate));
        mysqlData.setLanguages(mysqlHandler.getLanguages(startDate));
        mysqlData.setVernaculars(mysqlHandler.getVernaculars(startDate));
        mysqlData.setLicenses(mysqlHandler.getLicenses(startDate));
        mysqlData.setLocations(mysqlHandler.getLocations(startDate));
        mysqlData.setMedia(mysqlHandler.getMedia(startDate));
        mysqlData.setPage_contents(mysqlHandler.getPageContents(startDate));
        mysqlData.setAttributions(mysqlHandler.getAgents(startDate));
        mysqlData.setReferents(mysqlHandler.getReferents(startDate));
        mysqlData.setReferences(mysqlHandler.getReferences(startDate));

        return mysqlData;
    }
}
