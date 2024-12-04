package com.myProject.BankApplication.AccountController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.myProject.BankApplication.Bankaccount;
import com.myProject.BankApplication.Customer;
import com.myProject.BankApplication.AccountRepo.CustomerAccountRepository;
import com.myProject.BankApplication.CustomerRepo.CustomerRepository;

@RestController
@RequestMapping("/account")
public class CustomerAccountcontroller {
    @Autowired
    private CustomerAccountRepository accountRepository;
    private CustomerRepository customerRepository;



    // create bank account for customers through cust_id
    @PostMapping("/add/{custid}")
    //public ResponseEntity<String> addAccount(@RequestBody Bankaccount accountDetails){
        // if(customerRepository.existsById(accountDetails.getCust_id())){
        //     accountDetails.setDate_created(new Date());
        //     accountRepository.save(accountDetails);
        //     return ResponseEntity.ok("Account created successfully for customer ID: "+accountDetails.getCust_id());
        // }
        // else{
        //     return ResponseEntity.badRequest().body("Customer with ID: "+accountDetails.getCust_id()+" does not exist.");
        // }
        // if(!customerRepository.existsById(accountDetails.getCust_id()))
        //     return ResponseEntity.badRequest().body("Customer with ID: "+accountDetails.getCust_id()+" does not exist.");
        // else{
        //     accountDetails.setDate_created(new Date());
        //     accountRepository.save(accountDetails);
        //     return ResponseEntity.ok("Account created successfully for customer ID: "+accountDetails.getCust_id());
        // }
        Bankaccount addAccount(@PathVariable int custid, @RequestBody Bankaccount account){
            Optional<Customer> customerOptional = customerRepository.findById(custid);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                account.setCustomer(customer); 
                account.setDate_created(new Date());
                accountRepository.save(account);
                return account;
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + custid + " not found.");
            }
    }
    //}

    // get all account details
    @GetMapping("/getAllaccounts")
    List<Bankaccount> getAccounts(){
        List<Bankaccount> account = accountRepository.findAll();
        return account;
    }

    // get account details by acc_id
    @GetMapping("/getbyID/{accid}")
    Optional<Bankaccount> getaccountbyId(@PathVariable int accid){
        Optional<Bankaccount> account = accountRepository.findByAccid(accid);
        return account;
    }

    // Delete a account by cust_ID
    @DeleteMapping("/delete/{custid}")
    ResponseEntity<String> delaccount(@PathVariable int custid){
        Optional<Bankaccount> customerOptional = accountRepository.findById(custid);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer account not found");
        }
        accountRepository.deleteById(custid);
        return ResponseEntity.ok("Customer account deleted successfully");
    }

}
