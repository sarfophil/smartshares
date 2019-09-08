package com.smartshare.config;

import com.smartshare.dto.ActivityLog;
import com.smartshare.event.CreationEvent;
import com.smartshare.service.ActivityLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class EventListenerComponent {

    @Autowired
    private ActivityLogService activityLogService;

    @TransactionalEventListener
    public void handleActivityLogEvent(CreationEvent<ActivityLog> creationEvent){
        ActivityLog activityLog = creationEvent.getObject();
        activityLogService.logPrincipal(activityLog.getLogDescription());
        log.info("Log : Activity Log Saved "+activityLog);
    }
}
