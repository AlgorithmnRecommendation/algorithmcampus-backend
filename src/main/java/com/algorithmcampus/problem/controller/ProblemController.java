package com.algorithmcampus.problem.controller;

import com.algorithmcampus.global.common.response.CommonResponse;
import com.algorithmcampus.global.common.response.code.CommonSuccessCode;
import com.algorithmcampus.problem.dto.response.ProblemResponse;
import com.algorithmcampus.problem.dto.response.ProblemTagResponse;
import com.algorithmcampus.problem.service.ProblemService;
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
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Please explain the class!!!
 *
 * @author : 권지영
 * @filename : ProblemController
 * @since : 2026. 5. 1. 금요일
 */

@Validated
@Tag(name = "Problem", description = "문제 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Operation(
            summary = "알고리즘 태그 목록 조회",
            description = "문제에 사용되는 알고리즘 태그 목록을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "알고리즘 태그 목록 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProblemTagResponse.class)),
                    examples = @ExampleObject(
                            name = "알고리즘 태그 목록 조회 성공 예시",
                            value = """
                                    {
                                      "isSuccess": true,
                                      "code": "S000",
                                      "message": "요청에 성공했습니다.",
                                      "result": [
                                        {
                                          "tagId": 1,
                                          "tagName": "array",
                                          "displayName": "Array"
                                        },
                                        {
                                          "tagId": 2,
                                          "tagName": "hash-table",
                                          "displayName": "Hash Table"
                                        },
                                        {
                                          "tagId": 3,
                                          "tagName": "dynamic-programming",
                                          "displayName": "Dynamic Programming"
                                        }
                                      ]
                                    }
                                    """
                    )
            )
    )
    @GetMapping("/tags")
    public CommonResponse<List<ProblemTagResponse>> getProblemTags() {
        List<ProblemTagResponse> response = problemService.getProblemTags();
        return CommonResponse.success(CommonSuccessCode.OK, response);
    }

    @Operation(
            summary = "문제 단일 조회",
            description = "LeetCode 문제 번호를 기준으로 문제 상세 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "문제 단일 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class),
                            examples = @ExampleObject(
                                    name = "문제 단일 조회 성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "S000",
                                              "message": "요청에 성공했습니다.",
                                              "result": {
                                                "problemId": 1,
                                                "title": "Two Sum",
                                                "problemUrl": "https://leetcode.com/problems/two-sum/",
                                                "difficulty": "EASY",
                                                "acceptedCount": 15432000,
                                                "submissionCount": 28900000,
                                                "acceptanceRate": 53.4,
                                                "isPremium": false,
                                                "tags": [
                                                  {
                                                    "tagId": 1,
                                                    "tagName": "array",
                                                    "displayName": "Array"
                                                  },
                                                  {
                                                    "tagId": 2,
                                                    "tagName": "hash-table",
                                                    "displayName": "Hash Table"
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
                    description = "잘못된 요청 값",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "잘못된 요청 값 예시",
                                    value = """
                                            {
                                              "isSuccess": false,
                                              "code": "C001",
                                              "message": "요청 값이 올바르지 않습니다."
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 문제",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "존재하지 않는 문제 예시",
                                    value = """
                                            {
                                              "isSuccess": false,
                                              "code": "P001",
                                              "message": "존재하지 않는 문제입니다."
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{problemId}")
    public CommonResponse<ProblemResponse> getProblemById(
            @Parameter(
                    description = "LeetCode 문제 번호",
                    example = "1",
                    required = true
            )
            @PathVariable
            @NotNull(message = "문제 번호는 필수입니다.")
            @Min(value = 1, message = "문제 번호는 1 이상이어야 합니다.")
            Long problemId
    ) {
        ProblemResponse response = problemService.getProblemById(problemId);
        return CommonResponse.success(CommonSuccessCode.OK, response);
    }

    @Operation(
            summary = "문제 목록 조회",
            description = """
                    문제 목록을 조회합니다.

                    태그, 난이도 조건을 함께 사용할 수 있습니다.

                    예시:
                    - 전체 문제 조회: /api/v1/problems
                    - 태그별 조회: /api/v1/problems?tag=array
                    - 난이도별 조회: /api/v1/problems?difficulty=MEDIUM
                    - 태그 + 난이도 조회: /api/v1/problems?tag=graph&difficulty=HARD
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "문제 목록 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProblemResponse.class)),
                            examples = @ExampleObject(
                                    name = "문제 목록 조회 성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "S000",
                                              "message": "요청에 성공했습니다.",
                                              "result": [
                                                {
                                                  "problemId": 1,
                                                  "title": "Two Sum",
                                                  "problemUrl": "https://leetcode.com/problems/two-sum/",
                                                  "difficulty": "EASY",
                                                  "acceptedCount": 15432000,
                                                  "submissionCount": 28900000,
                                                  "acceptanceRate": 53.4,
                                                  "isPremium": false,
                                                  "tags": [
                                                    {
                                                      "tagId": 1,
                                                      "tagName": "array",
                                                      "displayName": "Array"
                                                    },
                                                    {
                                                      "tagId": 2,
                                                      "tagName": "hash-table",
                                                      "displayName": "Hash Table"
                                                    }
                                                  ]
                                                },
                                                {
                                                  "problemId": 53,
                                                  "title": "Maximum Subarray",
                                                  "problemUrl": "https://leetcode.com/problems/maximum-subarray/",
                                                  "difficulty": "MEDIUM",
                                                  "acceptedCount": 4200000,
                                                  "submissionCount": 8300000,
                                                  "acceptanceRate": 50.6,
                                                  "isPremium": false,
                                                  "tags": [
                                                    {
                                                      "tagId": 1,
                                                      "tagName": "array",
                                                      "displayName": "Array"
                                                    },
                                                    {
                                                      "tagId": 3,
                                                      "tagName": "dynamic-programming",
                                                      "displayName": "Dynamic Programming"
                                                    }
                                                  ]
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
                                    name = "잘못된 요청 값 예시",
                                    value = """
                                            {
                                              "isSuccess": false,
                                              "code": "C001",
                                              "message": "요청 값이 올바르지 않습니다."
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public CommonResponse<List<ProblemResponse>> getProblems(
            @Parameter(
                    description = "알고리즘 태그명",
                    example = "dynamic-programming"
            )
            @RequestParam(required = false)
            @Pattern(
                    regexp = "^[a-z0-9-]+$",
                    message = "태그명은 영문 소문자, 숫자, 하이픈만 사용할 수 있습니다."
            )
            String tag,

            @Parameter(
                    description = "문제 난이도",
                    example = "MEDIUM",
                    schema = @Schema(allowableValues = {"EASY", "MEDIUM", "HARD"})
            )
            @RequestParam(required = false)
            @Pattern(
                    regexp = "EASY|MEDIUM|HARD",
                    message = "난이도는 EASY, MEDIUM, HARD 중 하나여야 합니다."
            )
            String difficulty,

            @Parameter(
                    description = "페이지 번호",
                    example = "0"
            )
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            int page,

            @Parameter(
                    description = "페이지 크기",
                    example = "20"
            )
            @RequestParam(defaultValue = "20")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            int size
    ) {
        List<ProblemResponse> response = problemService.getProblems(tag, difficulty, page, size);
        return CommonResponse.success(CommonSuccessCode.OK, response);
    }
}

