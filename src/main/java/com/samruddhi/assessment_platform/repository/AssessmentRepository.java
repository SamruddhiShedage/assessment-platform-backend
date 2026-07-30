package com.samruddhi.assessment_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samruddhi.assessment_platform.entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}