package com.myProject.BankApplication;

import jakarta.persistence.Column;

//import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Table(name = "BANKACCOUNT")
public class Bankaccount {
    
    @Id
    @Column(name = "Cust_ID")
    private int cust_id;
    @Column(name = "Acc_ID")
    private String acc_id;
    @Column(name = "BALANCE")
    private float balance;
    @Column(name = "Acc_TYPE")
    private String acc_type;
    @Column(name = "DATE")
    private String date;

}
