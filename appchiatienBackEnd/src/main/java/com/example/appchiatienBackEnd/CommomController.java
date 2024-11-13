package com.example.appchiatienBackEnd;

import com.example.appchiatienBackEnd.Service.CommomService;
import com.example.appchiatienBackEnd.model.BillDetail;
import com.example.appchiatienBackEnd.model.Member;
import com.example.appchiatienBackEnd.model.bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/common")
public class CommomController {

    @Autowired
    private CommomService commomService;

    @CrossOrigin(origins = "https://appchitienfe-production.up.railway.app")
    @GetMapping("")
        public ResponseEntity<String> getUser(){


            return ResponseEntity.ok("Hello tinh tinh nguyen");
        }

    @PostMapping("/addMenber")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member newMember = commomService.addMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = commomService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Lấy thông tin thành viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") Long id) {
        Member member = commomService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // Cập nhật thông tin thành viên
    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable("id") Long id, @RequestBody Member member) {
        Member updatedMember = commomService.updateMember(id, member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    // Xóa thành viên theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id) {
        commomService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Không trả về nội dung khi xóa thành công
    }


    @PostMapping("/addBill")
    public ResponseEntity<bill> createBill(@RequestBody bill bill) {
        bill createdBill = commomService.createBill(bill);
        return ResponseEntity.ok(createdBill);
    }

    // Lấy danh sách tất cả Bill
    @GetMapping("/getBill")
    public ResponseEntity<List<bill>> getAllBills() {
        List<bill> bills = commomService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    // Lấy thông tin Bill theo ID
    @GetMapping("/getBill/{id}")
    public ResponseEntity<bill> getBillById(@PathVariable Long id) {
        bill bill = commomService.getBillById(id);
        if (bill != null) {
            return ResponseEntity.ok(bill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cập nhật thông tin Bill
    @PutMapping("/updateBill/{id}")
    public ResponseEntity<bill> updateBill(@PathVariable Long id, @RequestBody bill billDetails) {
        bill updatedBill = commomService.updateBill(id, billDetails);
        if (updatedBill != null) {
            return ResponseEntity.ok(updatedBill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa Bill theo ID
    @DeleteMapping("/deleteBill/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        boolean deleted = commomService.deleteBill(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addBillDetail")
    public ResponseEntity<BillDetail> createBillDetail(@RequestBody BillDetail billDetail) {
        BillDetail createdBillDetail = commomService.createBillDetail(billDetail);
        return ResponseEntity.ok(createdBillDetail);
    }

    // Lấy thông tin tất cả BillDetail
    @GetMapping("/getBillDetail")
    public ResponseEntity<List<BillDetail>> getAllBillDetails() {
        List<BillDetail> billDetails = commomService.getAllBillDetails();
        return ResponseEntity.ok(billDetails);
    }

    // Lấy thông tin BillDetail theo ID
    @GetMapping("/getBillDetail/{id}")
    public ResponseEntity<BillDetail> getBillDetailById(@PathVariable Long id) {
        BillDetail billDetail = commomService.getBillDetailById(id);
        if (billDetail != null) {
            return ResponseEntity.ok(billDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cập nhật BillDetail
    @PutMapping("/updateBillDetail/{id}")
    public ResponseEntity<BillDetail> updateBillDetail(@PathVariable Long id, @RequestBody BillDetail billDetailDetails) {
        BillDetail updatedBillDetail = commomService.updateBillDetail(id, billDetailDetails);
        if (updatedBillDetail != null) {
            return ResponseEntity.ok(updatedBillDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa BillDetail
    @DeleteMapping("/deleteBillDetail/{id}")
    public ResponseEntity<Void> deleteBillDetail(@PathVariable Long id) {
        boolean deleted = commomService.deleteBillDetail(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/getMaxBillId")
    public ResponseEntity<bill> getBillWithMaxId() {
        List<bill> bills = commomService.getAllBills();

        Optional<bill> maxBill = bills.stream().max(Comparator.comparing(bill::getId));

        if (maxBill.isPresent()) {
            return ResponseEntity.ok(maxBill.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/addAllBillDetail")
    public ResponseEntity<List<BillDetail>> saveBillDetails(@RequestBody List<BillDetail> billDetails) {
        List<BillDetail> savedBillDetails = commomService.saveBillDetails(billDetails);
        return ResponseEntity.ok(savedBillDetails);
    }

    @GetMapping("/getBillDetailToIDBill")
    public ResponseEntity<List<BillDetail>> getAllBillDetailsToIDBill() {
        List<bill> bills = commomService.getBillsWithStatusY();

        List<Long> billIds = bills.stream()
                .map(bill::getId)  // Lấy ID từ mỗi bill
                .collect(Collectors.toList());  // Chuyển sang List

        List<BillDetail> billDetails = commomService.getAllBillDetails(billIds);
        return ResponseEntity.ok(billDetails);
    }

    @GetMapping("/getBillToStatus")
    public ResponseEntity<List<bill>> getAllBillToStatus() {
        List<bill> bills = commomService.getBillsWithStatusY();
        return ResponseEntity.ok(bills);
    }
}
