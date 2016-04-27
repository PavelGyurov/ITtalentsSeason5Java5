package com.company;

import java.util.Random;

public class Demo {

    public static void main(String[] args) {

        Bank ktb = new Bank("KTB", "Bulgaria");
        Client[] clients = new Client[10];

        Client c1 = new Client("Client1", "Address1", 2000, 800);
        clients[0] = c1;
        Client c2 = new Client("Client2", "Address2", 1000, 950);
        clients[1] = c2;
        Client c3 = new Client("Client3", "Address3", 100, 450);
        clients[2] = c3;
        Client c4 = new Client("Client4", "Address4", 500, 1200);
        clients[3] = c4;
        Client c5 = new Client("Client5", "Address5", 5005, 2500);
        clients[4] = c5;
        Client c6 = new Client("Client6", "Address6", 10000, 700);
        clients[5] = c6;
        Client c7 = new Client("Client7", "Address7", 12750, 685);
        clients[6] = c7;
        Client c8 = new Client("Client8", "Address8", 230, 750);
        clients[7] = c8;
        Client c9 = new Client("Client9", "Address9", 480, 8000);
        clients[8] = c9;
        Client c10 = new Client("Client10", "Address10", 10, 450);
        clients[9] = c10;

        Random rand = new Random();
        for (int i = 0; i < clients.length; i++) {
            double depositPercent = 0.8 +  (rand.nextDouble() * (1.0 - 0.8));
            double deposit = depositPercent*clients[i].getMoney();
            deposit *= 100;
            deposit = Math.round(deposit);
            deposit /= 100;
            Deposit.DepositType type;
            if (rand.nextBoolean()){
                type = Deposit.DepositType.SHORT;
            }else {
                type = Deposit.DepositType.LONG;
            }
            clients[i].createDeposit(ktb, deposit, type);
        }

        System.out.println("Bank's deposit money: " + ktb.getAllDepositMoney());
        System.out.println("Bank's reserve: " + ktb.getReserve());

        for (int i = 0; i < clients.length; i++) {
            int credit = rand.nextInt(10000);
            int period = rand.nextInt(60);
            Credit.CreditType type;
            if (rand.nextBoolean()){
                type = Credit.CreditType.CONSUMER;
            }else {
                type = Credit.CreditType.HOME;
            }
            clients[i].askForCredit(ktb, (double)credit, type, period);
        }



        System.out.println("----- Bank's transactions -----");
        for (BankProduct bp : ktb.getBankProducts()){
            if (bp instanceof Deposit){
                System.out.println("Deposit name: " + bp.getName() + ", money deposited:  " + ((Deposit) bp).getMoneyDeposit() +
                        ". interest: " + bp.getYearlyInterest()*100 + " percent, period: " + bp.getPeriod() + " months");
            }
            if (bp instanceof Credit){
                System.out.println("Credit name: " + bp.getName() + ", monthly payment: " + ((Credit) bp).getMonthlyPayment() +
                ", interest: " + bp.getYearlyInterest()*100 + " percent, period: " + bp.getPeriod() + " months");
            }
        }

        System.out.println("----- Clients -----");

        for (int i = 0; i < clients.length; i++) {
            Client client = clients[i];
            System.out.println("Client name: " + client.getName() + ", money: " + client.getMoney() + ", deposits: ");
            for (Deposit dp : client.getDeposits()){
                System.out.print(" Deposit name: " + dp.getName() + ", money: " + dp.getMoneyDeposit() + " ");
            }
            for (Credit cr : client.getCredits()){
                System.out.print(" Credit name: " + cr.getName() + ", money: " + cr.getCreditMoney() + ", monthly payment: " + cr.getMonthlyPayment() + " ");
            }
            System.out.println();
        }

    }
}
