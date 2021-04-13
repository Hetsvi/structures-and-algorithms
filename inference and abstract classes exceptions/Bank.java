/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Bank class acts as the interface between a user and their money.
 * The Bank consists of an ArrayList of accounts and a interest rate for savings accounts.
 * Through this class users can create accounts, deposit to accounts,
 * withdraw from accounts, and transfer between accounts.
 *
 * @author Hetsvi
 * @since dec 2018
 */
public class Bank {
    private ArrayList<Account> accounts; // Structure holding all accounts.
    private double savingsInterestRate; // The interest rate given to savings account holders as a percent

    /**
     * Default constructor creates an empty accounts Array List and set initial interest rate to 0%
     */
    public Bank() {
        // TODO
        accounts = new ArrayList<>();
        savingsInterestRate =0;

    }

    /**
     * Sets the savings interest rate
     * @param rate The rate which will be the new savings interest rate
     */
    public void setSavingsInterest(double rate) {
        // TODO
        this.savingsInterestRate = rate;

    }

    /**
     * Returns the number of checking accounts this bank has
     * @return the number of checking accounts in this bank
     */
    public int getNumberOfCheckingAccounts() {
        // TODO
        int counter = 0;
        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i) instanceof CheckingAccount){
                counter = counter +1;
            }
        }
        return counter;
    }

    /**
     * Returns the number of savings accounts this bank has
     * @return the number of savings accounts in this bank
     */
    public int getNumberOfSavingsAccounts() {
        // TODO
        int counter =0;
        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i) instanceof SavingsAccount){  // checks if instance of
                counter = counter + 1;
            }
        }
        return counter;
    }

    /**
     * Helper method to get the checking account associated with accountID
     *
     * @param accountID the id of the account to obtain
     * @return If there exists an checking account with accountID return it. Otherwise return null
     */
    private Account getCheckingAccount(String accountID) {
        // TODO
        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i).getId() == accountID && accounts.get(i) instanceof CheckingAccount){
                return accounts.get(i);
            }
        }

        return null;
    }

    /**
     * Helper method to get the savings account associated with accountID
     *
     * @param accountID the id of the account to obtain
     * @return If there exists an savings account with accountID return it. Otherwise return null
     */
    private Account getSavingsAccount(String accountID) {
        // TODO
        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i).getId() == accountID && accounts.get(i) instanceof SavingsAccount){
                return accounts.get(i);
            }
        }

        return null;
    }

    /**
     * Creates a checking account with the given accountID and returns true or false depending on success.
     * You also need to print the proper message in each case, check write up for more details.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $0
     *
     * @param accountID The id the user wants associated to this new account.
     * @param initialDeposit The starting balance of the new account in dollars
     * @return True if account created successfully. False if not.
     */
    public boolean createCheckingAccount(String accountID, double initialDeposit) {
        String successMSG = "Successully created checking account for " + accountID  + ".";
        String failMSG = accountID + " ALREADY HAD A CHECKING ACCOUNT!";
        String minimumMSG = "THE MINIMUM INITIAL DEPOSIT FOR A CHECKING ACCOUNT WAS NOT MET!";

        // TODO

        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i).getId() == accountID){   // checks if account is there
                System.out.println(failMSG);
                return false;
            }
        }

        try {
            CheckingAccount newCheq = new CheckingAccount(accountID, initialDeposit);
            accounts.add(newCheq);   // adds new account
            System.out.println(successMSG);
            return true;

        } catch (InsufficientFundsException e) {
            System.out.println(minimumMSG);
            return false;

        }

    }


    /**
     * Creates a savings account with the given accountID and returns true or false depending on success.
     * You also need to print the proper message in each case, check write up for more details.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $100
     *
     * @param accountID The id the user wants associated to this new account.
     * @param initialDeposit The starting balance of the new account in dollars
     * @return True if account created successfully. False if not.
     */
    public boolean createSavingsAccount(String accountID, double initialDeposit) {
        String successMSG = "Successully created savings account for " + accountID  + ".";
        String failMSG = accountID + " ALREADY HAD A SAVINGS ACCOUNT!";
        String minimumMSG = "THE MINIMUM INITIAL DEPOSIT FOR A CHECKING ACCOUNT WAS NOT MET!";

        // TODO
      /*  if(getAccount(accountID, false) != null){
            System.out.println(failMSG);
            return false;
        }*/

        for(int i =0; i< accounts.size(); i++) {
            if (accounts.get(i).getId() == accountID) {   // checks if account exist
                System.out.println(failMSG);
                return false;
            }

        }

        try {
            SavingsAccount newSa = new SavingsAccount(accountID, initialDeposit);
            accounts.add(newSa);   // adds new saving account
            System.out.println(successMSG);
            return true;
        }

        catch (InsufficientFundsException e){
            System.out.println(minimumMSG);
            return false;
        }

    }


    /**
     * Helper method to get the account and print proper messages if no account is found.
     *
     * @param accountID the given accountID
     * @param isChecking true if is checking account, false otherwise
     * @return the proper account
     */
    public Account getAccount(String accountID, boolean isChecking) {
        String noCheckingMSG = accountID + " DOES NOT HAVE A CHECKING ACCOUNT!";
        String noSavingsMSG = accountID + " DOES NOT HAVE A SAVINGS ACCOUNT!";

        // TODO
        for(int i =0 ; i < accounts.size(); i++){
            if(isChecking == true){
                if(accounts.get(i) instanceof CheckingAccount && accounts.get(i).getId().equals(accountID)){
                    return accounts.get(i);  // returns according to whether checking and id equals
                }
            }


            else if(isChecking == false) {
                if(accounts.get(i) instanceof SavingsAccount && accounts.get(i).getId().equals(accountID)){
                    return accounts.get(i);
                }
            }

        }

        if(isChecking == true){
                System.out.println(noCheckingMSG);  // prints if no account
        }
        if(isChecking == false){
            System.out.println(noSavingsMSG);   // prints if no account
        }

        return null;
    }

    /**
     * Adds money to the account associated with accountID
     *
     * @param isChecking true if deposit to checking, false if to savings
     * @param accountID the id of the account to add money too
     * @param amount the amount of dollars to add to the account
     * @return The new balance of the account after deposit. Null if no account exists with accountID
     */
    public Double deposit(boolean isChecking, String accountID, double amount)  {
        // TODO
        if(isChecking == true){
            if(getAccount(accountID,isChecking) == null){
                return null;
            }
            double balance = getAccount(accountID, isChecking).getBalance();
            double newAmt = balance + amount;  // new balance
            getAccount(accountID, isChecking).deposit(amount);

            return newAmt;

        }

        else{

            double bal = getAccount(accountID, isChecking).getBalance();
            double amt = bal + amount;
            getAccount(accountID, isChecking).deposit(amount);  // gets account to deposit
            if(getAccount(accountID,isChecking) == null){
                return null;
            }
            return amt;
        }

    }

    /**
     * Removes money from the account associated with accountID
     *
     * @param isChecking true if withdraw from checking, false if from savings
     * @param accountID the id of the account to take money from
     * @param amount the amount of dollars to add to the account
     * @return The new balance of the account after withdrawal. Null if no account exists with accountID
     */
    public Double withdraw(boolean isChecking, String accountID, double amount) {

        // TODO
        try{
            if(isChecking == true){
                if(getAccount(accountID,isChecking) == null){
                    return null;
                }
                getAccount(accountID, isChecking).withdraw(amount);

                return getAccount(accountID, isChecking).getBalance();
            }

            else{
                if(getAccount(accountID,isChecking) == null){
                    return null;
                }
                getAccount(accountID, isChecking).withdraw(amount);  // gets account to withdraw

                return getAccount(accountID, isChecking).getBalance();
            }

        }

        catch(InsufficientFundsException e){
            System.out.println(e.getMessage());

        }

        return null;
    }

    /**
     * Moves money from an account to another account via "online" transfer. No check fees are charged.
     *
     * If either account does not exist the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason the transfer fails.
     *
     * @param fromAccountID the id of the account to take money from
     * @param isFromChecking true if transfer from checking, false if from savings
     * @param toAccountID the id of the account to put money in
     * @param isToChecking true if transfer to checking, false if to savings
     * @param amount the amount of dollars to add to the account
     * @return Whether or not the transfer succeeded.
     */
    public boolean onlineTransfer(String fromAccountID, boolean isFromChecking, String toAccountID,
                                  boolean isToChecking, double amount) {
        // TODO

            // checks for when to print error statements
            if(isFromChecking == true && getAccount(fromAccountID, isFromChecking) == null){
               // System.out.println(fromAccountID + " DOES NOT HAVE A CHECKING ACCOUNT!" );
                return false;
            }

            if(isFromChecking == false && getAccount(fromAccountID, isFromChecking) == null ){
               // System.out.println(fromAccountID + " DOES NOT HAVE A SAVINGS ACCOUNT!");
                return false;
            }

            if(isToChecking == true && getAccount(toAccountID, isToChecking) == null){
               // System.out.println(toAccountID + " DOES NOT HAVE A CHECKING ACCOUNT!" );
                return false;
            }

            if(isToChecking == false && getAccount(toAccountID, isToChecking) == null){
              //  System.out.println(toAccountID + "DOES NOT HAVE A SAVINGS ACCOUNT!");
                return false;
            }



        try{
            getAccount(fromAccountID, isFromChecking).withdraw(amount); // withdraws

        }

        catch (InsufficientFundsException e){
            System.out.println(e.getMessage());
            return false;
        }

        getAccount(toAccountID, isToChecking).deposit(amount); // deposits
        return true;

    }

    /**
     * Moves money from an account to another account using a "check."
     *
     * If the from account is not a checking account the transfer will fail.
     * If either account does not exist the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason the transfer fails.
     *
     * @param fromAccountID the id of the account to take money from
     * @param toAccountID the id of the account to put money in
     * @param amount the amount of dollars to add to the account
     * @return Whether or not the transfer succeeded.
     */
    public boolean checkTransfer(String fromAccountID,boolean isFromChecking, String toAccountID,
                                 boolean isToChecking, double amount) {
        String shouldUseCheckingMSG = fromAccountID + " SHOULD USE A CHECKING ACCOUNT!";
        // TODO

        if(isFromChecking == false){   // from shouldnt be saving
            System.out.println(shouldUseCheckingMSG);
            return false;
        }

        if(isFromChecking == true ){
            try {
                if(getAccount(fromAccountID, isFromChecking) == null){
                    return false;
                }

                if(getAccount(toAccountID, isToChecking) == null){
                    return false;
                }
                // casts to checking account
                CheckingAccount from = (CheckingAccount) getAccount(fromAccountID, isFromChecking);
                from.withdrawUsingCheck(amount);
                getAccount(toAccountID, isToChecking).deposit(amount);
            }

            catch (InsufficientFundsException e){
                System.out.println(e.getMessage()); // prints message
                return false;
            }
        }

        return true;
    }

    /**
     * Adds interest to every savings account.
     */
    public void addInterest() {
        // TODO
        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i) instanceof SavingsAccount){
                ((SavingsAccount) accounts.get(i)).addInterest(savingsInterestRate);
            }
        }
    }

    /**
     * First, if accountID has a checking account, print its information. Then, if accountID has a savings account,
     * print its information. Check write up for more details.
     *
     * @param accountID the id of the account to obtain
     */
    public void printAccount(String accountID) {
        // TODO
        for(int i =0; i< accounts.size(); i++){
            if(accounts.get(i) instanceof CheckingAccount){
                accounts.get(i).toString();   // calls toString method
                getAccount(accountID, true);
            }

            else{
                accounts.get(i).toString();
                getAccount(accountID, false);
            }

        }
    }

}
