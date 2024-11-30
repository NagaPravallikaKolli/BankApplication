package com.myProject.BankApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Cust_ID")
    private Integer cust_id;
    @Column(name = "Cust_NAME", nullable = false)
    private String custname;
    @Column(name = "Cust_EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "Cust_PASS", nullable = false)
    private String cust_pass;
    @Column(name = "Cust_ADDRESS", nullable = false)
    private String cust_address;
    @Column(name = "Cust_PHNO", nullable = false)
    private String cust_phno;
}
