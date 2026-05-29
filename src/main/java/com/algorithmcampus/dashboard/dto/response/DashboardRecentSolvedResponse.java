package com.algorithmcampus.dashboard.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 대시보드 최근 풀이기록 반환 dto
 *
 * @author : 권지영
 * @filename : DashboardRecentSolvedResponse
 * @since : 2026. 5. 14. 목요일
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "대시보드 최근 풀이 기록 응답 DTO")
public class DashboardRecentSolvedResponse {

    @Schema(description = "문제 ID", example = "7576")
    private Long problemId;

    @Schema(description = "문제 제목", example = "토마토")
    private String title;

    @Schema(description = "문제 난이도", example = "Gold V")
    private String tier;

    @Schema(description = "태그 목록", example = "[\"BFS\", \"Python\", \"백준\"]")
    private List<String> tags;

    @Schema(description = "풀이 시점", example = "2시간 전")
    private String solvedAtText;
}
