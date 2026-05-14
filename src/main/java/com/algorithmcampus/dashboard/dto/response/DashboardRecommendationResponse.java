package com.algorithmcampus.dashboard.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 대시보드 추천 문제 반환 dto
 *
 * @author : 권지영
 * @filename : DashboardRecommendationResponse
 * @since : 2026. 5. 14. 목요일
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "대시보드 추천 문제 응답 DTO")
public class DashboardRecommendationResponse {

    @Schema(description = "문제 ID", example = "1916")
    private Long problemId;

    @Schema(description = "문제 제목", example = "최소비용 구하기")
    private String title;

    @Schema(description = "문제 난이도", example = "Gold 5")
    private String tier;

    @Schema(description = "추천 유형", example = "약점 보완")
    private String recommendationType;

    @Schema(description = "문제 태그 목록", example = "[\"그래프\", \"다익스트라\", \"최단경로\"]")
    private List<String> tags;

    @Schema(description = "추천 이유", example = "최근 그래프 문제 풀이가 부족하여 추천된 문제입니다.")
    private String reason;
}
