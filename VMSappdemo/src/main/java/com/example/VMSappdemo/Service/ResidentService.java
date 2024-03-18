package com.example.VMSappdemo.Service;

import com.example.VMSappdemo.Controller.Resdident;
import com.example.VMSappdemo.Dto.AllVisitsBy;
import com.example.VMSappdemo.Dto.Visitdto;
import com.example.VMSappdemo.Entity.Flat;
import com.example.VMSappdemo.Entity.User;
import com.example.VMSappdemo.Entity.Visit;
import com.example.VMSappdemo.Enums.VisitStatus;
import com.example.VMSappdemo.Repo.UserRepo;
import com.example.VMSappdemo.Repo.VisitRepo;
import com.example.VMSappdemo.RestException.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private VisitRepo visitRepo;

    @Autowired
    private UserRepo userRepo;


    public String updateService(Long id, VisitStatus visitStatus){
        Visit visit = visitRepo.findById(id).get();
        if(visit==null){
            throw new BadRequestException("Visit doesn't exist");

        }
        if(visit.getStatus().equals(VisitStatus.WAITING)){
            visit.setStatus(visitStatus);
            //visit.approvedBy(id)
            visitRepo.save(visit);
            return "Approved";
        }
        else{
           throw new BadRequestException("Visit Expired");
        }
    }

    @Transactional
    public List<Visitdto>getPendingVisit(Long id){
        List<Visitdto>list = new ArrayList<>();
        User user = userRepo.findById(id).get();
        if(user!=null){
            Flat flat = user.getFlat();

            List<Visit>visitList=visitRepo.findByStatusAndFlat(VisitStatus.WAITING,flat);
            for(Visit temp : visitList){
                list.add(fromVisit(temp));
            }

        }
        return list;
    }
    @Transactional
    public AllVisitsBy getAllVisits(Long id, Pageable pageable){
        AllVisitsBy allVisitsby = new AllVisitsBy();
        List<Visitdto>list = new ArrayList<>();
        User user = userRepo.findById(id).get();
        if(user!=null){
            Flat flat = user.getFlat();
            Page<Visit>visitPage = visitRepo.findAll(pageable);
            List<Visit>visitList = visitPage.stream().toList();
            allVisitsby.setTotalPage(visitPage.getTotalPages());
            allVisitsby.setTotalRow(visitPage.getTotalElements());
            for(Visit temp : visitList){
                list.add(fromVisit(temp));
            }

        }
        allVisitsby.setVisits(list);
        return allVisitsby;
    }

    public Visitdto fromVisit(Visit visit){
        Visitdto visitdto = Visitdto.builder()
                .imageUrl(visit.getImageUrl())
                .noOfPeople(visit.getNoOfPeople())
                .purpose(visit.getPurpose())
                .flatNumber(visit.getFlat().getNumber())
                .visitorName(visit.getVisitor().getName())
                .build();

return visitdto;

    }




}
