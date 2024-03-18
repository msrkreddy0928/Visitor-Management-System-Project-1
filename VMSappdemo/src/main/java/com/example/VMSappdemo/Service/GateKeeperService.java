package com.example.VMSappdemo.Service;

import com.example.VMSappdemo.Dto.Addressdto;
import com.example.VMSappdemo.Dto.Visitdto;
import com.example.VMSappdemo.Dto.Visitordto;
import com.example.VMSappdemo.Entity.Address;
import com.example.VMSappdemo.Entity.Flat;
import com.example.VMSappdemo.Entity.Visit;
import com.example.VMSappdemo.Entity.Visitor;
import com.example.VMSappdemo.Enums.VisitStatus;
import com.example.VMSappdemo.Repo.FlatRepo;
import com.example.VMSappdemo.Repo.VisitRepo;
import com.example.VMSappdemo.Repo.VisitorRepo;
import com.example.VMSappdemo.RestException.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GateKeeperService {

    @Autowired
    private VisitorRepo visitorRepo;

    @Autowired
    private VisitRepo visitRepo;

    @Autowired
    private FlatRepo flatRepo;



    public Long createVisitor(Visitordto visitordto){
        Visitor visitor = Visitor.builder()
                .name(visitordto.getName())
                .email(visitordto.getEmail())
                .phone(visitordto.getPhone())
                .idNumber(visitordto.getIdNumber())
                .build();
        if (visitordto.getAddress() != null) {
            Addressdto addressdto = visitordto.getAddress();
            Address address = Address.builder()
                    .line1(addressdto.getLine1())
                    .line2(addressdto.getLine2())
                    .city(addressdto.getCity())
                    .pinCode(addressdto.getPinCode())
                    .country(addressdto.getCountry())
                    .build();
            visitor.setAddress(address);}
        visitor=visitorRepo.save(visitor);

        return visitor.getId();}

    public Visitordto getById(String id){
        Visitor visitor = visitorRepo.findByIdNumber(id);
        Visitordto visitordto=null;
        if(visitor!=null){
         visitordto =Visitordto.builder()
                .name(visitor.getName())
                .email(visitor.getEmail())
                .phone(visitor.getPhone())
                .idNumber(visitor.getIdNumber())
                .build();}


        return visitordto;}



    public Long createVisit(Visitdto visitdto) {
        Visitor visitor = visitorRepo.findByIdNumber(visitdto.getIdNumber());
        if(visitor == null) {
            throw new BadRequestException("Visitor doesn't exist");

        }
        Flat flat = flatRepo.findByNumber(visitdto.getFlatNumber());

        Visit visit = Visit.builder()
                .purpose(visitdto.getPurpose())
                .flat(flat)
                .status(VisitStatus.WAITING)
                .noOfPeople(visitdto.getNoOfPeople())
                .imageUrl(visitdto.getImageUrl())
                .visitor(visitor)
                //.createdBy(visitdto.getCreatedBy())
                .build();

        visit = visitRepo.save(visit);

        return visit.getId();

    }

    public String markEntry(Long id){
        Visit visit = visitRepo.findById(id).get();
        if(visit==null){
            throw new BadRequestException("Visitor doesn't exist");
        }
        if(visit.getStatus().equals(VisitStatus.APPROVED)){
            visit.setInTime(new Date());
            visitRepo.save(visit);
            return "Done";
        }
        else{
            throw new BadRequestException("Cannot Mark");
        }

    }

    public String markExit(Long id){
        Visit visit = visitRepo.findById(id).get();
        if(visit==null){
            throw new BadRequestException("Visitor doesn't exist");
        }
        if(visit.getStatus().equals(VisitStatus.APPROVED)&&visit.getInTime()!=null){
            visit.setOutTime(new Date());
            visit.setStatus(VisitStatus.COMPLETED);
            visitRepo.save(visit);
            return"completed";
        }
        else{
            throw new BadRequestException("cannot mark this");
        }

    }










}











