package com.github.andreepdias.mymoney.service;

import com.github.andreepdias.mymoney.exception.DataConsistencyException;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.model.entity.User;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import com.github.andreepdias.mymoney.repository.UserRepository;
import com.github.andreepdias.mymoney.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;

    private final UserService userService;
    private final TransactionService transactionService;

    public List<Wallet> findAll() {
        Integer userId = userService.findAuthenticatedUserId();
        return repository.findAllByUserId(userId);
    }

    public Page<Wallet> findPage(PageRequest pageRequest) {
        Integer userId = userService.findAuthenticatedUserId();
        return repository.findAllByUserId(userId, pageRequest);
    }

    public Wallet findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Wallet with id " + id + " was not found"));
    }

    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

    public Wallet insert(Wallet category){
        User user = userService.findAuthenticatedUser();
        category.setUser(user);
        category.setId(null);
        return repository.save(category);
    }

    public Wallet update(Wallet newWallet) {
        Wallet existentWallet = findById(newWallet.getId());
        updateWalletData(existentWallet, newWallet);
        return repository.save(existentWallet);
    }

    public void delete(Integer id) {
        if(!existsById(id)){
            throw new ObjectNotFoundException("Wallet with id " + id + " was not found.");
        }
        boolean existsTransactionsAssociated = transactionService.existsByWalletId(id);
        if(existsTransactionsAssociated){
            throw new DataConsistencyException("You can't delete a wallet with transactions associated.");
        }
        repository.deleteById(id);
    }

    private void updateWalletData(Wallet existentWallet, Wallet newWallet) {
        existentWallet.setName(newWallet.getName());
        existentWallet.setAmount(newWallet.getAmount());
        existentWallet.setIcon(newWallet.getIcon());
    }
}
