package com.company;

public class Deposit extends BankProduct{

    public enum DepositType{SHORT, LONG}

    private double monthlyRevenue;
    private DepositType depositType;
    private double moneyDeposit;

    public Deposit(String name, double moneyDeposit, DepositType depositType) {
        super(name, 0, 0);
        this.depositType = depositType;
        if (depositType == DepositType.SHORT){
            this.setPeriod(3);
            this.setYearlyInterest(0.03);
        }else if (depositType == DepositType.LONG){
            this.setYearlyInterest(0.05);
            this.setPeriod(12);
        }
        this.moneyDeposit = moneyDeposit;
    }

    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public double getMoneyDeposit() {
        return moneyDeposit;
    }
}
