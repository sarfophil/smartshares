package com.smartshares.model;

import java.time.LocalDate;
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

@Getter
@Setter
@Entity
@Table(name="shareholder_tbl")
public class Shareholder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private int shareholderId;
    
    @Column(name="firstname")
    private String firstName;

    @Column(name="middlename")
    private String middleName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="ssn_number")
    private String SSnitNumber;

    @Column(name="tin_number")
    private String tinNumber;

    @Column(name="address_1")
    private String address1;

    @Column(name="address_2")
    private String address2;

    @Column(name="address_3")
    private String address3;

    @Column(name="address_4")
    private String address4;

    @Column(name="contact_1")
    private String contact1;

    @Column(name="contact_2")
    private String contact2;

    @Column(name="contact_3")
    private String contact3;

    @Column(name="zip")
    private String zip;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="email")
    private String email;

    @Column(name="email_type")
    private String emailType;

    @Column(name="shares")
    private Double shares;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="date_inputted")
    private LocalDateTime dateInputted;

    @Column(name="date_editted")
    private LocalDateTime dateEditted;

    @Column(name="date_deleted")
    private LocalDateTime dateDeleted;

    @OneToOne
    @JoinColumn(name="user",nullable = false)
    private User user;
}