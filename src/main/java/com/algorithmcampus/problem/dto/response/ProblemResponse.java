package com.algorithmcampus.problem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Please explain the class!!!
 *
 * @author : 권지영
 * @filename : ProblemResponse
 * @since : 2026. 5. 1. 금요일
 */
@Getter
@Builder
@Schema(description = "문제 응답 DTO")
public class ProblemResponse {
    @Schema(description = "LeetCode 문제 번호", example = "1")
    private Long problemId;
    @Schema(description = "문제명", example = "Two Sum")
    private String title;
    @Schema(description = "문제 URL", example = "https://leetcode.com/problems/two-sum/")
    private String problemUrl;
    @Schema(
            description = "문제 난이도",
            example = "EASY",
            allowableValues = {"EASY", "MEDIUM", "HARD"}
    )
    private String difficulty;
    @Schema(description = "정답 수", example = "15432000")
    private Long acceptedCount;
    @Schema(description = "제출 수", example = "28900000")
    private Long submissionCount;
    @Schema(description = "정답률", example = "53.4")
    private Double acceptanceRate;
    @Schema(description = "유료 문제 여부", example = "false")
    private Boolean isPremium;
    @Schema(description = "문제 태그 목록")
    private List<ProblemTagResponse> tags;
}
