package com.smartshare.repository;

import com.smartshare.dto.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface LogsRepository extends JpaRepository<Log, UUID> {
    Page<Log> findByLogDateBetween(LocalDateTime from, LocalDateTime to,Pageable pageable);
}
