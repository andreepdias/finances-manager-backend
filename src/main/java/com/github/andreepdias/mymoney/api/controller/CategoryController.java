package com.github.andreepdias.mymoney.api.controller;

import com.github.andreepdias.mymoney.api.dto.CategoryDTO;
import com.github.andreepdias.mymoney.api.mapper.CategoryMapper;
import com.github.andreepdias.mymoney.exception.DataConsistencyException;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.model.enumerator.CategoryType;
import com.github.andreepdias.mymoney.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    
    private final CategoryMapper mapper;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> findAll(){
        return service.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryDTO> findPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return service.findPage(pageRequest).map(mapper::toDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO find(@PathVariable Integer id){
        Category category;
        try{
            category = service.findById(id);
        }catch(ObjectNotFoundException ex){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        CategoryDTO dto = mapper.toDTO(category);
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO insert(@RequestBody @Valid CategoryDTO dto){
        Category category = mapper.toEntity(dto);
        category = service.insert(category);
        return mapper.toDTO(category);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @RequestBody @Valid CategoryDTO dto){
        Category category = mapper.toEntity(dto);
        category.setId(id);
        try{
            category = service.update(category);
        }catch(ObjectNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        try{
            service.delete(id);
        }catch(ObjectNotFoundException | DataConsistencyException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
