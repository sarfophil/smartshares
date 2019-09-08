package com.smartshare.api;

import com.smartshare.service.ActivityLogService;
import com.smartshare.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v0.1/logs")
public class LogResource {
    @Autowired
    private LogService logService;

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("")
    public ResponseEntity<?> loadApplicationLogs(@RequestParam("size") int size, @RequestParam("page") int page,
                                                 @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                 @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(logService.findLogs(from,to,pageable));
    }

    @GetMapping("/activities")
    public ResponseEntity<?> loadActivities(@RequestParam("size") int size,@RequestParam("page") int page,
                                            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(activityLogService.findActivityLogs(from,to,pageable));
    }
}
