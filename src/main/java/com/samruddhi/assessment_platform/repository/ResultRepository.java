package com.samruddhi.assessment_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samruddhi.assessment_platform.entity.Result;


public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByUserId(Long userId);

    long countByUserId(Long userId);

    List<Result> findTop1ByUserIdOrderByScoreDesc(Long userId);
}