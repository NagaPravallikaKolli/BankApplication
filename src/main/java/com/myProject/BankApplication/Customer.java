package com.myProject.BankApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @Column(name = "Cust_ID")
    private int cust_id;
    @Column(name = "Cust_NAME")
    private String cust_name;
    @Column(name = "Cust_ADDRESS")
    private String cust_address;
    @Column(name = "Cust_PHNO")
    private String cust_phno;
}
