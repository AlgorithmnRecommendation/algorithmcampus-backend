package com.algorithmcampus.recommendation.dto.response;

import com.algorithmcampus.recommendation.domain.RecommendationType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 추천 문제 응답 dto
 *
 * @author : 권지영
 * @filename : response
 * @since : 2026. 4. 16. 목요일
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "추천 문제 응답 DTO")
public class RecommendationResponse {

    @Schema(description = "추천 ID", example = "1")
    private Long recommendationId;

    @Schema(description = "리트코드 문제 ID", example = "1")
    private Long leetcodeProblemId;

    @Schema(description = "문제 제목", example = "Two Sum")
    private String title;

    @Schema(description = "문제 난이도", example = "Easy")
    private String difficulty;

    @Schema(description = "문제 URL", example = "https://leetcode.com/problems/two-sum")
    private String problemUrl;

    @Schema(description = "추천 이유", example = "최근 풀이한 Hash Table 유형과 유사한 문제입니다.")
    private String recommendationReason;

    @Schema(description = "추천 유형", example = "SIMILAR")
    private RecommendationType recommendationType;

    @Schema(description = "추천 생성 시간", example = "2026-05-14T08:00:00")
    private LocalDateTime recommendationCreatedAt;
}
