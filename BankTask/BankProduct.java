package com.company;

public abstract class BankProduct {

    private String name;
    private double yearlyInterest;
    private int period;
    private double currentMoney;

    public BankProduct(String name, double yearlyInterest, int period) {
        this.name = name;
        if (yearlyInterest > 0){
            this.yearlyInterest = yearlyInterest;
        }else {
            this.yearlyInterest = 0.01;
        }
        if (period > 0 && period < 60){
            this.period = period;
        }else {
            this.period = 12;
        }
    }

    public String getName() {
        return name;
    }

    public double getYearlyInterest() {
        return yearlyInterest;
    }

    public int getPeriod() {
        return period;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearlyInterest(double yearlyInterest) {
        this.yearlyInterest = yearlyInterest;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }
}
