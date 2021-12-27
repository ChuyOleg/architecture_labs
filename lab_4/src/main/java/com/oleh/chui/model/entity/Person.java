package com.oleh.chui.model.entity;

import com.oleh.chui.model.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    private Long personId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
