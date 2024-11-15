package com.myProject.BankApplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcomePage")
public class Welcome {
    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to Tech Bank!!";
    }
}
