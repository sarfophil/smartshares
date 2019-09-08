package com.smartshare.repository;

import com.smartshare.dao.ShareholderDao;
import com.smartshare.dto.Shareholder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShareholderRepository extends JpaRepository<Shareholder, UUID> {
    Optional<Shareholder> findByUuid(UUID uuid);
    Page<Shareholder> findAll(Pageable pageable);
    Optional<Shareholder> findByClientId(String clientId);
    Optional<Shareholder> findByFirstnameAndMiddlenameAndLastname(String firstname,String middlename,String lastname);
    Page<Shareholder> findByIsActiveAndCreatedDateBetween(Boolean isActive, Pageable pageable, LocalDateTime from,LocalDateTime to);
    Page<Shareholder> findByStatusCodeAndCreatedDateBetween(int statusCode,Pageable pageable,LocalDateTime from,LocalDateTime to);

    Page<Shareholder> findByCreatedDateBetween(LocalDateTime from,LocalDateTime to,Pageable pageable);

    List<Shareholder> findByCreatedDateBetween(LocalDateTime from,LocalDateTime to);
    List<Shareholder> findByIsActiveAndCreatedDateBetween(Boolean isActive,LocalDateTime from,LocalDateTime to);
    List<Shareholder> findByStatusCodeAndCreatedDateBetween(int statusCode,LocalDateTime from,LocalDateTime to);


}
