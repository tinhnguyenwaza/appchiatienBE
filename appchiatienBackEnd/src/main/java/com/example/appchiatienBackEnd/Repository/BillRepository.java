package com.example.appchiatienBackEnd.Repository;

import com.example.appchiatienBackEnd.model.bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<bill, Long> {
    List<bill> findByStatus(String status);
}