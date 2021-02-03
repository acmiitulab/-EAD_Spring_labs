package com.example.demo;


import java.util.ArrayList;

public class Bank implements BankService {
    private ArrayList<Account> accounts;


    public Bank() {}

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }


    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(String username) {
        for (Account account : accounts) {
            if (username.equals(account.getUsername()) ){
                return account;
            }
        }

        return null;
    }


    public boolean validate(String username, String pinCode) {
       for (Account account : accounts) {
           if (username.equals(account.getUsername()) && pinCode.equals(account.getPin())) {
               return true;
           }
       }
       return false;
    }


    // BankService methods
    @Override
    public double checkBalance(String cardNumber) {
        Account account = getAccount(cardNumber);

        return account == null ? 0 : account.getBalance();
    }

    @Override
    public boolean withdraw(double amount, String cardNumber) {
        Account account = getAccount(cardNumber);


        if (account == null || account.getBalance() - amount < 0) {
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        return true;
    }

    @Override
    public boolean topUp(double amount, String cardNumber) {
        Account account = getAccount(cardNumber);


        if (account == null) {
            return false;
        }

        account.setBalance(account.getBalance() + amount);
        return true;
    }


    public void changePinCode(Account account, String newPinCode) {
        account.setPin(newPinCode);
    }

    // Method toString


    @Override
    public String toString() {
        return "Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
