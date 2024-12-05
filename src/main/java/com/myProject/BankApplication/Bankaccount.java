package com.myProject.BankApplication;

import java.util.Date;

import jakarta.persistence.Column;

//import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    // @ManyToOne(fetch = FetchType.LAZY) // Ensures efficient loading
    // @JoinColumn(name = "Cust_ID", referencedColumnName = "Cust_ID", nullable = false) // Foreign key column
    // private Customer customer; // Relationship with Customer
    @Column(name = "Cust_ID")
    private int custid;
    @Column(name = "BALANCE")
    private float balance;
    @Column(name = "Acc_TYPE", nullable = false)
    private String acc_type;
    @Column(name = "DATE")
    private Date date;
    public void setDate_created(Date date_created){
        this.date= date_created;
    }
    // public void setCustomer(Customer customer) {
    //     this.customer = customer;
    // }
    // public void setCust_id(int custid) {
    //     this. custid = custid;
    // }
}
