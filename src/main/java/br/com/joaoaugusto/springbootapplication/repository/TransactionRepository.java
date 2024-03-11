package br.com.joaoaugusto.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.joaoaugusto.springbootapplication.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
