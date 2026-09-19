package com.shaltout.jobportal.controller;

import com.shaltout.jobportal.entity.Users;
import com.shaltout.jobportal.services.UsersService;
import com.shaltout.jobportal.services.UsersTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("usersTypes", usersTypeService.getAll());
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegisteration(@Valid Users user,  Model model){
        if(usersService.findByEmail(user.getEmail()).isPresent()){
            model.addAttribute("error", "Email already registered");
            model.addAttribute("usersTypes", usersTypeService.getAll());
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNew(user);
        return "dashboard";
    }
}
