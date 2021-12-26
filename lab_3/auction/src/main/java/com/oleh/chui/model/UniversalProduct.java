package com.oleh.chui.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UniversalProduct {

    private Long productId;

    private String title;

    private String description;

    private String category;

    private int price;

    private String saleType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String deliveryType;

    private int deliveryPrice;

    private String seller;

    private int deliveryTimeDays;

}
