package com.example.VMSappdemo.Repo;

import com.example.VMSappdemo.Entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepo extends JpaRepository<Visitor,Long> {
    Visitor findByIdNumber(String id);
}
