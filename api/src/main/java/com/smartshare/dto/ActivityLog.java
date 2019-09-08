package com.smartshare.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.ApplicationEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "activitylog_tbl")
public class ActivityLog{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID logId;

    @Column(name = "log_description")
    private String logDescription;

    @Column(name = "log_datetime")
    private LocalDateTime logDateTime;


    public ActivityLog(String logDescription, LocalDateTime logDateTime) {
        this.logDescription = logDescription;
        this.logDateTime = logDateTime;
    }
}
