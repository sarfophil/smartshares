package com.smartshare.service;

import com.smartshare.dto.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface LogService {
    Page<Log> findLogs(LocalDateTime from, LocalDateTime to,Pageable page);
}
