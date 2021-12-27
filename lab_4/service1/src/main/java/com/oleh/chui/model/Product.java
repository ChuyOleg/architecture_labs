package com.oleh.chui.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @Setter
    @Getter
    private Long productId;

    @NotNull
    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String description;

    @NotNull
    @Setter
    @Getter
    private String category;

    @NotNull
    @Setter
    @Getter
    private int price;

    @Enumerated(EnumType.STRING)
    @NotNull
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
    private int sellerId;

}
