package com.smartshares.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="logs_tbl")
public class Logs {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
    private int logId;

}