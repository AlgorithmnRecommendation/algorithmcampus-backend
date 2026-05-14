package com.algorithmcampus.recommendation.domain;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 추천 유형
 *
 * @author : 권지영
 * @filename : RecommendationType
 * @since : 2026. 4. 23. 목요일
 */
@Schema(description = "추천 유형")
public enum RecommendationType {
    SIMILAR,
    WEAKNESS,
    LEVELUP,
    REVIEW
}

