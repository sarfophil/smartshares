package com.smartshare.service;

import com.smartshare.dto.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ActivityLogService {
    void logPrincipal(String description);

    Page<ActivityLog> findActivityLogs(LocalDateTime from, LocalDateTime to, Pageable pageable);
}
