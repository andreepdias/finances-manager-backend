package com.github.andreepdias.mymoney.repository;

import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Page<Wallet> findAllByUserId(Integer userId, Pageable pageable);

    List<Wallet> findAllByUserId(Integer userId);
}
