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

import com.myProject.BankApplication.Bankaccount;
import com.myProject.BankApplication.AccountRepo.CustomerAccountRepository;

@RestController
@RequestMapping("/account")
public class CustomerAccountcontroller {
    @Autowired
    private CustomerAccountRepository accountRepository;



    // create bank account for customers through cust_id
    @PostMapping("/add")
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
        Bankaccount addAccount(@RequestBody Bankaccount account){
            account.setDate_created(new Date());
            accountRepository.save(account);
            return account;
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
    @DeleteMapping("/delete/{cust_id}")
    ResponseEntity<String> delaccount(@PathVariable int cust_id){
        Optional<Bankaccount> customerOptional = accountRepository.findById(cust_id);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer account not found");
        }
        accountRepository.deleteById(cust_id);
        return ResponseEntity.ok("Customer account deleted successfully");
    }
}
