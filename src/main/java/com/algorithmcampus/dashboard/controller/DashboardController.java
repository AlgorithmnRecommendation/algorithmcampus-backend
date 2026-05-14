package com.algorithmcampus.dashboard.controller;

import com.algorithmcampus.dashboard.dto.response.DashboardResponse;
import com.algorithmcampus.dashboard.service.DashboardService;
import com.algorithmcampus.global.common.exception.ErrorResponse;
import com.algorithmcampus.global.common.response.CommonResponse;
import com.algorithmcampus.global.common.response.code.CommonSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 대시보드 컨트롤러
 *
 * @author : 권지영
 * @filename : DashboardController
 * @since : 2026. 5. 14. 목요일
 */
@Tag(name = "Dashboard", description = "대시보드 API")
@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "대시보드 조회",
            description = "사용자의 총 풀이 수, 연속 풀이 일수, 추천 문제, 유형별 분석, 최근 풀이 기록을 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "대시보드 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DashboardResponse.class),
                            examples = @ExampleObject(
                                    name = "대시보드 조회 성공 예시",
                                    value = """
                                            {
                                              "code": "S000",
                                              "message": "요청이 성공했습니다.",
                                              "data": {
                                                "totalSolvedCount": 127,
                                                "streakCount": 5,
                                                "summaryMessage": "이번 주 8문제를 풀이 완료했습니다.",
                                                "recommendationMessage": "오늘의 추천 문제 25개가 준비되었습니다.",
                                                "recommendedProblems": [
                                                  {
                                                    "problemId": 1916,
                                                    "title": "최소비용 구하기",
                                                    "tier": "Gold 5",
                                                    "recommendationType": "weakness",
                                                    "tags": [
                                                      "그래프",
                                                      "다익스트라",
                                                      "최단경로"
                                                    ],
                                                    "reason": "최근 그래프 문제 풀이가 부족하여 추천된 문제입니다."
                                                  },
                                                  {
                                                    "problemId": 1260,
                                                    "title": "DFS와 BFS",
                                                    "tier": "Silver 2",
                                                    "recommendationType": "similar",
                                                    "tags": [
                                                      "DFS",
                                                      "BFS",
                                                      "그래프"
                                                    ],
                                                    "reason": "최근 풀이한 그래프 탐색 문제와 유사한 유형의 문제입니다."
                                                  },
                                                  {
                                                    "problemId": 2206,
                                                    "title": "벽 부수고 이동하기",
                                                    "tier": "Gold 3",
                                                    "recommendationType": "levelup",
                                                    "tags": [
                                                      "BFS",
                                                      "그래프"
                                                    ],
                                                    "reason": "현재 풀이 수준보다 한 단계 높은 난이도에 도전할 수 있는 문제입니다."
                                                  },
                                                  {
                                                    "problemId": 2178,
                                                    "title": "미로 탐색",
                                                    "tier": "Silver 1",
                                                    "recommendationType": "review",
                                                    "tags": [
                                                      "BFS",
                                                      "그래프"
                                                    ],
                                                    "reason": "이전에 풀이한 BFS 개념을 복습하기에 적합한 문제입니다."
                                                  },
                                                  {
                                                    "problemId": 11053,
                                                    "title": "가장 긴 증가하는 부분 수열",
                                                    "tier": "Silver 2",
                                                    "recommendationType": "similar",
                                                    "tags": [
                                                      "DP"
                                                    ],
                                                    "reason": "최근 풀이한 DP 문제와 유사한 패턴을 연습할 수 있는 문제입니다."
                                                  }
                                                ],
                                                "typeAnalysis": [
                                                  {
                                                    "typeName": "BFS/DFS",
                                                    "solvedCount": 61,
                                                    "status": "충분"
                                                  },
                                                  {
                                                    "typeName": "DP",
                                                    "solvedCount": 22,
                                                    "status": "부족"
                                                  }
                                                ],
                                                "recentSolvedProblems": [
                                                  {
                                                    "problemId": 7576,
                                                    "title": "토마토",
                                                    "tier": "Gold V",
                                                    "tags": [
                                                      "BFS",
                                                      "Python",
                                                      "백준"
                                                    ],
                                                    "solvedAtText": "2시간 전"
                                                  }
                                                ]
                                              }
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
    @GetMapping
    public CommonResponse<DashboardResponse> getDashboard() {
        DashboardResponse dashboard = dashboardService.getDashboard();

        return CommonResponse.success(CommonSuccessCode.OK, dashboard);
    }
}
