package com.algorithmcampus.dashboard.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 대시보드 풀이 유형 분석 반환 dto
 *
 * @author : 권지영
 * @filename : DashboardAnalysisResponse
 * @since : 2026. 5. 14. 목요일
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "대시보드 유형별 분석 응답 DTO")
public class DashboardTypeAnalysisResponse {

    @Schema(description = "알고리즘 유형", example = "BFS/DFS")
    private String typeName;

    @Schema(description = "풀이 수", example = "61")
    private Integer solvedCount;

    @Schema(description = "분석 상태", example = "충분")
    private String status;
}
