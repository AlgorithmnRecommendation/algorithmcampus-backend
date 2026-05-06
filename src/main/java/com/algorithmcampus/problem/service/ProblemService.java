package com.algorithmcampus.problem.service;

import com.algorithmcampus.problem.dto.response.ProblemResponse;
import com.algorithmcampus.problem.dto.response.ProblemTagResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 문제 서비스
 *
 * @author : 권지영
 * @filename : ProblemService
 * @since : 2026. 5. 5. 화요일
 */
@Service
public class ProblemService {
    public ProblemResponse getProblemById(Long problemId) {
        return null;
    }
    public List<ProblemResponse> getProblems(String tag, String difficulty, int page, int size) {
        return Collections.emptyList();
    }
    public List<ProblemTagResponse> getProblemTags() {
        return Collections.emptyList();
    }
}

