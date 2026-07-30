package com.samruddhi.assessment_platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.samruddhi.assessment_platform.dto.CandidateDashboardDto;
import com.samruddhi.assessment_platform.dto.SubmitTestRequest;
import com.samruddhi.assessment_platform.entity.Result;
import com.samruddhi.assessment_platform.service.ResultService;

@RestController
@RequestMapping("/results")
@CrossOrigin(origins = "http://localhost:3000")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // Save Result
    @PostMapping
    public Result saveResult(@RequestBody Result result) {

        return resultService.saveResult(result);

    }

    // Submit Test
    @PostMapping("/submit")
    public Result submitTest(@RequestBody SubmitTestRequest request) {

        return resultService.submitTest(request);

    }

    // Get All Results (Admin)
    @GetMapping
    public List<Result> getAllResults() {

        return resultService.getAllResults();

    }

    // Get Result By Id
    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id) {

        return resultService.getResultById(id);

    }

    // Delete Result
    @DeleteMapping("/{id}")
    public String deleteResult(@PathVariable Long id) {

        resultService.deleteResult(id);

        return "Result deleted successfully.";

    }

    // Get Results of Particular User
    @GetMapping("/user/{userId}")
    public List<Result> getResultsByUser(@PathVariable Long userId) {

        return resultService.getResultsByUser(userId);

    }

    // Candidate Dashboard
    @GetMapping("/dashboard/{userId}")
    public CandidateDashboardDto getDashboard(@PathVariable Long userId) {

        return resultService.getDashboard(userId);

    }

}