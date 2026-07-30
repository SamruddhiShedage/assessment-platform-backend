package com.samruddhi.assessment_platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samruddhi.assessment_platform.entity.Assessment;
import com.samruddhi.assessment_platform.repository.AssessmentRepository;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Assessment createAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Assessment getAssessmentById(Long id) {

        return assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));
    }

    // Update Assessment
    public Assessment updateAssessment(Long id, Assessment updatedAssessment) {

        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        assessment.setTitle(updatedAssessment.getTitle());
        assessment.setDescription(updatedAssessment.getDescription());
        assessment.setDuration(updatedAssessment.getDuration());
        assessment.setTotalMarks(updatedAssessment.getTotalMarks());

        return assessmentRepository.save(assessment);
    }

    // Delete Assessment
    public void deleteAssessment(Long id) {

        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        assessmentRepository.delete(assessment);
    }
}