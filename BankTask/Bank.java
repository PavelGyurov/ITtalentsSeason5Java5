package com.company;

import java.util.ArrayList;

public class Bank {

    private String name;
    private String address;
    private ArrayList<BankProduct> bankProducts;
    private double reserve;
    private double allDepositMoney;

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
        this.bankProducts = new ArrayList<BankProduct>();
        reserve = 0;
        allDepositMoney = 0;
    }

    public void acceptDeposit(Client client, double deposit, Deposit.DepositType type){
        this.allDepositMoney += deposit;
        this.reserve += (0.9*deposit);
        bankProducts.add(new Deposit(client.getName() + "'s deposit", deposit, type));
    }

    public void evaluateCredit(Client client, double creditMoney, Credit.CreditType creditType, int period){
        double allMonthlyPayments = 0;
        for (Credit credits : client.getCredits()){
            allMonthlyPayments += credits.getMonthlyPayment();
        }
        if (allMonthlyPayments < client.getSalary()/2){
            if (this.reserve - creditMoney > this.getAllDepositMoney()*0.1){
                this.createCredit(client, creditMoney, creditType, period);
                System.out.println(this.name + " gave " + client.getName() + " a credit of " + creditMoney);
                this.allDepositMoney -= creditMoney;
            }else {
                System.out.println("The bank cannot afford to give this credit.");
            }
        }else {
            System.out.println(client.getName() + " cannot afford to pay this credit.");
        }
    }

    private void createCredit(Client client, double creditMoney, Credit.CreditType creditType, int period){
        bankProducts.add(new Credit(client.getName() + "'s Credit", creditMoney, creditType, period));
        client.addCredit(new Credit(client.getName() + "'s Credit", creditMoney, creditType, period));
    }

    public void payInterest(){
        for (BankProduct bp : bankProducts){
            if (bp instanceof Deposit){
                bp.setCurrentMoney(bp.getCurrentMoney() + (bp.getCurrentMoney() * bp.getYearlyInterest()));
            }
        }
    }

    //getters and setters

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<BankProduct> getBankProducts() {
        return bankProducts;
    }

    public double getReserve() {
        return reserve;
    }

    public double getAllDepositMoney() {
        for (BankProduct bp : bankProducts){
            if (bp instanceof Deposit){
                allDepositMoney += bp.getCurrentMoney();
            }
        }
        return allDepositMoney;
    }

    public void setAllDepositMoney(double allDepositMoney) {
        this.allDepositMoney = allDepositMoney;
    }
}
