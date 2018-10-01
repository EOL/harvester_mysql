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

    @RequestMapping(value= "/addEntry", method = RequestMethod.POST, consumes = "application/json")
    public boolean addEntry(@RequestBody NodeRecord nodeRecord){
        return mysqlService.addEntry(nodeRecord);
    }

    @RequestMapping(value ="/getLatestUpdates/{startDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MysqlData> getLatestUpdates(@PathVariable("startDate") String startDate){
        long yourmilliseconds = Long.valueOf(startDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(yourmilliseconds);
        Date start =null;
        try {
            start = sdf.parse(sdf.format(resultdate));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        MysqlData mysqlData = mysqlService.getLatestUpdates(start);
        List<MysqlData> data = new ArrayList<>();
        data.add(mysqlData);

        return new ResponseEntity<MysqlData>(mysqlData, HttpStatus.OK);

    }
}
