package com.myProject.BankApplication.CustomerService;
import com.myProject.BankApplication.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.BankApplication.CustomerRepo.CustomerRepository;

@Service
public class Customerservice {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Customer already exists");
        }
        return customerRepository.save(customer);

    }
}
