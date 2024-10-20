package com.example.appchiatienBackEnd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bill_details")
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "expense_amount")
    private Double expenseAmount;

    @Column(name = "income_amount")
    private Double incomeAmount;

    @Column(name = "id_bill")
    private Long idBill;


    public Long getId() {
        return id;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }


}
