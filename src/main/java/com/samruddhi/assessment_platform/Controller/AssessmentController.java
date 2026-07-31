package com.samruddhi.assessment_platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.samruddhi.assessment_platform.entity.Assessment;
import com.samruddhi.assessment_platform.service.AssessmentService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public Assessment createAssessment(@RequestBody Assessment assessment) {
        return assessmentService.createAssessment(assessment);
    }

    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public Assessment getAssessmentById(@PathVariable Long id) {
        return assessmentService.getAssessmentById(id);
    }

    // Update Assessment
    @PutMapping("/{id}")
    public Assessment updateAssessment(
            @PathVariable Long id,
            @RequestBody Assessment assessment) {

        return assessmentService.updateAssessment(id, assessment);
    }

    // Delete Assessment
    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable Long id) {

        assessmentService.deleteAssessment(id);

        return "Assessment deleted successfully.";
    }
}