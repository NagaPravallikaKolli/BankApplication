package com.myProject.BankApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ADMIN")
public class Admin {
    @Id
    @Column(name = "Admin_ID")
    private int admin_id;
    @Column(name = "Admin_NAME")
    private String admin_name;
    @Column(name = "Admin_PASSWORD")
    private String admin_pass;
}
