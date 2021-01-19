package com.github.andreepdias.mymoney.api.controller;

import com.github.andreepdias.mymoney.api.dto.UserDTO;
import com.github.andreepdias.mymoney.exception.DuplicatedException;
import com.github.andreepdias.mymoney.model.entity.User;
import com.github.andreepdias.mymoney.service.DBService;
import com.github.andreepdias.mymoney.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final DBService dbService;

    private final ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO userDTO){
        User user = mapper.map(userDTO, User.class);

        User newUser;
        try{
            newUser = service.insert(user);
        }catch(DuplicatedException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return mapper.map(newUser, UserDTO.class);
    }

    @PostMapping("/populate")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createAndPopulate(@RequestBody @Valid UserDTO userDTO){
        User user = mapper.map(userDTO, User.class);

        User newUser;
        try{
            newUser = service.insert(user);
            dbService.populateUser(newUser);
        }catch(DuplicatedException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
        return mapper.map(newUser, UserDTO.class);
    }
}
