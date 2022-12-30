package pl.wszib.edu.services;

import java.util.concurrent.ThreadLocalRandom;

public class MoneyService implements Runnable{

    private double money;

    public MoneyService(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public void run() {
        System.out.println("\nChanging the balance start");
        int quantity = 0;
        while(quantity < 10){
            double showRandomMoney = getRandomMoney();
            this.money += roundTo2DecimalPlace(showRandomMoney);
            System.out.println("Add " + roundTo2DecimalPlace(showRandomMoney) + " now balance is equal " + roundTo2DecimalPlace(money));
            quantity++;
        }
    }
    private double getRandomMoney() {
        return ThreadLocalRandom.current().nextDouble(20, 1000);
    }
    public static double roundTo2DecimalPlace(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
