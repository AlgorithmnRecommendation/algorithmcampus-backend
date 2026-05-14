package com.algorithmcampus.recommendation.service;

import com.algorithmcampus.recommendation.dto.response.RecommendationResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 추천 서비스
 *
 * @author : 권지영
 * @filename : RecommendationService
 * @since : 2026. 5. 1. 금요일
 */
@Service
public class RecommendationService {

    public List<RecommendationResponse> getRecommendations(Long userId) {
        return Collections.emptyList();
    }
}

