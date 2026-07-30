package com.samruddhi.assessment_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samruddhi.assessment_platform.entity.Question;


public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByAssessmentId(Long assessmentId);

}