package com.github.andreepdias.mymoney.api.dto;

import com.github.andreepdias.mymoney.model.entity.Wallet;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TransactionDTO {

    private Integer id;

    @NotEmpty(message = "{field.dto.transaction.name.required}")
    private String name;
    
    private String description;

    @NotEmpty(message = "{field.dto.transaction.amount.required}")
    private String amount;

    @NotEmpty(message = "{field.dto.transaction.date.required}")
    private String date;

    @NotNull(message = "{field.dto.transaction.categoryId.required}")
    private Integer categoryId;

    @NotNull(message = "{field.dto.transaction.walletId.required}")
    private Integer walletId;

    private Integer userId;

    private CategoryDTO category;

    private WalletDTO wallet;

}
