package com.github.andreepdias.mymoney.service;

import com.github.andreepdias.mymoney.api.dto.TransactionDTO;
import com.github.andreepdias.mymoney.exception.DataConsistencyException;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.exception.ReferenceNotFoundException;
import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.model.entity.User;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import com.github.andreepdias.mymoney.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    private final UserService userService;

    public Page<Transaction> findPage(PageRequest pageRequest) {
        Integer userId = userService.findAuthenticatedUserId();
        return repository.findAllByUserIdOrderByDateDesc(userId, pageRequest);
    }

    public Transaction findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Transaction with id " + id + " was not found"));
    }

    public List<Transaction> searchByMonthAndYear(Integer month, Integer year) {
        Integer userId = userService.findAuthenticatedUserId();

        LocalDate startMonth = LocalDate.of(year, month, 1);
        LocalDate endMonth = startMonth.with(TemporalAdjusters.lastDayOfMonth());

        return repository.findAllByUserIdAndDateBetweenOrderByDateDesc(userId, startMonth, endMonth);
    }

    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

    public Transaction insert(Transaction transaction){
        User user = userService.findAuthenticatedUser();
        transaction.setUser(user);
        transaction.setId(null);
        return repository.save(transaction);
    }

    public Transaction update(Transaction newTransaction) {
        Transaction existentTransaction = findById(newTransaction.getId());
        updateTransactionData(existentTransaction, newTransaction);
        return repository.save(existentTransaction);
    }

    public void delete(Integer id) {
        if(!existsById(id)){
            throw new ObjectNotFoundException("Transaction with id " + id + " was not found.");
        }
        repository.deleteById(id);
    }

    private void updateTransactionData(Transaction existentTransaction, Transaction newTransaction) {
        existentTransaction.setName(newTransaction.getName());
        existentTransaction.setDescription(newTransaction.getDescription());
        existentTransaction.setAmount(newTransaction.getAmount());
        existentTransaction.setCategory(newTransaction.getCategory());
        existentTransaction.setWallet(newTransaction.getWallet());
    }

    public boolean existsByCategoryId(Integer categoryId){
        return repository.existsByCategoryId(categoryId);
    }

    public boolean existsByWalletId(Integer walletId) {
        return repository.existsByWalletId(walletId);
    }
}
