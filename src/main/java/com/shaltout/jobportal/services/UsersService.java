package com.shaltout.jobportal.services;

import com.shaltout.jobportal.entity.JobSeekerProfile;
import com.shaltout.jobportal.entity.RecruiterProfile;
import com.shaltout.jobportal.entity.Users;
import com.shaltout.jobportal.repository.JobSeekerProfileRepository;
import com.shaltout.jobportal.repository.RecruiterProfileRepository;
import com.shaltout.jobportal.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = usersRepository.save(users);
        if(users.getUserTypeId().getUserTypeId()==1){
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }else{
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<Users> findByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
