package com.github.andreepdias.mymoney.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.andreepdias.mymoney.model.enumerator.CategoryType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Double amount;

    @Column
    private String icon;

    @OneToMany(mappedBy = "wallet")
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public void addAmount(Double amount, CategoryType categoryType) {
        if(categoryType == CategoryType.INCOME){
            this.amount += amount;
        }else{
            this.amount -= amount;
        }
    }

    public void subtractAmount(Double amount, CategoryType categoryType) {
        if(categoryType == CategoryType.INCOME){
            this.amount -= amount;
        }else{
            this.amount += amount;
        }
    }
}
