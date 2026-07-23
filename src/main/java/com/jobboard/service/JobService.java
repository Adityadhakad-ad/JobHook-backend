package com.jobboard.service;

import com.jobboard.entity.Job;
import com.jobboard.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    // 📌 Get all jobs
    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    // 📌 Get job by id
    public Job getJobById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // 📌 Create job
    public Job saveJob(Job job) {
        return repository.save(job);
    }

    // 📌 Delete job
    public String deleteJob(Long id) {
        repository.deleteById(id);
        return "Job Deleted Successfully";
    }

    // 📌 Update job
public Job updateJob(Long id, Job updatedJob) {

    Job job = repository.findById(id).orElse(null);

    if (job != null) {

        job.setTitle(updatedJob.getTitle());
        job.setCompany(updatedJob.getCompany());
        job.setLocation(updatedJob.getLocation());
        job.setSkills(updatedJob.getSkills());

        job.setDescription(updatedJob.getDescription());
        job.setSalary(updatedJob.getSalary());
        job.setExperience(updatedJob.getExperience());
        job.setJobType(updatedJob.getJobType());

        return repository.save(job);
    }

    return null;
}
    

    // 🔍 Search by Title, Company, Location, Skills, Job Type, Description
           public List<Job> searchJobs(String keyword) {
            return repository.searchJobs(keyword);
          }

    // 📍 Filter by location
    public List<Job> getByLocation(String location) {
        return repository.findByLocation(location);
    }
}