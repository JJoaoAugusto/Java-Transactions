package br.com.joaoaugusto.springbootapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.joaoaugusto.springbootapplication.dto.TransactionDto;
import br.com.joaoaugusto.springbootapplication.model.Transaction;
import br.com.joaoaugusto.springbootapplication.service.TransactionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    // Linkando o service

    final TransactionService transactionService;

    // Constructor

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // CRUD

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody final TransactionDto data) {
        final Transaction transaction = transactionService.createTransaction(data);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Transaction> retrieveTransaction(@PathVariable final String id) {
        final Transaction transaction = transactionService.retrieveTransaction(Long.parseLong(id));
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
