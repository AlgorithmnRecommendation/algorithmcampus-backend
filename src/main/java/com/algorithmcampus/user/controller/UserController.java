package com.algorithmcampus.user.controller;

import com.algorithmcampus.global.common.response.CommonResponse;
import com.algorithmcampus.user.dto.UserInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 인증 API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Operation(
            summary = "GitHub OAuth2.0 인증 시작",
            description = "GitHub OAuth2.0 로그인을 시작합니다. " +
                    "state 값을 생성하여 세션에 저장한 후, GitHub 인증 페이지로 리다이렉트(302)합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "GitHub 인증 페이지로 리다이렉트"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류"
            )
    })
    @GetMapping("/oauth2/github/authorize")
    public void authorizeGitHub(HttpServletResponse response) {
        // TODO: GitHub OAuth 인증 시작 로직 구현 예정
        // 1. state 값 생성 (CSRF 방지용 랜덤 문자열)
        // 2. state 값을 세션 또는 Redis에 저장
        // 3. GitHub OAuth URL 생성
        //    https://github.com/login/oauth/authorize?client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&state={STATE}&scope=read:user user:email
        // 4. response.sendRedirect()로 GitHub 인증 페이지로 리다이렉트
    }

    @Operation(
            summary = "GitHub OAuth2.0 콜백 처리",
            description = "GitHub에서 리다이렉트된 인증 코드를 처리합니다. " +
                    "state를 검증하고, code로 GitHub 액세스 토큰을 받아 사용자 정보를 조회합니다. " +
                    "최초 로그인 시 자동으로 회원가입을 처리하며, JWT 토큰을 쿠키에 저장한 후 프론트엔드 메인 페이지로 리다이렉트(302)합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "로그인 성공 - 프론트엔드 메인 페이지로 리다이렉트 (JWT는 HttpOnly 쿠키에 저장)"
            ),
            @ApiResponse(
                    responseCode = "302",
                    description = "로그인 실패 - 프론트엔드 에러 페이지로 리다이렉트"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 - 인증 코드가 유효하지 않거나 state 값이 일치하지 않습니다."
            )
    })
    @GetMapping("/oauth2/github/callback")
    public void githubCallback(
            @Parameter(description = "GitHub OAuth 인증 코드", required = true, example = "abc123def456")
            @RequestParam String code,
            @Parameter(description = "OAuth state 파라미터 (CSRF 방지)", required = true)
            @RequestParam String state,
            HttpServletResponse response
    ) {
        // TODO: GitHub OAuth 로그인/회원가입 로직 구현 예정
        // 1. state 검증 (세션 또는 Redis에 저장된 값과 비교)
        // 2. code로 GitHub에 액세스 토큰 요청
        //    POST https://github.com/login/oauth/access_token
        // 3. 액세스 토큰으로 GitHub 사용자 정보 조회
        //    GET https://api.github.com/user
        // 4. DB에서 사용자 조회 (GitHub ID 기준)
        //    - 없으면: 자동 회원가입 (INSERT)
        //    - 있으면: 로그인 처리
        // 5. JWT 액세스 토큰 및 리프레시 토큰 생성
        // 6. JWT를 HttpOnly 쿠키에 저장
        //    response.addCookie(new Cookie("accessToken", jwt))
        // 7. 프론트엔드 메인 페이지로 리다이렉트
        //    response.sendRedirect("http://localhost:3000/")
    }

    @Operation(
            summary = "현재 로그인된 사용자 정보 조회",
            description = "JWT 토큰을 검증하여 현재 로그인된 사용자의 정보를 반환합니다. " +
                    "쿠키에 저장된 JWT 토큰을 자동으로 읽어 사용자 정보를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패 - JWT 토큰이 유효하지 않거나 만료됨"
            )
    })
    @GetMapping("/me")
    public ResponseEntity<CommonResponse<UserInfoResponseDto>> getCurrentUser() {
        // TODO: 현재 로그인된 사용자 정보 조회 로직 구현 예정
        // 1. 쿠키에서 JWT 토큰 추출
        // 2. JWT 토큰 검증 및 파싱 (userId 추출)
        // 3. DB에서 사용자 정보 조회
        // 4. UserInfoResponseDto로 변환하여 반환
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "로그아웃",
            description = "사용자를 로그아웃합니다. " +
                    "JWT 쿠키를 삭제하고, 필요시 리프레시 토큰을 블랙리스트에 추가합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "로그아웃 성공"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자"
            )
    })
    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout(HttpServletResponse response) {
        // TODO: 로그아웃 로직 구현 예정
        // 1. 쿠키에서 JWT 토큰 추출
        // 2. 리프레시 토큰을 Redis 블랙리스트에 추가 (선택)
        // 3. accessToken 쿠키 삭제 (MaxAge=0 설정)
        // 4. refreshToken 쿠키 삭제 (MaxAge=0 설정)
        return ResponseEntity.ok().build();
    }
}
