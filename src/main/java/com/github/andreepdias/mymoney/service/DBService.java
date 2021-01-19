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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;


    public void instantiateDatabase() throws ParseException {
        User u1 = new User(null, "André", "andre.dias@msn.com", "andre", LocalDate.now(), new ArrayList<>(), new ArrayList<>());
        userRepository.save(u1);

        populateUser(u1);
    }

    public void populateUser(User u1){
        Category c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21;
        c1 = new Category(null, "Food", "Feeding related expense. Buying food, drinking, etc.", CategoryType.EXPENSE, u1);
        c2 = new Category(null, "Bills", "Bills payment. Like water bills, electricity, etc. ", CategoryType.EXPENSE, u1);
        c3 = new Category(null, "Transport", "Money spent with transportation. Example fuel, uber, bus, etc." ,CategoryType.EXPENSE, u1);
        c4 = new Category(null, "Shopping", "Clothes, shoes, any shopping related." ,CategoryType.EXPENSE, u1);
        c5 = new Category(null, "Entertainment", "Going out, buying games, watching movies, etc.", CategoryType.EXPENSE, u1);
        c6 = new Category(null, "Health", "Medicine, surgery or anything health related." ,CategoryType.EXPENSE, u1);
        c7 = new Category(null, "Gift", "Spending resources for gifting someone." ,CategoryType.EXPENSE, u1);
        c8 = new Category(null, "Home", "House related expenses. Furniture, home appliances, etc." ,CategoryType.EXPENSE, u1);
        c9 = new Category(null, "Education", "University, courses, books and so on.", CategoryType.EXPENSE, u1);
        c10 = new Category(null, "Business", "Expenses with creating or maintaining a business.", CategoryType.EXPENSE, u1);
        c11 = new Category(null, "Fee", "Any fee ou tax related expense.", CategoryType.EXPENSE, u1);
        c12 = new Category(null, "Withdraw", "Withdrawing money.", CategoryType.EXPENSE, u1);
        c13 = new Category(null, "Other", "Something not listed.", CategoryType.EXPENSE, u1);
        c14 = new Category(null, "Salary", "Receiving salary." ,CategoryType.INCOME, u1);
        c15 = new Category(null, "Gift", "Money gifted by somebody." , CategoryType.INCOME, u1);
        c16 = new Category(null, "Selling", "Selling stuff and getting richer.", CategoryType.INCOME, u1);
        c17 = new Category(null, "Award", "Any kind of money award." ,CategoryType.INCOME, u1);
        c18 = new Category(null, "Interest", "Interest money from some type of investment." , CategoryType.INCOME, u1);
        c19 = new Category(null, "Other", "Other incoming not listed." , CategoryType.INCOME, u1);
        c20 = new Category(null, "Tech", "Shopping tech related expenses. Cellphone, notebook, etc." , CategoryType.EXPENSE, u1);
        c21 = new Category(null, "Freelance", "Money got from freelance job." , CategoryType.INCOME, u1);
        List<Category> categoryList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21);
        categoryRepository.saveAll(categoryList);

        Wallet w1 = new Wallet(null, "Cash", 1000.0, "local_atm", new ArrayList<>(), u1);
        Wallet w2 = new Wallet(null, "BB", 2000.0, "account_balance", new ArrayList<>(), u1);
        Wallet w3 = new Wallet(null, "Nubank", 3000.0, "credit_card", new ArrayList<>(), u1);
        List<Wallet> walletList = Arrays.asList(w1, w2, w3);
        walletRepository.saveAll(walletList);

        Transaction  t1 = new Transaction(null, "Monitor", "Monitor Dell 24\"", 800.0, LocalDate.now(), c20, w2, u1);
        Transaction  t2 = new Transaction(null, "Mesa", "Mesa para o quarto", 350.0, LocalDate.now(), c8, w2, u1);
        Transaction  t3 = new Transaction(null, "Teclado", "Teclado mecânico", 170.0, LocalDate.now(), c20, w2, u1);
        Transaction  t4 = new Transaction(null, "Mouse", "Mouse Corsair", 60.0, LocalDate.now(), c20, w2, u1);
        List<Transaction> transactionsBB = Arrays.asList(t1, t2, t3, t4);

        Transaction  t5 = new Transaction(null, "Tênis", "Tênis branco", 80.0, LocalDate.now(), c4, w3, u1);
        Transaction  t6 = new Transaction(null, "Camisa social", "Camisa social preta", 100.0, LocalDate.now(), c4, w3, u1);
        Transaction  t7 = new Transaction(null, "Agasalho", "Moletom cinza", 80.0, LocalDate.now(), c4, w3, u1);
        Transaction  t8 = new Transaction(null, "Meias", "Meias pretas cano baixo", 30.0, LocalDate.now(), c4, w3, u1);
        List<Transaction> transactionsNUBANK = Arrays.asList(t5, t6, t7, t8);

        Transaction  t9 = new Transaction(null, "Almoço", "Almoço no shopping", 20.0, LocalDate.now(), c1, w1, u1);
        Transaction  t10 = new Transaction(null, "Academia", "Mensalidade da academia", 75.0, LocalDate.now(), c2, w1, u1);
        Transaction  t11 = new Transaction(null, "Café da tarde", "Alimentos para o café da Padaria ", 22.0, LocalDate.now(), c1, w1, u1);
        Transaction  t12 = new Transaction(null, "Inscrição evento", "Taxa de inscrição do evento XXX", 80.0, LocalDate.now(), c9, w1, u1);
        List<Transaction> transactionsCASH = Arrays.asList(t9, t10, t11, t12);

        Transaction  t13 = new Transaction(null, "Website ABC", "Website para a startup XXX", 700., LocalDate.now(), c21, w1, u1);
        Transaction  t14 = new Transaction(null, "Rendimento DEF", "Rendimento mensal do banco YYY", 22., LocalDate.now(), c18, w1, u1);
        Transaction  t15 = new Transaction(null, "Salário imaginário", "Salário parcial imaginário", 1000., LocalDate.now(), c14, w1, u1);
        List<Transaction> transactionsINCOME = Arrays.asList(t13, t14, t15);

        transactionRepository.saveAll(transactionsBB);
        transactionRepository.saveAll(transactionsNUBANK);
        transactionRepository.saveAll(transactionsCASH);
        transactionRepository.saveAll(transactionsINCOME);

        w1.getTransactions().addAll(transactionsCASH);
        w1.getTransactions().addAll(transactionsINCOME);
        w2.getTransactions().addAll(transactionsBB);
        w3.getTransactions().addAll(transactionsNUBANK);

        walletRepository.saveAll(walletList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionsCASH);
        transactions.addAll(transactionsBB);
        transactions.addAll(transactionsNUBANK);
        transactions.addAll(transactionsINCOME);

        u1.getTransactions().addAll(transactions);
        u1.getWallets().addAll(walletList);
        userRepository.save(u1);
    }


}
