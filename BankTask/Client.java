package com.company;

import java.util.ArrayList;

public class Client {

    private String name;
    private String address;
    private double money;
    private double salary;
    ArrayList<Credit> credits;
    ArrayList<Deposit> deposits;

    public Client(String name, String address, double money, double salary) {
        this.name = name;
        this.address = address;
        if (money > 0){
            this.money = money;
        }else {
            money = 0;
        }
        if (salary > 420){
            this.salary = salary;
        }else {
            this.salary = 420;
        }
        credits = new ArrayList<Credit>();
        deposits = new ArrayList<Deposit>();
    }


    public void createDeposit(Bank bank, double money, Deposit.DepositType type){
        if (money <= this.money){
            bank.acceptDeposit(this, money, type);
            this.money -= money;
            bank.setAllDepositMoney(bank.getAllDepositMoney() + money);
            System.out.println(this.name + " deposited " + money);
            this.deposits.add(new Deposit(this.name + "'s deposit", money, type));
        }else {
            System.out.println(this.getName() + " doesn't have this much money.");
        }
    }

    public void askForCredit(Bank bank, double creditMoney, Credit.CreditType type, int period){
        bank.evaluateCredit(this, creditMoney, type, period);
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getMoney() {
        return money;
    }

    public double getSalary() {
        return salary;
    }

    public ArrayList<Credit> getCredits() {
        return credits;
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public void addCredit(Credit credit){
        this.credits.add(credit);
    }
}
