package com.myProject.BankApplication.CustomerController;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myProject.BankApplication.Customer;
import com.myProject.BankApplication.CustomerRepo.CustomerRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/customerregister")
public class CustomerRegistercontroller {
    @Autowired
    CustomerRepository customerRepository;

    // Add a customer
    @PostMapping("/add")
    Customer addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    // Get all customers
    @GetMapping("/getAllcust")
    List<Customer> getCustomer(){
        List<Customer> customer = customerRepository.findAll();
        return customer;
    }

    // Get customer by ID
    @GetMapping("/getbyID/{custid}")
    Optional<Customer> getCustomer(@PathVariable int custid){
        Optional<Customer> customer = customerRepository.findById(custid);
        return customer;
    }

    //Get customer by their name
    @GetMapping("/getbyName/{custname}")
    Optional<Customer> getCustomerbyName(@PathVariable String custname){
        Optional<Customer> customer = customerRepository.findByCustname(custname);
        return customer;
    }

    
    // Delete a customer by ID
    @DeleteMapping("/delete/{custid}")
    ResponseEntity<String> delCustomer(@PathVariable int custid){
        Optional<Customer> customerOptional = customerRepository.findById(custid);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        customerRepository.deleteById(custid);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    // update customer details by their ID
    @PutMapping("/updatecustomer/{custid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int custid, @RequestBody Customer updatedCustomer){
        Optional<Customer> existingCustomerOpt = customerRepository.findById(custid);
        if(existingCustomerOpt.isPresent()){
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setCustname(updatedCustomer.getCustname());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setCust_pass(updatedCustomer.getCust_pass());
            existingCustomer.setCust_address(updatedCustomer.getCust_address());
            existingCustomer.setCust_phno(updatedCustomer.getCust_phno());

            customerRepository.save(existingCustomer);
            return ResponseEntity.ok(existingCustomer);
        }
        else{
            return ResponseEntity.notFound().build();
        }
        
    }

}