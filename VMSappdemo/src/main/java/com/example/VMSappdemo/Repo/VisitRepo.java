package com.example.VMSappdemo.Repo;

import com.example.VMSappdemo.Entity.Flat;
import com.example.VMSappdemo.Entity.Visit;
import com.example.VMSappdemo.Enums.VisitStatus;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit,Long> {
    List<Visit> findByStatusAndFlat(VisitStatus visitStatus, Flat flat);
    List<Visit>findByStatusAndCreateDateLessThanEqual(VisitStatus visitStatus,Date date);



}
