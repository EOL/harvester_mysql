package org.bibalex.eol.mysql;

import org.bibalex.eol.models.NodeRecord;
import org.bibalex.eol.mysqlModels.MysqlData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class MysqlController {

    @Autowired
    private MysqlService mysqlService;

    @RequestMapping(value= "/addEntries", method = RequestMethod.POST, consumes = "application/json")
    public boolean addEntries(@RequestBody NodeRecord [] nodeRecords){
        return mysqlService.addEntries(nodeRecords);
    }

    @RequestMapping(value= "/loadFilesToMysql", method = RequestMethod.POST)
    public boolean loadFilesToMysql(){
        return mysqlService.loadFilesToMysql();
    }

    @RequestMapping(value ="/getLatestUpdates/{startDate}/{endDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MysqlData> getLatestUpdates(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
        long startMilliSeconds = Long.valueOf(startDate);
        long endMilliSeconds = Long.valueOf(endDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultStartDate = new Date(startMilliSeconds);
        Date resultEndDate = new Date(endMilliSeconds);
        Date start =null, end=null;
        try {
            start = sdf.parse(sdf.format(resultStartDate));
            end = sdf.parse(sdf.format(resultEndDate));
            System.out.println(end);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        MysqlData mysqlData = mysqlService.getLatestUpdates(start, end);
        List<MysqlData> data = new ArrayList<>();
        data.add(mysqlData);

        return new ResponseEntity<MysqlData>(mysqlData, HttpStatus.OK);
    }

    @RequestMapping(value ="/getEndTime", method = RequestMethod.GET)
    public Date getEndTime(){
        return mysqlService.getEndTime();
    }
}
