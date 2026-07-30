package com.samruddhi.assessment_platform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samruddhi.assessment_platform.dto.AnswerSubmission;
import com.samruddhi.assessment_platform.dto.CandidateDashboardDto;
import com.samruddhi.assessment_platform.dto.SubmitTestRequest;
import com.samruddhi.assessment_platform.entity.Assessment;
import com.samruddhi.assessment_platform.entity.Question;
import com.samruddhi.assessment_platform.entity.Result;
import com.samruddhi.assessment_platform.entity.User;
import com.samruddhi.assessment_platform.repository.AssessmentRepository;
import com.samruddhi.assessment_platform.repository.QuestionRepository;
import com.samruddhi.assessment_platform.repository.ResultRepository;
import com.samruddhi.assessment_platform.repository.UserRepository;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Save Result
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    // Get All Results
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // Get Result By Id
    public Result getResultById(Long id) {

        return resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));

    }

    // Delete Result
    public void deleteResult(Long id) {

        Result result = getResultById(id);

        resultRepository.delete(result);

    }

    // Get Results of Particular User
    public List<Result> getResultsByUser(Long userId) {
        return resultRepository.findByUserId(userId);
    }

    // Candidate Dashboard
    public CandidateDashboardDto getDashboard(Long userId) {

        CandidateDashboardDto dto = new CandidateDashboardDto();

        dto.setTotalAssessments((int) assessmentRepository.count());

        List<Result> results = resultRepository.findByUserId(userId);

        dto.setTestsAttempted(results.size());

        if (results.isEmpty()) {

            dto.setHighestScore(0);
            dto.setAverageScore(0);
            dto.setRecentResults(results);

            return dto;

        }

        int highestScore = results.stream()
                .mapToInt(Result::getScore)
                .max()
                .orElse(0);

        double averageScore = results.stream()
                .mapToInt(Result::getScore)
                .average()
                .orElse(0);

        dto.setHighestScore(highestScore);
        dto.setAverageScore(averageScore);
        dto.setRecentResults(results);

        return dto;

    }

    // Submit Test
    public Result submitTest(SubmitTestRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Assessment assessment = assessmentRepository.findById(request.getAssessmentId())
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        List<Question> questions =
                questionRepository.findByAssessmentId(assessment.getId());

        int score = 0;

        for (Question question : questions) {

            for (AnswerSubmission answer : request.getAnswers()) {

                if (answer.getQuestionId().equals(question.getId())) {

                    if (question.getCorrectAnswer()
                            .equalsIgnoreCase(answer.getSelectedAnswer())) {

                        score++;

                    }

                }

            }

        }

        Result result = new Result();

        result.setUser(user);
        result.setAssessment(assessment);
        result.setScore(score);
        result.setSubmittedAt(LocalDateTime.now());

        return resultRepository.save(result);

    }

}