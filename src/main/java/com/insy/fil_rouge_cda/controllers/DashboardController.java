package com.insy.fil_rouge_cda.controllers;

import com.insy.fil_rouge_cda.Dtos.DashboardDTO;
import com.insy.fil_rouge_cda.services.DashboardService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/dashboard")
    public DashboardDTO showDashboard() {

        return dashboardService.getDashboardData();
    }
}
