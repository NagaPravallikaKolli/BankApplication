package com.myProject.BankApplication;

import java.util.Date;

import jakarta.persistence.Column;

//import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@Table(name = "BANKACCOUNT")
public class Bankaccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Acc_ID")
    private int accid;
    //@Column(name = "Cust_ID")
    //private int custid;
    @Column(name = "BALANCE")
    private float balance;
    @Column(name = "Acc_TYPE", nullable = false)
    private String acc_type;
    @Column(name = "DATE")
    //@ManyToOne
    //@JoinColumn(name = "custid", nullable = false) // Foreign key column
    private Customer customer; // Relationship with Customer
    private Date date;
    public void setDate_created(Date date_created) {
        this.date= date_created;
    }

}
