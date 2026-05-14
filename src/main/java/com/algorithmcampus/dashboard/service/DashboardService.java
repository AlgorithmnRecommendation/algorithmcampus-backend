package com.algorithmcampus.dashboard.service;

import com.algorithmcampus.dashboard.dto.response.DashboardResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Please explain the class!!!
 *
 * @author : 권지영
 * @filename : DashboardService
 * @since : 2026. 5. 14. 목요일
 */
@Service

public class DashboardService {

    public DashboardResponse getDashboard() {
        return DashboardResponse.builder()
                .totalSolvedCount(0)
                .summaryMessage("")
                .recommendationMessage("")
                .recommendedProblems(Collections.emptyList())
                .typeAnalysis(Collections.emptyList())
                .recentSolvedProblems(Collections.emptyList())
                .build();
    }
}
