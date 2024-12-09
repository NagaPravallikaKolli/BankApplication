package com.myProject.BankApplication.AccountController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    //get account details by customer id
    @GetMapping("getbyCustId/{custid}")
    Optional<Bankaccount> getaccountbycustId(@PathVariable int custid){
        Optional<Bankaccount> account = accountRepository.findBycustid(custid);
        return account;
    }

    //update account type by customer id
    @PutMapping("updateacctype/{custid}")
    public ResponseEntity<Bankaccount> updateAccType(@PathVariable int custid, @RequestBody Bankaccount updatedBankaccount){
        Optional<Bankaccount> existingBankOptional = accountRepository.findBycustid(custid);
        if(existingBankOptional.isPresent()){
            Bankaccount existingAcc = existingBankOptional.get();
            existingAcc.setAcc_type(updatedBankaccount.getAcc_type());

            accountRepository.save(existingAcc);
            return ResponseEntity.ok(existingAcc);
        }
        else{
            return ResponseEntity.notFound().build();
        }
        
    }

    //deposit money by account ID
    @PutMapping("/deposit/{accid}")
    public ResponseEntity<String> depositAmount(@PathVariable int accid, @RequestBody Map<String, Float> requestBody) {
        Optional<Bankaccount> existingBankOptional = accountRepository.findById(accid);
        float amount = requestBody.get("amount");
        if (existingBankOptional.isPresent()) {
            Bankaccount existingAcc = existingBankOptional.get();
            float newBalance = existingAcc.getBalance() + amount;
            existingAcc.setBalance(newBalance);
            accountRepository.save(existingAcc);
            return ResponseEntity.ok("Amount deposited successfully..");
        }else{
            return ResponseEntity.badRequest().body("Account not found");
        }
   }

   @PutMapping("/withdraw/{accid}")
   public ResponseEntity<String> withdrawAmount(@PathVariable int accid, @RequestBody Map<String, Float> requestBody) {
       Optional<Bankaccount> existingBankOptional = accountRepository.findById(accid);
       float amount = requestBody.get("amount");
       if (existingBankOptional.isPresent()) {
           Bankaccount existingAcc = existingBankOptional.get();
           if(existingAcc.getBalance()>amount){
                float newBalance = existingAcc.getBalance() - amount;
                existingAcc.setBalance(newBalance);
                accountRepository.save(existingAcc);
                return ResponseEntity.ok("Withdraw Successful..");
           }else{
                float currentBalance = existingAcc.getBalance();
                return ResponseEntity.badRequest().body("Withdrawal amount exceeds current balance: "+currentBalance);         
            }
       }else{
           return ResponseEntity.badRequest().body("Account not found");
       }
  }

    // Delete a account by Account ID
    @DeleteMapping("/deleteByaccid/{accid}")
    ResponseEntity<String> deleteaccount(@PathVariable int accid){
        Optional<Bankaccount> cusOptional = accountRepository.findByAccid(accid);
        if (cusOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer account not found");
        }
        accountRepository.deleteById(accid);
        return ResponseEntity.ok("Customer account deleted successfully");
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