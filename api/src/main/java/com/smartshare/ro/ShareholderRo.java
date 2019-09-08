package com.smartshare.ro;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShareholderRo {
    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    private String middlename;

    @NonNull
    private LocalDate dob;

    private Boolean ssn;
    private Boolean tin;
    private String ssnNumber;
    private String tinNumber;
    @NonNull
    private int gender;
    @NonNull
    private String emailType;
    @NonNull
    private String email;
    @NonNull
    private String address1;

    private String address2;
    private String address3;
    private String address4;
    private String homeContactNumber;
    private String workContactNumber;
    @Size(max = 10)
    private String cellContact;
    private int statusCode;
    private Boolean isActive;
    private String zipCode;
    private String city;
    private String country;
    private BigDecimal amount;

    public ShareholderRo(@NonNull String firstname, @NonNull String lastname, String middlename, @NonNull LocalDate dob, Boolean ssn, Boolean tin, String ssnNumber, String tinNumber, @NonNull int gender, @NonNull String emailType, @NonNull String email, @NonNull String address1, String address2, String address3, String address4, String homeContactNumber, String workContactNumber, @Size(max = 10) String cellContact, int statusCode, Boolean isActive, String zipCode, String city, String country, BigDecimal amount) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
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
