package pl.wszib.edu.services;

import lombok.SneakyThrows;
import pl.wszib.edu.model.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MoneyService implements Runnable{

    private double money;
    private List<Double> logs;
    public MoneyService(double money) {
        this.money = money;
        this.logs = new ArrayList<>();
    }

    public double getMoney() {
        return money;
    }

    @SneakyThrows
    @Override
    public void run() {
        int quantity = getRandomDigit();
        while(quantity > 0){
            double showRandomMoney = getRandomMoney();
            this.money += roundTo2DecimalPlace(showRandomMoney);
            logs.add(showRandomMoney);
            System.out.println(Thread.currentThread().getName() + " Add " + roundTo2DecimalPlace(showRandomMoney) + " now balance is equal " + roundTo2DecimalPlace(money));
            quantity--;
        }
    }
    private double getRandomMoney() {
        return ThreadLocalRandom.current().nextDouble(20, 1000);
    }
    private int getRandomDigit(){
        return ThreadLocalRandom.current().nextInt(10);
    }
    public static double roundTo2DecimalPlace(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public List<Double> getLogs() {
        return logs;
    }
}
