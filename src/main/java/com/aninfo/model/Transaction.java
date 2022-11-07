package com.aninfo.model;
import com.aninfo.model.OperationType;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private OperationType type;
    private Long cbu;
    private Double amount;

    public Transaction() {}

    public Transaction(OperationType type, Double amount, Long cbu) {
        this.type = type;
        this.amount = amount;
        this.cbu = cbu;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long id) {
        this.ID = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setCBU(Long cbu) {
        this.cbu = cbu;
    }

    public Long getCBU() {
        return cbu;
    }
}
