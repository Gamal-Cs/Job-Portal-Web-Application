package com.shaltout.jobportal.controller;

import com.shaltout.jobportal.entity.RecruiterProfile;
import com.shaltout.jobportal.entity.Users;
import com.shaltout.jobportal.repository.UsersRepository;
import com.shaltout.jobportal.services.RecruiterProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
@RequiredArgsConstructor
public class RecruiterProfileController {

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            Users users = usersRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());

            if(!recruiterProfile.isEmpty()){
                model.addAttribute("profile", recruiterProfile.get());
            }
        }
        return "recruiter_profile";
    }
}
