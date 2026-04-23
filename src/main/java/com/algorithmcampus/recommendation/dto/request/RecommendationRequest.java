package com.algorithmcampus.recommendation.dto.request;

import com.algorithmcampus.recommendation.domain.RecommendationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 추천 요청 dto
 *
 * @author : 권지영
 * @filename : RecommendationRequest
 * @since : 2026. 4. 23. 목요일
 */
@Getter
@NoArgsConstructor
@Schema(description = "추천 요청 DTO")
public class RecommendationRequest {

    @Schema(description = "추천을 받을 사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "추천 유형", example = "SIMILAR")
    private RecommendationType recommendationType;

    @Schema(description = "사용자의 풀이 이력 요약", example = "브루트포스와 구현 문제를 주로 풀었고, DP는 적게 풀었습니다.")
    private String solvedHistorySummary; // 요약이 어떻게 될지를 모르겠어서 일단 string으로 넣었습니다
}
