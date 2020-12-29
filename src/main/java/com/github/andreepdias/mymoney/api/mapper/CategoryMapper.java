package com.github.andreepdias.mymoney.api.mapper;

import com.github.andreepdias.mymoney.api.dto.CategoryDTO;
import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.model.enumerator.CategoryType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final ModelMapper mapper = new ModelMapper();

    public Category toEntity(CategoryDTO dto){
        Category category = mapper.map(dto, Category.class);
        category.setCategoryType(CategoryType.toEnum(dto.getCategoryTypeCode()));
        return category;
    }

    public CategoryDTO toDTO(Category entity){
        CategoryDTO dto = mapper.map(entity, CategoryDTO.class);
        dto.setCategoryTypeCode(entity.getCategoryType().getCode());
        dto.setCategoryTypeName(entity.getCategoryType().getDescription());
        return dto;
    }
}
