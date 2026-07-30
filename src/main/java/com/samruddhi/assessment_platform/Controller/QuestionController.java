package com.samruddhi.assessment_platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.samruddhi.assessment_platform.entity.Question;
import com.samruddhi.assessment_platform.service.QuestionService;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Add Question
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // Get All Questions
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Get Questions By Assessment
    @GetMapping("/assessment/{assessmentId}")
    public List<Question> getQuestionsByAssessment(
            @PathVariable Long assessmentId) {

        return questionService.getQuestionsByAssessment(assessmentId);

    }

    // Get Question By ID
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {

        return questionService.getQuestionById(id);

    }

    // Update Question
    @PutMapping("/{id}")
    public Question updateQuestion(
            @PathVariable Long id,
            @RequestBody Question question) {

        return questionService.updateQuestion(id, question);

    }

    // Delete Question
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {

        questionService.deleteQuestion(id);

    }

}