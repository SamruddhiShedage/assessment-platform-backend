package com.samruddhi.assessment_platform.dto;

import java.util.List;

public class SubmitTestRequest {

    private Long userId;
    private Long assessmentId;
    private List<AnswerSubmission> answers;

    public SubmitTestRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public List<AnswerSubmission> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerSubmission> answers) {
        this.answers = answers;
    }
}