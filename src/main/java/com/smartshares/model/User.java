package com.smartshares.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author SARFO PHIL
 * 
 */
@Setter
@Getter
@Entity
@Table(name="user_tbl")
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private int userId;

    @Column(name="othername")
    private String userOthername;

    @Column(name="surname")
    private String userSurname;

    @Column(name="username")
    private String userAccountName;

    @Column(name="password")
    private String password;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="editted_date")
    private LocalDateTime edittedDate;

    @OneToOne
    @JoinColumn(name="admin",nullable = false)
    private Admin admin;

}