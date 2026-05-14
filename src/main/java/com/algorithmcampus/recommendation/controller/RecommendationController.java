package com.algorithmcampus.recommendation.controller;

import com.algorithmcampus.global.common.response.CommonResponse;
import com.algorithmcampus.global.common.response.code.CommonSuccessCode;
import com.algorithmcampus.recommendation.dto.response.RecommendationResponse;
import com.algorithmcampus.recommendation.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 추천 컨트롤러
 *
 * @author : 권지영
 * @filename : RecommendationController
 * @since : 2026. 4. 23. 목요일
 */


@Tag(name = "Recommendation", description = "문제 추천 관련 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Operation(
            summary = "추천 문제 리스트 조회",
            description = """
                    사용자의 추천 문제 리스트를 조회합니다.

                    추천 문제는 매일 아침 갱신됩니다.
                    한 번의 요청으로 SIMILAR, WEAKNESS, LEVELUP, REVIEW 유형의 추천 문제가 리스트 형태로 반환됩니다.

                    추천 유형 설명:
                    - SIMILAR: 최근 풀이한 문제와 유사한 문제
                    - WEAKNESS: 사용자가 상대적으로 약한 알고리즘 유형의 문제
                    - LEVELUP: 현재 수준을 기준으로 한 단계 높은 문제
                    - REVIEW: 복습이 필요한 문제
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "추천 문제 리스트 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = RecommendationResponse.class)
                            ),
                            examples = @ExampleObject(
                                    name = "추천 문제 리스트 조회 성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "S000",
                                              "message": "요청에 성공했습니다.",
                                              "result": [
                                                {
                                                  "recommendationId": 1,
                                                  "leetcodeProblemId": 1,
                                                  "title": "Two Sum",
                                                  "difficulty": "Easy",
                                                  "problemUrl": "https://leetcode.com/problems/two-sum",
                                                  "recommendationReason": "최근 풀이한 Hash Table 유형과 유사한 문제입니다.",
                                                  "recommendationType": "SIMILAR",
                                                  "recommendationCreatedAt": "2026-05-14T08:00:00"
                                                },
                                                {
                                                  "recommendationId": 2,
                                                  "leetcodeProblemId": 3,
                                                  "title": "Longest Substring Without Repeating Characters",
                                                  "difficulty": "Medium",
                                                  "problemUrl": "https://leetcode.com/problems/longest-substring-without-repeating-characters",
                                                  "recommendationReason": "Sliding Window 유형 풀이 경험이 적어 추천되었습니다.",
                                                  "recommendationType": "WEAKNESS",
                                                  "recommendationCreatedAt": "2026-05-14T08:00:00"
                                                },
                                                {
                                                  "recommendationId": 3,
                                                  "leetcodeProblemId": 15,
                                                  "title": "3Sum",
                                                  "difficulty": "Medium",
                                                  "problemUrl": "https://leetcode.com/problems/3sum",
                                                  "recommendationReason": "현재 풀이 수준을 기준으로 한 단계 높은 난이도의 문제입니다.",
                                                  "recommendationType": "LEVELUP",
                                                  "recommendationCreatedAt": "2026-05-14T08:00:00"
                                                },
                                                {
                                                  "recommendationId": 4,
                                                  "leetcodeProblemId": 20,
                                                  "title": "Valid Parentheses",
                                                  "difficulty": "Easy",
                                                  "problemUrl": "https://leetcode.com/problems/valid-parentheses",
                                                  "recommendationReason": "이전에 풀이한 Stack 유형 문제로 복습을 위해 추천되었습니다.",
                                                  "recommendationType": "REVIEW",
                                                  "recommendationCreatedAt": "2026-05-14T08:00:00"
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 값",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "잘못된 요청 예시",
                                    value = """
                                            {
                                              "isSuccess": false,
                                              "code": "C001",
                                              "message": "잘못된 요청입니다.",
                                              "result": null
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public CommonResponse<List<RecommendationResponse>> getRecommendations(
            @Parameter(
                    description = "사용자 ID",
                    example = "1",
                    required = true
            )
            @RequestParam
            @NotNull(message = "사용자 ID는 필수입니다.")
            @Min(value = 1, message = "사용자 ID는 1 이상이어야 합니다.")
            Long userId
    ) {
        List<RecommendationResponse> response = recommendationService.getRecommendations(userId);

        return CommonResponse.success(CommonSuccessCode.OK, response);
    }
}
