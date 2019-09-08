package com.smartshare.event;

import com.smartshare.dto.ActivityLog;

public class LogCreatedEvent extends CreationEvent<ActivityLog> {
    public LogCreatedEvent(ActivityLog activityLog) {
        super(activityLog);
    }
}
