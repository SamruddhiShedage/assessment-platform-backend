package com.samruddhi.assessment_platform.dto;

import java.util.List;

import com.samruddhi.assessment_platform.entity.Result;

public class CandidateDashboardDto {

    private int totalAssessments;
    private long testsAttempted;
    private int highestScore;
    private double averageScore;
    private List<Result> recentResults;

    public int getTotalAssessments() {
        return totalAssessments;
    }

    public void setTotalAssessments(int totalAssessments) {
        this.totalAssessments = totalAssessments;
    }

    public long getTestsAttempted() {
        return testsAttempted;
    }

    public void setTestsAttempted(long testsAttempted) {
        this.testsAttempted = testsAttempted;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Result> getRecentResults() {
        return recentResults;
    }

    public void setRecentResults(List<Result> recentResults) {
        this.recentResults = recentResults;
    }

}