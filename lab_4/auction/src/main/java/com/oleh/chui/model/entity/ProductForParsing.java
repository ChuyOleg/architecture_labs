package com.oleh.chui.model.entity;

import com.oleh.chui.model.util.SaleType;
import lombok.*;

import java.time.LocalDate;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductForParsing {

    @Setter
    @Getter
    private Long productId;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String category;

    @Setter
    @Getter
    private int price;

    @Setter
    @Getter
    private SaleType saleType;

    @Setter
    @Getter
    private LocalDate startDate;

    @Setter
    @Getter
    private LocalDate endDate;

    @Setter
    @Getter
    private String deliveryType;

    @Setter
    @Getter
    private int deliveryPrice;

    @Setter
    @Getter
    private int deliveryTimeDays;

    @Setter
    @Getter
    private int seller_id;

}
