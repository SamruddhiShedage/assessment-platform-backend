package com.samruddhi.assessment_platform.dto;

import java.util.List;

public class ResultResponseDto {

    private int score;

    private int correct;

    private int wrong;

    private int attempted;

    private int totalQuestions;

    private double percentage;

    private List<QuestionAnalysisDto> questionAnalysis;

    public ResultResponseDto() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public List<QuestionAnalysisDto> getQuestionAnalysis() {
        return questionAnalysis;
    }

    public void setQuestionAnalysis(List<QuestionAnalysisDto> questionAnalysis) {
        this.questionAnalysis = questionAnalysis;
    }
}