package com.example.VMSappdemo.Controller;

import com.example.VMSappdemo.Dto.Addressdto;
import com.example.VMSappdemo.Dto.Userdto;
import com.example.VMSappdemo.Enums.UserStatus;
import com.example.VMSappdemo.Service.AdminService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class Admin {

    private static Logger LOGGER = LoggerFactory.getLogger(Admin.class);

    @Autowired
    private AdminService adminService;

    @PostMapping("/createUser")
    public ResponseEntity<Long>createUser(@RequestBody Userdto userdto){
        return ResponseEntity.ok(adminService.createUser(userdto));
    }

    @PostMapping("/csvUpload")
    public ResponseEntity<List<String>> createUSerCSv(@RequestParam("file")MultipartFile file){
        LOGGER.info("file uploaded:{}",file.getOriginalFilename());
        List<String>response = new ArrayList<>();
        try{
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Iterable<CSVRecord>csvRecords=csvParser.getRecords();
            for(CSVRecord csvRecord:csvRecords){
                Userdto userdto = new Userdto();
                userdto.setName(csvRecord.get("name"));
                userdto.setEmail(csvRecord.get("email"));
                userdto.setPhone(csvRecord.get("phone"));
                userdto.setFlatNo(csvRecord.get("flatNo"));
                userdto.setRole(csvRecord.get("role"));
                userdto.setIdNumber(csvRecord.get("idNumber"));
                userdto.setStatus(UserStatus.ACTIVE);
                Addressdto addressdto = new Addressdto();
                addressdto.setLine1(csvRecord.get("line1"));
                addressdto.setLine2(csvRecord.get("line2"));
                addressdto.setCity(csvRecord.get("city"));
                addressdto.setPinCode(csvRecord.get("pincode"));
                addressdto.setCountry(csvRecord.get("country"));
                userdto.setAddress(addressdto);

                try{
                    Long id = adminService.createUser(userdto);
                    response.add("Created User"+userdto.getName()+" with id :"+id);
                }
                catch(Exception e){
                    response.add("Unable to created User "+userdto.getName()+" msg:"+e.getMessage());
                }

            }

        }
        catch(IOException e){
        }

        return ResponseEntity.ok(response);


    }
}
