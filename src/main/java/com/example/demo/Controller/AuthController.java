package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    
    public String showSignupForm(Model model) { 
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/login";

    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user != null && user.getPassword().equals(password)) {
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
    
    
    
    @RequestMapping(value = "/getme", method = RequestMethod.GET)
    public String getme() {
    	return "login";
    }
    
    
    @RequestMapping(value = "/getme", method = RequestMethod.POST)
    public String getmee() {
        return "redirect:/index";
    }
}
