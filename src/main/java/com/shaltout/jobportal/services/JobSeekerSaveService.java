package com.shaltout.jobportal.services;

import com.shaltout.jobportal.entity.JobPostActivity;
import com.shaltout.jobportal.entity.JobSeekerProfile;
import com.shaltout.jobportal.entity.JobSeekerSave;
import com.shaltout.jobportal.repository.JobSeekerSaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerSaveService {
    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public List<JobSeekerSave> getCandidateJob(JobSeekerProfile userAccountId){
        return jobSeekerSaveRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerSave> getJobCandidates(JobPostActivity job) {
        return jobSeekerSaveRepository.findByJob(job);
    }
}
