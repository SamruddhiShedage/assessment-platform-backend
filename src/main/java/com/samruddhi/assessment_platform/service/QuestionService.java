package com.samruddhi.assessment_platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samruddhi.assessment_platform.entity.Question;
import com.samruddhi.assessment_platform.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // Add Question
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Get All Questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Get Questions By Assessment
    public List<Question> getQuestionsByAssessment(Long assessmentId) {
        return questionRepository.findByAssessmentId(assessmentId);
    }

    // Get Question By ID
    public Question getQuestionById(Long id) {

        return questionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Question not found with ID : " + id));

    }

    // Update Question
    public Question updateQuestion(Long id, Question updatedQuestion) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Question not found with ID : " + id));

        question.setQuestionText(updatedQuestion.getQuestionText());
        question.setOptionA(updatedQuestion.getOptionA());
        question.setOptionB(updatedQuestion.getOptionB());
        question.setOptionC(updatedQuestion.getOptionC());
        question.setOptionD(updatedQuestion.getOptionD());
        question.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

        return questionRepository.save(question);

    }

    // Delete Question
    public void deleteQuestion(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Question not found with ID : " + id));

        questionRepository.delete(question);

    }

}