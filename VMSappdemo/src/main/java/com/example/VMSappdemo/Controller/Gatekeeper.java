package com.example.VMSappdemo.Controller;

import com.example.VMSappdemo.Dto.Visitdto;
import com.example.VMSappdemo.Dto.Visitordto;
import com.example.VMSappdemo.Service.GateKeeperService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/gatekeeper")
public class Gatekeeper {

    private static Logger LOGGER = LoggerFactory.getLogger(Gatekeeper.class);
    @Autowired
    private GateKeeperService gateKeeperService;

    @PostMapping("/createVisitor")
    public ResponseEntity<Long> createVisitor(@RequestBody @Valid Visitordto visitordto){
        return ResponseEntity.ok(gateKeeperService.createVisitor(visitordto));


    }

    @GetMapping("/getById")
    public ResponseEntity<Visitordto>getById(@RequestParam String id){
        Visitordto visitordto = gateKeeperService.getById(id);
        if(visitordto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(visitordto);
    }

    @PostMapping("/createVist")
    public ResponseEntity<Long>createVisit(@RequestBody @Valid Visitdto visitdto){
       return  ResponseEntity.ok(gateKeeperService.createVisit(visitdto));
    }
    @PostMapping("/markEntry/{id}")
    public ResponseEntity<String>markEntry(@PathVariable Long id){
        return ResponseEntity.ok(gateKeeperService.markEntry(id));
    }

    @PostMapping("/markExit/{id}")
    public ResponseEntity<String>markExit(@PathVariable Long id){
        return ResponseEntity.ok(gateKeeperService.markExit(id));
    }
    @PostMapping("/uploadImage")
    public ResponseEntity<String>uploadVisitorImage(@RequestParam("file")MultipartFile file){
        String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
        String publicUrl = "/content/"+fileName;
        String uploadPath = "C:/Spring network/Images/"+fileName;
        try{
            file.transferTo(new File(uploadPath));
        }
        catch(IOException e){
            LOGGER.error("Exception in uploading image: {}",e);
            return ResponseEntity.ok("Exception in uploading image");
        }

       return  ResponseEntity.ok(publicUrl);


    }

}
