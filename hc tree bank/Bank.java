/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.util.HashMap;

/**
 * The Bank class acts as the interface between a user and their money.
 * The Bank consists of a HashMap of accounts and a interest rate for savings accounts.
 * Through this class users can create accounts, deposit to accounts,
 * withdraw from accounts, and transfer between accounts.
 * @author Hetsvi
 * @since 20May
 */
public class Bank {
    //Structure holding all accounts. Key = accountID, Value = account
    private HashMap<String, Account> accounts;
    //The interest rate given to savings account holders as a percent
    private double savingsInterestRate;

    /**
     * Default constructor creates an empty accounts map and set initial interest rate to 0%
     */
    public Bank() {
        //TODO: implement
        accounts =  new HashMap<String, Account>();
        savingsInterestRate = 0;
    }

    /**
     * Sets the savings interest rate
     *
     */
    // what does set it to 2% mean
    public void setSavingsInterest(double rate) {
        //TODO: implement
        this. savingsInterestRate = rate;
    }

    /**
     * Returns the number of accounts this bank has
     *
     */
    public int getNumberOfAccounts() {
        //TODO: implement
        return accounts.size();
    }

    /**
     * Gets the account associated with accountID
     *
     */
    private Account getAccount(String accountID) {
        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        if(!accounts.containsKey(accountID)) {
            System.out.println(noAccountMessage);
        }
        //TODO: implement
        return accounts.get(accountID);
    }

    /**
     * Creates a checking account with the given accountID and
     * returns true or false depending on success.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $0
     *
     */

    public boolean createCheckingAccount(String accountID, double initialDeposit) {
        String minimumMessage = "The minimum initial deposit for a checking" +
                " account was not met!";
        //TODO: implement
        if(!accounts.containsKey(accountID)){
            System.out.println(accountID + " DOES NOT HAVE AN ACCOUNT!");
            try {
                CheckingAccount newAccount = new CheckingAccount(
                        accountID, initialDeposit);
                accounts.put(newAccount.id, newAccount);
                return true;

            }

            catch (InsufficientFundsException e){
                System.out.println(minimumMessage);
                return false;
            }
        }

        return false;
    }

    /**
     * Creates a savings account with the given accountID and
     * returns true or false depending on success.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $100
     *
     */
    public boolean createSavingsAccount(String accountID, double initialDeposit) {
        String minimumMessage = "The minimum initial deposit for a saving " +
                "account was not met!";
        //TODO: implement
        if(!accounts.containsKey(accountID)){
            System.out.println(accountID + " DOES NOT HAVE AN ACCOUNT!");
            try {
                SavingsAccount newAccount = new SavingsAccount
                        (accountID, initialDeposit);
                accounts.put(newAccount.id, newAccount);
            }

            catch (InsufficientFundsException e){
                System.out.println(minimumMessage);
                return false;
            }
        }
        return true;
    }

    /**
     * Adds money to the account associated with accountID
     *
     */
    public Double deposit(String accountID, double amount)  {
        //TODO: implement
        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        if(!accounts.containsKey(accountID)) {
            System.out.println(accountID + noAccountMessage);
            return null;
        }

        return getAccount(accountID).deposit(amount);
       // return accounts.get(accountID).getBalance();
    }

    /**
     * Removes money from the account associated with accountID
     *
     */
    public Double withdraw(String accountID, double amount) {
        //TODO: implement

        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        if(!accounts.containsKey(accountID)) {
            System.out.println(accountID + noAccountMessage);
            return null;
        }

        // Example of how to print the message that have been passed to Exception
        try {
            // TODO: add proper statement here
            double withdrawAmt = getAccount(accountID).withdraw(amount);
            withdrawAmt = withdrawAmt- amount;
        } catch (InsufficientFundsException e) {

            //When there isn't enough money inform the user whats wrong and return null
            System.out.println(e.getMessage());
            return null;
        }

        //Withdrawal succeeded. Inform user of their updated balance
        return getAccount(accountID).getBalance();
    }

    /**
     * Moves money from an account to another account via "online" transfer.
     * No check fees are charged.
     *
     * If either account does not exist the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason,
     * the transfer fails.
     *
     */
    public boolean onlineTransfer(String fromAccountID, String toAccountID, double amount) {
        //TODO: implement
        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        if(!accounts.containsKey(toAccountID)) {
            System.out.println(toAccountID + noAccountMessage);
            return false;
        }

        if(!accounts.containsKey(fromAccountID)) {
            System.out.println(fromAccountID + noAccountMessage);
            return false;
        }

        try {
            // TODO: add proper statement here
            double withdrawAmt = accounts.get(fromAccountID).withdraw(amount);
            withdrawAmt = withdrawAmt- amount;
            } catch (InsufficientFundsException e) {

            //When there isn't enough money inform the user whats wrong and return null
            System.out.println(e.getMessage());
            return false;
        }

        double depositAmout = accounts.get(toAccountID).deposit(amount);
        return true;
    }

    /**
     * Moves money from an account to another account using a "check."
     *
     * If either account does not exist the transfer will fail.
     * If the from account is not a checking account the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason,
     * the transfer fails.
     *
     */
    public boolean checkTransfer(String fromAccountID, String toAccountID, double amount) {
      String notCheckingMessage = " IS NOT A CHECKING ACCOUNT!";
        //TODO: implement
        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        if(!accounts.containsKey(toAccountID)) {
            System.out.println(toAccountID + noAccountMessage);
            return false;
        }

        if(!accounts.containsKey(fromAccountID)) {
            System.out.println(fromAccountID + noAccountMessage);
            return false;
        }

            if (accounts.get(fromAccountID) instanceof SavingsAccount) {
                System.out.println(fromAccountID + " IS NOT A CHECKING ACCOUNT!");
                return false;
            }
            if(accounts.get(fromAccountID) instanceof CheckingAccount){
                try {
                    CheckingAccount fromAC = (CheckingAccount)accounts.get(fromAccountID);
                    fromAC.withdrawUsingCheck(amount);
                    CheckingAccount toAC = (CheckingAccount)accounts.get(toAccountID);
                    toAC.deposit(amount);
                }

                catch (InsufficientFundsException e){
                    System.out.println(e.getMessage());
                    return false;

                }
            }



    return true;

    }

    /**
     * Adds interest to every savings account.
     */
    public void addInterest() {
        //TODO: implement

        for(Account a : accounts.values()){
            if(a instanceof SavingsAccount){
                ((SavingsAccount) a).addInterest(savingsInterestRate);
            }
        }

    }

    /**
     * Prints the account information associated with accountID if an account exists.
     *
     */
    public void printAccount(String accountID) {
        //TODO: implement
        String noAccountMessage = " DOES NOT HAVE AN ACCOUNT!";
        if(!accounts.containsKey(accountID)) {
            System.out.println(accountID + noAccountMessage);
        }
        System.out.println(accounts.get(accountID).toString());
    }

}
