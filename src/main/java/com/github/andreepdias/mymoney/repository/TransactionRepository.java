package com.github.andreepdias.mymoney.repository;

import com.github.andreepdias.mymoney.model.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserIdOrderByDateDesc(Integer userId);

    boolean existsByCategoryId(Integer categoryId);

    boolean existsByWalletId(Integer walletId);

    Page<Transaction> findAllByUserIdOrderByDateDescIdDesc(Integer userId, Pageable pageable);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND month(t.date) = :month AND year(t.date) = :year")
    List<Transaction> searchByMonthAndYear(Integer userId, Integer month, Integer year);

    List<Transaction> findAllByUserIdAndDateBetweenOrderByDateDescIdDesc(Integer userId, LocalDate time, LocalDate time1);
}
