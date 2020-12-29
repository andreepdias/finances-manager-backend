package com.github.andreepdias.mymoney.api.mapper;

import com.github.andreepdias.mymoney.api.dto.WalletDTO;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletMapper {

    private final ModelMapper mapper = new ModelMapper();

    public Wallet toEntity(WalletDTO dto){
        Wallet wallet = mapper.map(dto, Wallet.class);
        return wallet;
    }

    public WalletDTO toDTO(Wallet entity){
        WalletDTO dto = mapper.map(entity, WalletDTO.class);
        return dto;
    }
}
