package com.myProject.BankApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USERS")
public class Users {

    @Id
    @Column(name = "User_ID")
    private int user_id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String pass;
}
