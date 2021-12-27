package com.oleh.chui.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Setter
    @Getter
    private Long orderId;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id", nullable = false)
    @Setter
    @Getter
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter
    @Getter
    private Person customer;

    @Setter
    @Getter
    private LocalDate dateTime;

}
