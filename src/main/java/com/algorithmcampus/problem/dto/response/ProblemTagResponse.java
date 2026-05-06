package com.algorithmcampus.problem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * Please explain the class!!!
 *
 * @author : 권지영
 * @filename : ProblemTagResponse
 * @since : 2026. 5. 1. 금요일
 */
@Getter
@Builder
@Schema(description = "문제 태그 응답 DTO")
public class ProblemTagResponse {
    @Schema(description = "태그 ID", example = "1")
    private Long tagId;
    @Schema(description = "태그명", example = "dynamic-programming")
    private String tagName;
    @Schema(description = "화면 표시용 태그명", example = "Dynamic Programming")
    private String displayName;
}

