package com.example.appchiatienBackEnd.Service;

import com.example.appchiatienBackEnd.Repository.BillDetailRepository;
import com.example.appchiatienBackEnd.Repository.BillRepository;
import com.example.appchiatienBackEnd.Repository.CommomRepository;
import com.example.appchiatienBackEnd.model.BillDetail;
import com.example.appchiatienBackEnd.model.Member;
import com.example.appchiatienBackEnd.model.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CommomService {

    @Autowired
    private CommomRepository memberRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member updateMember(Long id, Member member) {
        Member existingMember = getMemberById(id);
        // Cập nhật thông tin thành viên (ví dụ cập nhật tên, email, v.v.)
        existingMember.setName(member.getName());
        // Lưu thay đổi
        return memberRepository.save(existingMember);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public bill createBill(bill bill) {
        return billRepository.save(bill);
    }

    public List<bill> getAllBills() {
        return billRepository.findAll();
    }

    public bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    public bill updateBill(Long id, bill billDetails) {
        return billRepository.findById(id).map(bill -> {
            bill.setStatus(billDetails.getStatus());
            bill.setIdUser(billDetails.getIdUser());
            bill.setTotalMoney(billDetails.getTotalMoney());
            bill.setTotalPeople(billDetails.getTotalPeople());
            return billRepository.save(bill);
        }).orElse(null);
    }

    public boolean deleteBill(Long id) {
        return billRepository.findById(id).map(bill -> {
            billRepository.delete(bill);
            return true;
        }).orElse(false);
    }

    public BillDetail createBillDetail(BillDetail billDetail) {
        return billDetailRepository.save(billDetail);
    }

    public List<BillDetail> getAllBillDetails() {
        return billDetailRepository.findAll();
    }

    public BillDetail getBillDetailById(Long id) {
        return billDetailRepository.findById(id).orElse(null);
    }

    public BillDetail updateBillDetail(Long id, BillDetail billDetailDetails) {
        return billDetailRepository.findById(id).map(billDetail -> {
            billDetail.setName(billDetailDetails.getName());
            billDetail.setExpenseAmount(billDetailDetails.getExpenseAmount());
            billDetail.setIncomeAmount(billDetailDetails.getIncomeAmount());
            return billDetailRepository.save(billDetail);
        }).orElse(null);
    }

    public boolean deleteBillDetail(Long id) {
        return billDetailRepository.findById(id).map(billDetail -> {
            billDetailRepository.delete(billDetail);
            return true;
        }).orElse(false);
    }

    public List<BillDetail> saveBillDetails(List<BillDetail> billDetails) {
        return billDetailRepository.saveAll(billDetails);
    }

    public List<BillDetail> getAllBillDetails(List<Long> idBills) {
        return billDetailRepository.findByIdBillIn(idBills);
    }

    public List<bill> getBillsWithStatusY() {
        return billRepository.findByStatus("Y");
    }
}
