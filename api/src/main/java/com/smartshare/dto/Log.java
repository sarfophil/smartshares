package com.smartshare.dto;

import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author SARFO PHILIP
 * Transaction logs of a Shareholder
 */
@Data
@Entity(name = "logs_tbl")
public class Log {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column(name = "log_description")
    private String logDescription;

    @Column(name = "log_date")
    private LocalDateTime logDate;

    @Column(name = "log_amount")
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "shareholder",nullable = false)
    private Shareholder shareholder;

    @CreatedBy
    private String user;

    public Log(){}

    public Log(String logDescription, BigDecimal amount, Shareholder shareholder){
        this.logDescription = logDescription;
        this.amount = amount;
        this.shareholder = shareholder;
    }

}
