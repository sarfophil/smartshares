package com.smartshare.dao;


import com.smartshare.dto.Shareholder;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareholderDao {
    Shareholder findShareholderByFullname(String firstname, String lastname, String middlename);
}
