package com.smartshare.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author SARFO PHILIP
 * Data Access Object for a new shareholder
 * Retrieves and Collects basic information about a shareholder
 * */

@Data
@Entity(name = "shareholder_tbl")
@EntityListeners(AuditingEntityListener.class)
public class Shareholder {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name="lastname")
    private String lastname;

    @Column(name="dob")
    private LocalDate dob;

    /* Has Social Security Number */
    @Column(name="has_ssn")
    private Boolean ssn;

    /* Has Tin Number*/
    @Column(name="has_tin")
    private Boolean tin;

    @Column(name="ssn")
    private String ssnNumber;

    @Column(name="tin")
    private String tinNumber;

    @Column(name = "gender")
    private int gender;

    @Column(name="email_type")
    private String emailType;

    @Column(name = "email")
    private String email;

    @Column(name="address_1")
    private String address1;

    @Column(name = "addresss_2")
    private String address2;

    @Column(name = "address_3")
    private String address3;

    @Column(name= "address_4")
    private String address4;

    @Column(name = "home_contact")
    private String homeContactNumber;

    @Column(name = "work_contact")
    private String workContactNumber;

    @Column(name = "cell_contact")
    private String cellContact;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "status")
    private Boolean isActive;

    @Column(name = "zip")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "amount")
    private BigDecimal amount;

    @CreatedBy
    private String user;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Shareholder(){}

    public Shareholder(String firstname,String middlename,String lastname,LocalDate dob,Boolean ssn,Boolean tin,
    String ssnNumber,String tinNumber,int gender,String emailType,String email,String address1,String address2,String address3,
                       String address4,String homeContactNumber,String workContactNumber,String cellContact,int statusCode,
                       Boolean isActive,String zipCode,String city,String country,BigDecimal amount){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.dob = dob;
        this.ssn = ssn;
        this.tin = tin;
        this.ssnNumber = ssnNumber;
        this.tinNumber = tinNumber;
        this.gender = gender;
        this.emailType = emailType;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
        this.homeContactNumber = homeContactNumber;
        this.workContactNumber = workContactNumber;
        this.cellContact = cellContact;
        this.statusCode = statusCode;
        this.isActive = isActive;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.amount = amount;
    }


}

