package com.example.VMSappdemo.Controller;

import com.example.VMSappdemo.Dto.AllVisitsBy;
import com.example.VMSappdemo.Dto.Visitdto;
import com.example.VMSappdemo.Dto.Visitordto;
import com.example.VMSappdemo.Enums.VisitStatus;
import com.example.VMSappdemo.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/resident")
public class Resdident {

    @Autowired
    private ResidentService residentService;




    @PostMapping("/actOnVisit/{id}")
    public ResponseEntity<String>actOnVisit(@PathVariable Long id, @RequestParam VisitStatus visitStatus){
        return ResponseEntity.ok(residentService.updateService(id,visitStatus));

    }
      @GetMapping("/pendingVisits")
       public ResponseEntity<List<Visitdto>>pendingVisit(@RequestHeader Long id){
        return ResponseEntity.ok(residentService.getPendingVisit(id));
      }
    @GetMapping("/AllVisits")
    public ResponseEntity<AllVisitsBy>getpendingVisit(@RequestHeader Long id, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        Pageable  pageable = Pageable.ofSize(pageSize).withPage(pageNo);
        return ResponseEntity.ok(residentService.getAllVisits(id,pageable));
    }



}
