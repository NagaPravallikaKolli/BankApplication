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
    @GetMapping("/getbyID/{cust_id}")
    Optional<Customer> getCustomer(@PathVariable int cust_id){
        Optional<Customer> customer = customerRepository.findById(cust_id);
        return customer;
    }

    //Get customer by their name
    @GetMapping("/getbyName/{custname}")
    Optional<Customer> getCustomerbyName(@PathVariable String custname){
        Optional<Customer> customer = customerRepository.findByCustname(custname);
        return customer;
    }

    
    // Delete a customer by ID
    @DeleteMapping("/delete/{cust_id}")
    ResponseEntity<String> delCustomer(@PathVariable int cust_id){
        Optional<Customer> customerOptional = customerRepository.findById(cust_id);
        if (customerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        customerRepository.deleteById(cust_id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    // update customer details by their ID
    @PutMapping("/updatecustomer/{cust_id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int cust_id, @RequestBody Customer updatedCustomer){
        Optional<Customer> existingCustomerOpt = customerRepository.findById(cust_id);
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