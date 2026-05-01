package com.algorithmcampus.problem.service;

import com.algorithmcampus.problem.dto.response.ProblemResponse;
import com.algorithmcampus.problem.dto.response.ProblemTagResponse;

import java.util.List;

/**
 * Please explain the class!!!
 *
 * @author : 권지영
 * @filename : ProblemService
 * @since : 2026. 5. 1. 금요일
 */
public interface ProblemService {

    ProblemResponse getProblemById(Long problemId);

    List<ProblemResponse> getProblems(String tag, String difficulty, int page, int size);

    List<ProblemTagResponse> getProblemTags();

}

