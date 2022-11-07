package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.OperationType;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {

        OperationType type = transaction.getType();
        Double amount = transaction.getAmount();
        Long cbu = transaction.getCBU();
        try {
            if (type == OperationType.DEPOSIT) {
                accountService.deposit(cbu, amount);
            } else if (type == OperationType.WITHDRAW) {
                accountService.withdraw(cbu, amount);
            }
        }

        catch(DepositNegativeSumException i) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        catch(InsufficientFundsException e) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Optional<Collection<Transaction>> findByCBU(Long cbu) {
        Collection<Transaction> transactions = transactionRepository.findAll();

        transactions.removeIf(p -> p.getCBU() != cbu);

        return Optional.of(transactions);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
