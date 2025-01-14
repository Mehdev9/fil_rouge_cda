package com.insy.fil_rouge_cda.services;

import com.insy.fil_rouge_cda.Dtos.DashboardDTO;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    public DashboardDTO getDashboardData() {

        String userName = "John Doe";
        int totalItemsSold = 200;
        int totalSales = 5000;
        int totalStock = 150;

        return new DashboardDTO(userName, totalItemsSold, totalSales, totalStock);
    }
}
