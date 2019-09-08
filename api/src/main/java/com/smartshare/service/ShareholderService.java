package com.smartshare.service;

import com.smartshare.dto.Shareholder;
import com.smartshare.ro.ShareholderRo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface ShareholderService {
    void shareholder(ShareholderRo shareholderRo);

    ResponseEntity<?> deleteShareholder(UUID uuid) throws NoSuchElementException;

    ResponseEntity<?> getShareholderByFullname(String firstname, String lastname, String middlename) throws NoSuchElementException;

    Page<Shareholder> getAllShareholders(Pageable pageable);

    void updateShareholderDetails(Shareholder shareholder);

    Shareholder findShareholderByClientId(String clientId) throws NoSuchElementException;

    List<Shareholder> findAllShareholders(LocalDateTime from, LocalDateTime to);

    Page<Shareholder> findShareholderByStatus(Boolean status, Pageable pageable, LocalDateTime from, LocalDateTime to);

    List<Shareholder> findAllShareholderByStatus(Boolean status, LocalDateTime from, LocalDateTime to);

    Page<Shareholder> findShareholderByStatusCode(int status, Pageable pageable, LocalDateTime from, LocalDateTime to);

    List<Shareholder> findAllShareholderByStatusCode(int status, LocalDateTime from, LocalDateTime to);

    Page<Shareholder> findShareholders(Pageable pageable, LocalDateTime from, LocalDateTime to);
}
