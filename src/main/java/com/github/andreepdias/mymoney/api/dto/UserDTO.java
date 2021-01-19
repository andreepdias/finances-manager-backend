package com.github.andreepdias.mymoney.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    @NotEmpty(message = "{field.dto.name.required}")
    private String name;

    @NotEmpty(message = "{field.dto.email.required}")
    private String email;

    @NotEmpty(message = "{field.dto.password.required}")
    private String password;
}
