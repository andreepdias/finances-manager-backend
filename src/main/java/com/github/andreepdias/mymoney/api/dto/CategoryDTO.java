package com.github.andreepdias.mymoney.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    private Integer id;

    @NotEmpty(message = "{field.dto.category.name.required}")
    private String name;

    private String description;

    @NotNull(message = "{field.dto.category.categoryType.required}")
    private Integer categoryTypeCode;

    private String categoryTypeName;

}

