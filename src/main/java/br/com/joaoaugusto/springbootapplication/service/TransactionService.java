package br.com.joaoaugusto.springbootapplication.service;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.joaoaugusto.springbootapplication.dto.TransactionDto;
import br.com.joaoaugusto.springbootapplication.exception.AppException;
import br.com.joaoaugusto.springbootapplication.model.Transaction;
import br.com.joaoaugusto.springbootapplication.model.User;
import br.com.joaoaugusto.springbootapplication.repository.TransactionRepository;
import br.com.joaoaugusto.springbootapplication.repository.UserRepository;

@Service
public class TransactionService {

    // Linkando o repositório

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    // Contructor

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    // CRUD

    public Transaction createTransaction(final TransactionDto data) {

        User payer = userRepository.findById(data.getPayerId()).orElseThrow(() -> new AppException("Payer not found", HttpStatus.NOT_FOUND));
        if (Objects.equals(payer.getType(), "SELLER") ) {
            throw new AppException("SELLER type users can not send money", HttpStatus.FORBIDDEN);
        }

        User payee = userRepository.findById(data.getPayeeId()).orElseThrow(() -> new AppException("Payee not found", HttpStatus.NOT_FOUND));
        if (Objects.equals(payee.getType(), "COMMON")) {
            throw new AppException("COMMON type users can not receive money", HttpStatus.FORBIDDEN);
        }

        final float payerBalance = payer.getBalance();
        payer.setBalance(payerBalance - data.getValue());

        if (payerBalance < data.getValue()) {
            throw new AppException("Payer balance not sufficient", HttpStatus.FORBIDDEN);
        }

        final float payeeBalance = payee.getBalance();
        payee.setBalance(payeeBalance + data.getValue());

        final Transaction transaction = new Transaction(payer, payee, data.getValue());
        return transactionRepository.save(transaction);
    }

    public Transaction retrieveTransaction(final long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("Transaction not found", HttpStatus.NOT_FOUND));
    }

}
