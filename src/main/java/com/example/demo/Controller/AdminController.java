package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Admin;
import com.example.demo.Repository.AdminRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin_login")
    public String showLoginForm() {
        return "admin_login";
    }

    @GetMapping("/index")
    public String welcome1() {
        return "index";
    }

    @PostMapping("/admin_login")
    public String login(@RequestParam String admin_email, @RequestParam String password, Model model) {
        Admin adm = adminRepository.findByEmail(admin_email);
        System.out.println(adm);
        if (adm != null && adm.getPassword().equals(password)) {
            // TODO: Implement successful login logic
            // For simplicity, redirect to a welcome page.
//            System.out.println("inside if "+user);

//            return "redirect:/welcome";
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/index")
    public String welcome() {
        return "index";
    }
    
    
//    
//    @RequestMapping(value = "/getme", method = RequestMethod.GET)
//    public String getme() {
//    	return "login";
//    }
//    
    
    @RequestMapping(value = "/getme", method = RequestMethod.POST)
    public String getmee() {
        return "redirect:/index";
    }
}
