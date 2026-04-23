package com.algorithmcampus.recommendation.controller;

import com.algorithmcampus.global.common.response.CommonResponse;
import com.algorithmcampus.global.common.response.code.CommonSuccessCode;
import com.algorithmcampus.recommendation.dto.request.RecommendationRequest;
import com.algorithmcampus.recommendation.dto.response.RecommendationResponse;
import com.algorithmcampus.global.common.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 추천 컨트롤러
 *
 * @author : 권지영
 * @filename : RecommendationController
 * @since : 2026. 4. 23. 목요일
 */
@Tag(name = "Recommendation", description = "추천 API")
@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    @Operation(
            summary = "추천 문제 조회",
            description = "사용자 정보와 추천 유형을 기준으로 추천 문제 목록을 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "추천 문제 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RecommendationListResponse.class),
                            examples = @ExampleObject(
                                    name = "추천 문제 조회 성공 예시",
                                    value = """
                                            {
                                              "code": "S000",
                                              "message": "요청이 성공했습니다.",
                                              "data": [
                                                {
                                                  "problemId": 1001,
                                                  "title": "A+B",
                                                  "tier": "Silver 3",
                                                  "reason": "사용자의 풀이 이력 기반으로 추천된 문제입니다."
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "잘못된 요청 예시",
                                    value = """
                                            {
                                              "code": "E400",
                                              "message": "잘못된 요청입니다."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "서버 내부 오류 예시",
                                    value = """
                                            {
                                              "code": "E500",
                                              "message": "서버 내부 오류입니다."
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public CommonResponse<List<RecommendationResponse>> getRecommendations(
            @RequestBody RecommendationRequest request
    ) {
        List<RecommendationResponse> recommendations = List.of(
                RecommendationResponse.builder()
                        .problemId(1001L)
                        .title("A+B")
                        .tier("Silver 3")
                        .reason("사용자의 풀이 이력 기반으로 추천된 문제입니다.")
                        .build()
        );

        return CommonResponse.success(CommonSuccessCode.OK, recommendations);
    }

    @Schema(name = "RecommendationListResponse", description = "추천 문제 목록 공통 응답")
    private static class RecommendationListResponse {

        @Schema(description = "응답 코드", example = "S000")
        public String code;

        @Schema(description = "응답 메시지", example = "요청이 성공했습니다.")
        public String message;

        @ArraySchema(schema = @Schema(implementation = RecommendationResponse.class))
        public List<RecommendationResponse> data;
    }
}