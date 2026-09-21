package com.shaltout.jobportal.controller;

import com.shaltout.jobportal.entity.JobPostActivity;
import com.shaltout.jobportal.services.JobPostActivityService;
import com.shaltout.jobportal.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;

    @GetMapping("job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model){
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);
        model.addAttribute("jobDetails",jobDetails);
        model.addAttribute("user",usersService.getCurrentUserProfile());
        return "job-details";
    }
}
