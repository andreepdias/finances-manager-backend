package com.github.andreepdias.mymoney.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class

WalletDTO {

    private Integer id;

    @NotEmpty(message = "{field.dto.wallet.name.required}")
    private String name;

    @NotEmpty(message = "{field.dto.wallet.amount.required}")
    private String amount;

    @NotEmpty(message = "{field.dto.wallet.icon.required}")
    private String icon;
}
