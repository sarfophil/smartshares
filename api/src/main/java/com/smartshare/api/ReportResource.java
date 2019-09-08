package com.smartshare.api;

import com.smartshare.ApplicationProperties;
import com.smartshare.ro.Links;
import com.smartshare.service.ShareholderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v0.1/reports/")
@RequiredArgsConstructor
public class ReportResource {
    private final ApplicationProperties applicationProperties;
    private final ShareholderService shareholderService;

    @GetMapping("links")
    public ResponseEntity<?> reportLinks(){
        List<Links> linksList = new ArrayList<>();
        linksList.add(new Links("Retrieve all shareholders",applicationProperties.getEnv().getUrl()
                +"api/v0.1/reports/shareholders"));
        linksList.add(new Links("Retrieve all shareholders by active status",applicationProperties.getEnv().getUrl()+
                "api/v0.1/shareholders/status"));
        linksList.add(new Links("Retrieve all shareholders by status",applicationProperties.getEnv().getUrl()+
                "api/v0.1/shareholder/status"));
        return ResponseEntity.ok(linksList);
    }

    @GetMapping("shareholders")
    public ResponseEntity<?> findShareholders(@RequestParam("size") int size, @RequestParam("page") int page,
                                                     @RequestParam("show_all") boolean maxPage,
                                                     @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                     @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Pageable pageable = PageRequest.of(page,maxPage?Integer.MAX_VALUE:size);
        return ResponseEntity.ok(shareholderService.findShareholders(pageable,from,to));
    }

    @GetMapping("shareholders/pdf")
    public ResponseEntity<?> exportShareholdersToPdf(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                     @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return ResponseEntity.ok(shareholderService.findAllShareholders(from,to));
    }



    @GetMapping("shareholders/status")
    public ResponseEntity<?> findShareholderByActiveStatus(@RequestParam("status") Boolean status,@RequestParam("size") int size,
                                                           @RequestParam("page") int page,@RequestParam("show_all") boolean maxPage,
                                                         @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                         @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Pageable pageable = PageRequest.of(page,maxPage?Integer.MAX_VALUE:size);
        return ResponseEntity.ok(shareholderService.findShareholderByStatus(status,pageable,from,to));
    }

    @GetMapping("shareholders/status/pdf")
    public ResponseEntity<?> exportShareholderByActiveStatus(@RequestParam("status") Boolean status,
                                                                        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return ResponseEntity.ok(shareholderService.findAllShareholderByStatus(status,from,to));
    }

    @GetMapping("shareholder/status")
    public ResponseEntity<?> findShareholderByStatus(@RequestParam("status") int status, @RequestParam("size") int size,
                                                     @RequestParam("page") int page,@RequestParam("show_all") boolean maxPage,
                                                     @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                     @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        Pageable pageable = PageRequest.of(page, maxPage?Integer.MAX_VALUE:size, Sort.Direction.DESC);
        return ResponseEntity.ok(shareholderService.findShareholderByStatusCode(status,pageable,from,to));
    }

    @GetMapping("shareholder/status/pdf")
    public ResponseEntity<?> findShareholderByStatusPdf(@RequestParam("status") int status,@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        return ResponseEntity.ok(shareholderService.findAllShareholderByStatusCode(status,from,to));
    }






}
