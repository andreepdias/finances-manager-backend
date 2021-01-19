package com.github.andreepdias.mymoney.model.entity;

import com.github.andreepdias.mymoney.model.enumerator.CategoryType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private CategoryType categoryType;

    @ManyToOne
    @JoinColumn
    private User user;

}
