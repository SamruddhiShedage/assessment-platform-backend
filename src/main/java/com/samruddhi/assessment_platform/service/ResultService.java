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
import java.util.ArrayList;

import com.samruddhi.assessment_platform.dto.QuestionAnalysisDto;
import com.samruddhi.assessment_platform.dto.ResultResponseDto;
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
    public ResultResponseDto submitTest(SubmitTestRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Assessment assessment = assessmentRepository.findById(request.getAssessmentId())
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        List<Question> questions =
                questionRepository.findByAssessmentId(assessment.getId());

        int score = 0;
        int attempted = 0;

        List<QuestionAnalysisDto> analysisList = new ArrayList<>();

        for (Question question : questions) {

            AnswerSubmission submittedAnswer = null;

            for (AnswerSubmission answer : request.getAnswers()) {

                if (answer.getQuestionId().equals(question.getId())) {

                    submittedAnswer = answer;
                    break;

                }

            }

            QuestionAnalysisDto analysis = new QuestionAnalysisDto();

            analysis.setQuestionText(question.getQuestionText());

            analysis.setCorrectAnswer(question.getCorrectAnswer());

            analysis.setCorrectOption(
                    getOptionText(question, question.getCorrectAnswer())
            );

            if (submittedAnswer != null &&
                    submittedAnswer.getSelectedAnswer() != null &&
                    !submittedAnswer.getSelectedAnswer().isBlank()) {

                attempted++;

                analysis.setSelectedAnswer(submittedAnswer.getSelectedAnswer());

                analysis.setSelectedOption(
                        getOptionText(question,
                                submittedAnswer.getSelectedAnswer())
                );

                boolean correct = question.getCorrectAnswer()
                        .equalsIgnoreCase(submittedAnswer.getSelectedAnswer());

                analysis.setCorrect(correct);

                if (correct) {

                    score++;

                }

            } else {

                analysis.setSelectedAnswer(null);
                analysis.setSelectedOption(null);
                analysis.setCorrect(false);

            }

            analysisList.add(analysis);

        }

        Result result = new Result();

        result.setUser(user);
        result.setAssessment(assessment);
        result.setScore(score);
        result.setSubmittedAt(LocalDateTime.now());

        resultRepository.save(result);

        int totalQuestions = questions.size();

        int wrong = attempted - score;

        double percentage = totalQuestions == 0
                ? 0
                : (score * 100.0) / totalQuestions;

        ResultResponseDto response = new ResultResponseDto();

        response.setScore(score);
        response.setCorrect(score);
        response.setWrong(wrong);
        response.setAttempted(attempted);
        response.setTotalQuestions(totalQuestions);
        response.setPercentage(Math.round(percentage * 100.0) / 100.0);

        response.setQuestionAnalysis(analysisList);

        return response;
    }
    private String getOptionText(Question question, String option) {

        if (option == null) {
            return null;
        }

        switch (option.toUpperCase()) {

            case "A":
                return question.getOptionA();

            case "B":
                return question.getOptionB();

            case "C":
                return question.getOptionC();

            case "D":
                return question.getOptionD();

            default:
                return null;
        }
    }

}