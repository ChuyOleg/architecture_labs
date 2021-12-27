package com.oleh.chui.model.entity;

import com.oleh.chui.model.util.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"products", "orders"})
@EqualsAndHashCode(exclude = {"products", "orders"})
public class Person {

    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @Setter
    @Getter
    private Long personId;

    @NotNull
    @Setter
    @Getter
    private String firstName;

    @NotNull
    @Setter
    @Getter
    private String lastName;

    @NotNull
    @Setter
    @Getter
    private String email;

    @NotNull
    @Setter
    @Getter
    private String password;

    @NotNull
    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller")
    @Setter
    @Getter
    private Set<Product> products;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    @Setter
    @Getter
    private Set<Order> orders;
}
