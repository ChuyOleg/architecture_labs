package com.oleh.chui.model.entity;

import com.oleh.chui.model.util.SaleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long productId;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private String category;

    @NotNull
    private int price;

    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String deliveryType;

    private int deliveryPrice;

    private int deliveryTimeDays;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", nullable = false)
    private Person seller;

    @OneToMany(mappedBy = "product")
    private Set<Order> orders;

}
