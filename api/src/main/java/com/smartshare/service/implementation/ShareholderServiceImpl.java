package com.smartshare.service.implementation;

import com.smartshare.constant.EventTypes;
import com.smartshare.dto.ActivityLog;
import com.smartshare.dto.Shareholder;
import com.smartshare.repository.ShareholderRepository;
import com.smartshare.ro.ShareholderRo;
import com.smartshare.service.ActivityLogService;
import com.smartshare.service.ShareholderService;
import com.smartshare.utils.Principal;
import com.smartshare.ws.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@Slf4j
public class ShareholderServiceImpl implements ShareholderService {

    @Autowired
    private ShareholderRepository shareholderRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ActivityLogService activityLogService;


    @Override
    public void shareholder(ShareholderRo shareholderRo){
        Shareholder shareholder = new Shareholder(
                shareholderRo.getFirstname().toLowerCase(),shareholderRo.getMiddlename().toLowerCase(),shareholderRo.getLastname().toLowerCase(),
                shareholderRo.getDob(),shareholderRo.getSsn(),shareholderRo.getTin(),shareholderRo.getSsnNumber(),
                shareholderRo.getTinNumber(),shareholderRo.getGender(),shareholderRo.getEmailType(),
                shareholderRo.getEmail(),shareholderRo.getAddress1(),shareholderRo.getAddress2(),
                shareholderRo.getAddress3(),shareholderRo.getAddress4(),shareholderRo.getHomeContactNumber(),
                shareholderRo.getWorkContactNumber(),shareholderRo.getCellContact(),shareholderRo.getStatusCode(),
                shareholderRo.getIsActive(),shareholderRo.getZipCode(),shareholderRo.getCity(),shareholderRo.getCountry(),
                shareholderRo.getAmount());
        Shareholder savedShareholder = shareholderRepository.save(shareholder);

        //Update Shareholder
        generateClientId(savedShareholder.getUuid());

        //Message Broker
        Listener listener = new Listener(Principal.getPrincipal(),EventTypes.NEW_SHAREHOLDER,LocalDateTime.now(),true);
        simpMessagingTemplate.convertAndSend("/topic/new_shareholder",listener);
    }

    private void generateClientId(UUID uuid){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YY");
        int uuidLength = uuid.toString().length();
        String clientId = "SMSH-"+dateTimeFormatter.format(LocalDate.now())+
                            uuid.toString().substring(uuidLength-5,uuidLength);
        Optional<Shareholder> shareholder = shareholderRepository.findByUuid(uuid);
        if(shareholder.isPresent()){
            Shareholder shareholder1 = shareholder.get();
            shareholder1.setClientId(clientId.toUpperCase());
            shareholderRepository.save(shareholder1);

            //Store log activity
            String logDescription = "New Record Added: "+clientId.toUpperCase()+". Account: "+Principal.getPrincipal()+" at "+LocalDateTime.now();
          //  ActivityLog activityLog = new ActivityLog(logDescription,LocalDateTime.now());
           // applicationEventPublisher.publishEvent(new LogCreatedEvent(activityLog));
            activityLogService.logPrincipal(logDescription);
        }
    }

    @Override
    public ResponseEntity<?> deleteShareholder(UUID uuid) throws NoSuchElementException{
        Optional<Shareholder> shareholder = shareholderRepository.findByUuid(uuid);
        shareholder.orElseThrow();
        Shareholder shareholder1 = shareholder.get();
        shareholder1.setIsActive(false);
        shareholderRepository.save(shareholder1);

        //Store log activity
        String logDescription = "Record "+shareholder1.getClientId()+" Deleted. Account: "+Principal.getPrincipal()+". Time "+ LocalDateTime.now();
        ActivityLog activityLog = new ActivityLog(logDescription, LocalDateTime.now());
        applicationEventPublisher.publishEvent(activityLog);

        //Message broker
        Listener listener = new Listener(Principal.getPrincipal(),EventTypes.DELETE_SHAREHOLDER,LocalDateTime.now(),true);
        simpMessagingTemplate.convertAndSend("/topic/remove_shareholder",listener);

        return ResponseEntity.ok("Record removed");
    }


    @Override
    public ResponseEntity<?> getShareholderByFullname(String firstname, String lastname, String middlename) throws NoSuchElementException{
        Optional<Shareholder> shareholder = shareholderRepository.findByFirstnameAndMiddlenameAndLastname(firstname.toLowerCase(),middlename.toLowerCase(),lastname.toLowerCase());
        shareholder.orElseThrow();
        return ResponseEntity.ok(shareholder.get());
    }


    @Override
    public void updateShareholderDetails(Shareholder shareholder) throws NoSuchElementException{
        Optional<Shareholder> shareholder1 = shareholderRepository.findByUuid(shareholder.getUuid());
        if(shareholder1.isPresent()) {
            shareholderRepository.save(shareholder);

            //Message broker
            Listener listener = new Listener(Principal.getPrincipal(), EventTypes.UPDATED_SHAREHOLDER, LocalDateTime.now(), true);
            simpMessagingTemplate.convertAndSend("/topic/update_shareholder", listener);

            //Store log activity
            String logDescription = "Record " + shareholder.getClientId() + " updated. Account: " + Principal.getPrincipal() + ". Time " + LocalDateTime.now();
            //ActivityLog activityLog = new ActivityLog(logDescription, LocalDateTime.now());
            //applicationEventPublisher.publishEvent(activityLog);
            activityLogService.logPrincipal(logDescription);
        }else {
            throw new NoSuchElementException("Shareholder not found");
        }

    }

    @Override
    public Shareholder findShareholderByClientId(String clientId) throws NoSuchElementException{
        Optional<Shareholder> shareholder = shareholderRepository.findByClientId(clientId.toUpperCase());
        shareholder.orElseThrow();
        return shareholder.get();
    }

    @Override
    public Page<Shareholder> getAllShareholders(Pageable pageable){
        return shareholderRepository.findAll(pageable);
    }

    @Override
    public List<Shareholder> findAllShareholders(LocalDateTime from, LocalDateTime to){
        return shareholderRepository.findByCreatedDateBetween(from,to);
    }

    /**
     * @apiNote Find Shareholders by their Active Status
     *
     * */
    @Override
    public Page<Shareholder> findShareholderByStatus(Boolean status, Pageable pageable,LocalDateTime from,LocalDateTime to){
        return shareholderRepository.findByIsActiveAndCreatedDateBetween(status,pageable,from,to);
    }

    @Override
    public List<Shareholder> findAllShareholderByStatus(Boolean status, LocalDateTime from, LocalDateTime to){
        return shareholderRepository.findByIsActiveAndCreatedDateBetween(status,from,to);
    }

    /**
     * @apiNote Find shareholders by the status code.
     * */
    @Override
    public Page<Shareholder> findShareholderByStatusCode(int status,Pageable pageable,LocalDateTime from,LocalDateTime to){
        return shareholderRepository.findByStatusCodeAndCreatedDateBetween(status,pageable,from,to);
    }

    @Override
    public List<Shareholder> findAllShareholderByStatusCode(int status, LocalDateTime from, LocalDateTime to){
        return shareholderRepository.findByStatusCodeAndCreatedDateBetween(status,from,to);
    }

    @Override
    public Page<Shareholder> findShareholders(Pageable pageable, LocalDateTime from, LocalDateTime to){
        return shareholderRepository.findByCreatedDateBetween(from,to,pageable);
    }








}
