package com.insy.fil_rouge_cda.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardDTO {

    private String userName;
    private int totalItemsSold;
    private int totalSales;
    private int totalStock;

    public DashboardDTO(String userName, int totalItemsSold, int totalSales, int totalStock) {
        this.userName = userName;
        this.totalItemsSold = totalItemsSold;
        this.totalSales = totalSales;
        this.totalStock = totalStock;
    }
}
