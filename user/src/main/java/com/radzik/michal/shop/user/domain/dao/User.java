package com.radzik.michal.shop.user.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
public non-sealed class User extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @NotAudited
    private String password;

    @ManyToOne
    private Address address;

    @ManyToMany
    @NotAudited
    private Set<Role> roles;

    private String token;



}
