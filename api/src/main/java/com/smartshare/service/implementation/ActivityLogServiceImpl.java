package com.smartshare.service.implementation;

import com.smartshare.dto.ActivityLog;
import com.smartshare.repository.ActivityLogRepository;
import com.smartshare.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Service
@Slf4j
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public void logPrincipal(String description){
        ActivityLog activityLog = new ActivityLog(description, LocalDateTime.now());
        log.info("activity : {}"+activityLog);
        activityLogRepository.save(activityLog);
    }

    @Override
    public Page<ActivityLog> findActivityLogs(LocalDateTime from, LocalDateTime to, Pageable pageable){
        return activityLogRepository.findByLogDateTimeBetween(from,to,pageable);
    }
}
