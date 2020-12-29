package com.github.andreepdias.mymoney.service;

import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.model.entity.Transaction;
import com.github.andreepdias.mymoney.model.entity.User;
import com.github.andreepdias.mymoney.model.entity.Wallet;
import com.github.andreepdias.mymoney.model.enumerator.CategoryType;
import com.github.andreepdias.mymoney.repository.CategoryRepository;
import com.github.andreepdias.mymoney.repository.TransactionRepository;
import com.github.andreepdias.mymoney.repository.UserRepository;
import com.github.andreepdias.mymoney.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    Category c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19;
    User u1;
    Wallet w1;
    Transaction t1;

    public void instantiateDatabase() throws ParseException {
        insertCategories();
        insertUsers();
        insertWallets();
        insertTransaction();
    }

    private void insertWallets(){
        w1 = new Wallet(null, "Cash", 1000.0, "local_atm", null, u1);
        walletRepository.save(w1);
    }

    public void insertTransaction(){
        Transaction  t1 = new Transaction(null, "Monitor", "", 800.0, LocalDate.now(), c8, w1, u1);
        transactionRepository.save(t1);

        w1.setTransactions(Arrays.asList(t1));
    }

    private void insertUsers(){
        u1 = new User(null, "André", "andreeduardo1997@gmail.com", "andre", LocalDate.now(), null, null);
        userRepository.save(u1);
    }

    private void insertCategories(){
        c1 = new Category(null, "Food", "Feeding related expense. Buying food, drinking, etc.", CategoryType.EXPENSE);
        c2 = new Category(null, "Bills", "Bills payment. Like water bills, electricity, etc. ", CategoryType.EXPENSE);
        c3 = new Category(null, "Transport", "Money spent with transportation. Example fuel, uber, bus, etc." ,CategoryType.EXPENSE);
        c4 = new Category(null, "Shopping", "Clothes, shoes, tech or any shopping related." ,CategoryType.EXPENSE);
        c5 = new Category(null, "Entertainment", "Going out, buying games, watching movies, etc.", CategoryType.EXPENSE);
        c6 = new Category(null, "Health", "Medicine, surgery or anything health related." ,CategoryType.EXPENSE);
        c7 = new Category(null, "Gift", "Spending resources for gifting someone." ,CategoryType.EXPENSE);
        c8 = new Category(null, "Home", "House related expenses. Furniture, home appliances, etc." ,CategoryType.EXPENSE);
        c9 = new Category(null, "Education", "University, courses, books and so on.", CategoryType.EXPENSE);
        c10 = new Category(null, "Business", "Expenses with creating or maintaining a business.", CategoryType.EXPENSE);
        c11 = new Category(null, "Fee", "Any fee ou tax related expense.", CategoryType.EXPENSE);
        c12 = new Category(null, "Withdraw", "Withdrawing money.", CategoryType.EXPENSE);
        c13 = new Category(null, "Other", "Something not listed.", CategoryType.EXPENSE);

        c14 = new Category(null, "Salary", "Receiving salary." ,CategoryType.INCOME);
        c15 = new Category(null, "Gift", "Money gifted by somebody." , CategoryType.INCOME);
        c16 = new Category(null, "Selling", "Selling stuff and getting richer.", CategoryType.INCOME);
        c17 = new Category(null, "Award", "Any kind of money award." ,CategoryType.INCOME);
        c18 = new Category(null, "Interest", "Interest money from some type of investment." , CategoryType.INCOME);
        c19 = new Category(null, "Other", "Other incoming not listed." , CategoryType.INCOME);

        categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13));
        categoryRepository.saveAll(Arrays.asList(c14, c15, c16, c17, c18, c19));
    }
}
