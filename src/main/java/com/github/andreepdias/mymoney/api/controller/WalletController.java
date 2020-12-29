package com.github.andreepdias.mymoney.api.controller;

import com.github.andreepdias.mymoney.api.dto.WalletDTO;
import com.github.andreepdias.mymoney.api.mapper.WalletMapper;
import com.github.andreepdias.mymoney.exception.DataConsistencyException;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService service;

    private final WalletMapper mapper;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<WalletDTO> findAll(){
        return service.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<WalletDTO> findPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return service.findPage(pageRequest).map(mapper::toDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public WalletDTO find(@PathVariable Integer id){
        Wallet wallet;
        try{
            wallet = service.findById(id);
        }catch(ObjectNotFoundException ex){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        WalletDTO dto = mapper.toDTO(wallet);
        return dto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletDTO insert(@RequestBody @Valid WalletDTO dto){
        Wallet wallet = mapper.toEntity(dto);
        wallet = service.insert(wallet);
        return mapper.toDTO(wallet);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @RequestBody @Valid WalletDTO dto){
        Wallet wallet = mapper.toEntity(dto);
        wallet.setId(id);
        try{
            wallet = service.update(wallet);
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
