package com.github.andreepdias.mymoney.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Wallet> wallets = new ArrayList<>();

}
