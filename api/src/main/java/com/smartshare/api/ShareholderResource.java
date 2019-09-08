package com.smartshare.api;

import com.smartshare.dto.Shareholder;
import com.smartshare.ro.ShareholderRo;
import com.smartshare.service.ShareholderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v0.1/")
@Slf4j
public class ShareholderResource {

    @Autowired
    private ShareholderService shareholderService;

    @GetMapping("")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok("SmartShares backend API v0.1{}");
    }


    @PostMapping("shareholder")
    public ResponseEntity<?> addShareholder(@Valid @RequestBody ShareholderRo shareholderRo){
      shareholderService.shareholder(shareholderRo);
      return ResponseEntity.accepted().build();
    }

    @PutMapping("shareholder")
    public ResponseEntity<?> updateShareholder(@RequestBody Shareholder shareholder){
        ResponseEntity<?> responseEntity;
        try {
            shareholderService.updateShareholderDetails(shareholder);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @DeleteMapping("shareholder/{uuid}")
    public ResponseEntity<?> deleteShareholder(@PathVariable("uuid") UUID uuid){
        shareholderService.deleteShareholder(uuid);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("shareholders")
    public ResponseEntity<?> loadAllShareholders(@RequestParam("page") int page,@RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(shareholderService.getAllShareholders(pageable).getContent());
    }

    @GetMapping("shareholder/client/{clientId}")
    public ResponseEntity<?> searchByClientId(@PathVariable("clientId") String clientId){
        ResponseEntity<?> responseEntity;
        try{
            return ResponseEntity.ok(shareholderService.findShareholderByClientId(clientId));
        }catch (NoSuchElementException e){
             responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @GetMapping("shareholder/name")
    public ResponseEntity<?> searchByFullname(@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,
                                              @RequestParam("middlename") String middlename){
        ResponseEntity<?> responseEntity;
        try{
            return ResponseEntity.ok(shareholderService.getShareholderByFullname(firstname, lastname, middlename));
        }catch (NoSuchElementException e){
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

}
