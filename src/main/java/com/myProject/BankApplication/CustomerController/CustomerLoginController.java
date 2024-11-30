package com.myProject.BankApplication.CustomerController;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.BankApplication.Customer;
import com.myProject.BankApplication.CustomerRepo.CustomerRepository;

@RestController
@RequestMapping("/customerlogin")
public class CustomerLoginController {
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody Map<String, String> loginData){
        String email = loginData.get("email");
        String password = loginData.get("cust_pass");

        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Customer customer = customerOptional.get();
        if(!customer.getCust_pass().equals(password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password!! Please try again.");
        }
        return ResponseEntity.ok("Login successful!!");
    }
}
