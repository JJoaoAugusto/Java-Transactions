package br.com.joaoaugusto.springbootapplication.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    // Colunas da tabela transactions

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User payer;
    
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User payee;

    @Column(columnDefinition = "DECIMAL", nullable = false)
    private float value;

    @CreationTimestamp
    @Column(updatable = false)
    private Date date;

    // Constructors

    public Transaction() {
    }

    public Transaction(User payer, User payee, float value) {
        this.payer = payer;
        this.payee = payee;
        this.value = value;
    }

    // Getters e Setters -> Permite a leitura e edição de atributos privados

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Sobrescrição do método para ler as informações de forma limpa

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", payer=" + payer +
                ", payee=" + payee +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
