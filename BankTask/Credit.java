package com.company;

public class Credit extends BankProduct{

    public enum CreditType{HOME, CONSUMER}

    private double monthlyPayment;
    private CreditType creditType;
    private double creditMoney;

    public Credit(String name, double creditMoney, CreditType creditType, int period) {
        super(name, 0, period);
        this.creditType = creditType;
        if (creditType == CreditType.HOME){
            this.setYearlyInterest(0.06);
        }else if (creditType == CreditType.CONSUMER){
            this.setYearlyInterest(0.1);
        }
        this.creditMoney = creditMoney;
        this.monthlyPayment = Math.round(creditMoney/period*100)/100;

    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public double getCreditMoney() {
        return creditMoney;
    }
}
