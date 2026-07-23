package com.jobboard.controller;

import com.jobboard.entity.Job;
import com.jobboard.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    // 📌 Get all OR filter by location
    @GetMapping
    public List<Job> getJobs(@RequestParam(required = false) String location) {

        if (location != null) {
            return service.getByLocation(location);
        }

        return service.getAllJobs();
    }

    // 📌 Get by id
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return service.getJobById(id);
    }

    // 📌 Create job
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return service.saveJob(job);
    }

    // 📌 Update job
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id,
                         @RequestBody Job job) {
        return service.updateJob(id, job);
    }

    // 📌 Delete job
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        return service.deleteJob(id);
    }

    // 🔍 Search by Title, Company, Location, Skills, Job Type, Description
       @GetMapping("/search")
       public List<Job> searchJobs(@RequestParam String keyword) {
        return service.searchJobs(keyword);
      }
}