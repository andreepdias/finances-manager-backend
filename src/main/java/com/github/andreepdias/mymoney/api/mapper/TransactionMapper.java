package com.github.andreepdias.mymoney.api.mapper;

import com.github.andreepdias.mymoney.api.dto.TransactionDTO;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.exception.ReferenceNotFoundException;
import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import com.github.andreepdias.mymoney.service.CategoryService;
import com.github.andreepdias.mymoney.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final ModelMapper mapper;
    private final CategoryMapper categoryMapper;
    private final WalletMapper walletMapper;

    private final CategoryService categoryService;
    private final WalletService walletService;

    public Transaction  toEntity(TransactionDTO dto){
        Transaction transaction = mapper.map(dto, Transaction.class);
        transaction.setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("d/M/yyyy")));

        try{
            Category category = categoryService.findById(dto.getCategoryId());
            Wallet wallet = walletService.findById(dto.getWalletId());

            transaction.setCategory(category);
            transaction.setWallet(wallet);
        }catch (ObjectNotFoundException ex){
            throw new ReferenceNotFoundException("This transaction is inconsistent. " + ex.getMessage());
        }

        return transaction;
    }

    public TransactionDTO toDTO(Transaction entity){
        TransactionDTO dto = mapper.map(entity, TransactionDTO.class);
        dto.setDate(entity.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        dto.setCategoryId(entity.getCategory().getId());
        dto.setWalletId(entity.getWallet().getId());

        dto.setCategory(categoryMapper.toDTO(entity.getCategory()));
        dto.setWallet(walletMapper.toDTO(entity.getWallet()));

        return dto;
    }
}
