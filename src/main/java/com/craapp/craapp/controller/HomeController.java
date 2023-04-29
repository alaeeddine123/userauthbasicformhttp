package com.craapp.craapp.controller;



import com.craapp.craapp.entities.Appuser;
import com.craapp.craapp.services.AppuserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class HomeController {


    private final AppuserService appuserService ;

    public HomeController(AppuserService appuserService
                          ) {
        this.appuserService = appuserService;


    }

    @GetMapping
    public String testforall(){
        return "everyone can access";
    }

    @GetMapping("/home")
     public String home() {
    return ("<h1>Welcome to home page <h1>");
}

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String  adminLogged(){
        return "Welcome admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String  userLogged(){
        return "welcome user";
    }

    @PreAuthorize("hasRole('USER')")

    @GetMapping("/user/getusers")
    public List<Appuser> appusers(){
        return appuserService.findAll();
    }



}
