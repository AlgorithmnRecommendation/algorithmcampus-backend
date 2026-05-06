package com.algorithmcampus.recommendation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Schema(description = "문제 ID", example = "1001")
    private Long problemId;

    @Schema(description = "문제 제목", example = "A+B")
    private String title;

    @Schema(description = "문제 난이도", example = "Silver 3")
    private String tier;

    @Schema(description = "추천 이유", example = "사용자의 풀이 이력 기반으로 추천된 문제입니다.")
    private String reason;
}
