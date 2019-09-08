package com.smartshare.service.implementation;

import com.smartshare.dto.Log;
import com.smartshare.repository.LogsRepository;
import com.smartshare.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogsRepository logsRepository;


    @Override
    public Page<Log> findLogs(LocalDateTime from, LocalDateTime to, Pageable page) {
        return logsRepository.findByLogDateBetween(from,to,page);
    }
}
