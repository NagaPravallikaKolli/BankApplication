package com.myProject.BankApplication.CustomerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myProject.BankApplication.Customer;

@RestController
@RequestMapping("/customerapi")
public class Customercontroller {
    @Autowired
    private CustomerService customerservice;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer){
        try{
            customerservice.registerCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successfull !!");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
