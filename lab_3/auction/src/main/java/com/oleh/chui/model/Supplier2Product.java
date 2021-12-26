package com.oleh.chui.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Supplier2Product extends UniversalProduct {

    @Id
    @Getter
    @Setter
    private Long productId;

    @Getter
    @Setter
    private String title;

    @Setter
    @Getter
    private String description;

    @Getter
    @Setter
    private String category;

    @Setter
    @Getter
    private int price;

    @Getter
    @Setter
    private String saleType;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @Getter
    @Setter
    private String deliveryType;

    @Getter
    @Setter
    private int deliveryPrice;

    @Getter
    @Setter
    private String seller;

    @Getter
    @Setter
    private int deliveryTimeDays;
}
