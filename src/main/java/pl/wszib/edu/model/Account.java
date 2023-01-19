package pl.wszib.edu.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String surname;
    private double account_balance;
    @ElementCollection
    private List<Double> logs;

    public Account() {
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public Account(String name, String surname, double account_balance) {
        this.name = name;
        this.surname = surname;
        this.account_balance = account_balance;
        this.logs = new ArrayList<>();
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

    public List<Double> getLogs() {
        return logs;
    }

    public void setLogs(List<Double> logs) {
        this.logs.addAll(logs);
    }
}
