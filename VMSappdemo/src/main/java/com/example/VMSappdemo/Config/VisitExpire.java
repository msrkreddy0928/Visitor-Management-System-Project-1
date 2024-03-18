package com.example.VMSappdemo.Config;

import com.example.VMSappdemo.Entity.Visit;
import com.example.VMSappdemo.Enums.VisitStatus;
import com.example.VMSappdemo.Repo.VisitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
@Configuration
public class VisitExpire {

    private static Logger LOGGER = LoggerFactory.getLogger(VisitExpire.class);

    @Autowired
    VisitRepo visitRepo;

    @Scheduled(fixedDelay = 3000)
    public void markVisitExpire(){
        LOGGER.info("Marking visits as expire");
        Date date = new Date();
        List<Visit>visitList=visitRepo.findByStatusAndCreateDateLessThanEqual(VisitStatus.WAITING,date);
        for(Visit visit : visitList){
            visit.setStatus(VisitStatus.EXPIRED);}
        visitRepo.saveAll(visitList);


    }


}
