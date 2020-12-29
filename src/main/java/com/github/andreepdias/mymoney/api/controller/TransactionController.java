package com.github.andreepdias.mymoney.api.controller;

import com.github.andreepdias.mymoney.api.dto.TransactionDTO;
import com.github.andreepdias.mymoney.api.mapper.TransactionMapper;
import com.github.andreepdias.mymoney.exception.DataConsistencyException;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.exception.ReferenceNotFoundException;
import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    private final TransactionMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TransactionDTO> findPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return service.findPage(pageRequest).map(mapper::toDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO find(@PathVariable Integer id){
        Transaction transaction;
        try{
            transaction = service.findById(id);
        }catch(ObjectNotFoundException ex){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        TransactionDTO dto = mapper.toDTO(transaction);
        return dto;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> searchByMonthAndYear(
            @RequestParam Integer month,
            @RequestParam Integer year
    ){
        return service.searchByMonthAndYear(month, year)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO insert(@RequestBody @Valid TransactionDTO dto){
        Transaction transaction;
        try{
            transaction = mapper.toEntity(dto);
            transaction = service.insert(transaction);
        }catch(ReferenceNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return mapper.toDTO(transaction);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @RequestBody @Valid TransactionDTO dto){
        Transaction transaction;
        try{
            transaction = mapper.toEntity(dto);
            transaction.setId(id);
            transaction = service.update(transaction);
        }catch(ObjectNotFoundException | ReferenceNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        try{
            service.delete(id);
        }catch(ObjectNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
