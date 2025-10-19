package com.halks.distribution_erp.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("test")
public class TestController {

    @GetMapping("user")
    public String helloUser() {
        return "This is user!";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "This is admin!";
    }

}
