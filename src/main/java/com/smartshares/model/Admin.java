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
@Table(name="admin_tbl")
public class Admin {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
    private int adminId;

    @Column(name="Surname")
    private String adminSurname;

    @Column(name="Othername")
    private String adminOthername;

    @Column(name="admin_name")
    private String accountName;

    @Column(name="admin_password")
    private String adminPassword;

    @Column(name="isActive")
    private Boolean isActive;
}