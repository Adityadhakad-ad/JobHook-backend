package com.jobboard.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Job Title
    private String title;

    // Company Name
    private String company;

    // Job Location
    private String location;

    // Required Skills
    private String skills;

    // Salary
    private String salary;

    // Job Type (Full Time, Part Time, Internship)
    private String jobType;

    // Experience Required
    private String experience;

    // Job Description
    @Column(length = 2000)
    private String description;
}