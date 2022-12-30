package pl.wszib.edu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String surname;
    private double account_balance;

    public Account() {
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public Account(String name, String surname, double account_balance) {
        this.name = name;
        this.surname = surname;
        this.account_balance = account_balance;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public double getAccount_balance() {
        return account_balance;
    }
}
