package com.algorithmcampus.dashboard.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 대시보드 정보 반환 dto
 *
 * @author : 권지영
 * @filename : DashboardResponse
 * @since : 2026. 5. 14. 목요일
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "대시보드 응답 DTO")
public class DashboardResponse {
    @Schema(description = "총 풀이 수", example = "127")
    private Integer totalSolvedCount;
    @Schema(description = "연속 풀이 일수", example = "5")
    private Integer streakCount;
    @Schema(description = "대시보드 요약 문구", example = "이번 주 8문제를 풀이 완료했습니다.")
    private String summaryMessage;
    @Schema(description = "추천 문제 안내 문구", example = "오늘의 추천 문제 25개가 준비되었습니다.")
    private String recommendationMessage;
    @Schema(description = "오늘의 추천 문제 목록")
    private List<DashboardRecommendationResponse> recommendedProblems;
    @Schema(description = "유형별 분석 목록")
    private List<DashboardTypeAnalysisResponse> typeAnalysis;
    @Schema(description = "최근 풀이 기록 목록")
    private List<DashboardRecentSolvedResponse> recentSolvedProblems;
}
