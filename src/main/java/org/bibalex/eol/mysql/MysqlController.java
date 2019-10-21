package org.bibalex.eol.mysql;

import org.bibalex.eol.helpers.DateHelper;
import org.bibalex.eol.models.NodeRecord;
import org.bibalex.eol.mysqlModels.MysqlData;
import org.bibalex.eol.mysqlModels.MysqlMedium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

        Date start = DateHelper.convertFromMillisecondsToDate(startDate);
        Date end = DateHelper.convertFromMillisecondsToDate(endDate);

        MysqlData mysqlData = mysqlService.getLatestUpdates(start, end);
        List<MysqlData> data = new ArrayList<>();
        data.add(mysqlData);

        return new ResponseEntity<MysqlData>(mysqlData, HttpStatus.OK);
    }

    @RequestMapping(value ="/getMediaOfResource/{resourceId}/{limit}/{offset}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<MysqlMedium> getMediaOfResource(@PathVariable("resourceId") int resource_id, @PathVariable("limit") int limit, @PathVariable("offset") int offset){
        ArrayList<MysqlMedium> media = mysqlService.getMediaOfResource(resource_id, limit, offset);
        return media;
    }

    @RequestMapping(value ="/getEndTime", method = RequestMethod.GET)
    public Date getEndTime(){
        return mysqlService.getEndTime();
    }

    @RequestMapping(value ="/getStartAndEndTimes/{startDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<String> getStartAndEndTimes(@PathVariable("startDate") String startDate){
        Date date = DateHelper.convertFromMillisecondsToDate(startDate);
        return mysqlService.getStartAndEndTimes(date);
    }

    @RequestMapping(value= "/addStartTimeOfResource", method = RequestMethod.POST)
    public boolean addStartTimeOfResource(){
        return mysqlService.addStartTimeOfResource();
    }

    @RequestMapping(value= "/addEndTimeOfResource", method = RequestMethod.POST)
    public boolean addEndTimeOfResource(){
        return mysqlService.addEndTimeOfResource();
    }

}
