package com.shaltout.jobportal.services;

import com.shaltout.jobportal.entity.UsersType;
import com.shaltout.jobportal.repository.UsersTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersTypeService {
    private final UsersTypeRepository usersTypeRepository;
    
    public List<UsersType> getAll(){
        return usersTypeRepository.findAll();
    }
}
