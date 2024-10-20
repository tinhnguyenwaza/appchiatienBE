package com.example.appchiatienBackEnd.Repository;

import com.example.appchiatienBackEnd.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface    BillDetailRepository extends JpaRepository<BillDetail, Long> {
    List<BillDetail> findByIdBillIn(List<Long> idBills);
}
