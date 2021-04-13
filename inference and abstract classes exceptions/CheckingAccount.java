/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * The CheckingAccount class extends Account.
 * This account does not give any interest.
 * It allows deposit and withdrawals with no fees.
 * The balance cannot go bellow $0 through a withdrawal. after which they cannot withdraw again
 *      until they deposit enough to cover their overdraft fee.
 *
 * A checking account also allows the users to use checks may be used to make withdrawals.
 * The first three checks are free, but subsequent uses add a fee of $2 to each check withdrawal.
 * Checks are allowed to take the balance negative (i.e., an overdraft).
 * If the balance goes negative the user incurs an overdraft fee of $35.
 * If the balance is negative we do not allow any additional withdrawals until enough money is
 * deposited to cover the fee.
 *
 * @author Hetsvi
 * @since dec 2018
 */

public class CheckingAccount extends Account {

    private int numberOfChecksUsed; // stores the number of checks used.
    private boolean inOverdraft; // Indicates if the account is currently having negative balance


    /**
     * Constructor for our banks checking accounts
     *
     * @param id The identifier associated with this Checking Account
     * @param initialDeposit The starting balance for this new Checking Account
     * @throws InsufficientFundsException if initialDeposit is too low
     */


   public CheckingAccount(String id, double initialDeposit) throws InsufficientFundsException{
        // TODO
       super(id, initialDeposit);
       double deposit = 0.01;
       if(initialDeposit < deposit){   // checks deposit
           throw new InsufficientFundsException();
       }

    }



    /**
     * This method makes the numberOfChecksUsed zero
     */

    public void resetChecksUsed(){
        // TODO
        this.numberOfChecksUsed =0;
    }


    /**
     * This method returns the numberOfChecksUsed.
     * @return the number of checks used this month
     */

    public int getChecksUsed(){
        // TODO

        return this.numberOfChecksUsed;
    }


    /**
     * Returns a string that contains the id, the type of account (Checking or Savings), and balance.
     * For example, if the user id is "ghosh", account type is checking account, and balance is "120.30",
     * the string returned will be as follows (identical punctuation and spaces):
     * ID: ghosh, Type: Checking, Balance: 120.30
     * @return the String that represents this checking account
     */

    public String toString() {
        String id = this.getId();
        double balance = this.getBalance();

        // TODO: set both local variables to proper value

        return "ID: " + id + ", Type: Checking, Balance: " + balance;  // string
    }


    /**
     * Overrides the deposit method. Adds money to the checking account and check if it covers negative balance.
     *
     * @param amount The amount the customer wishes to add to the account
     * @return The updated balance in the account
     */

    @Override
    public double deposit(double amount) {
        // TODO
        int over = 35;
        this.balance =  this.getBalance() + amount;
        if(inOverdraft == true){
            if(this.getBalance() >= over){
                this.balance =  this.getBalance() - over;    // if overdraft is true subtract
                inOverdraft = false;

            }
        }

        return this.balance;
    }


    /**
     * Implements abstract withdraw method.
     * Rejects withdrawal if the withdrawal would put the account bellow $0 and throws an exception.
     * Rejects withdrawal if the account is in overdraft and throws an exception
     *
     * @param amount The amount the customer wishes to redeem from the account.
     * @throws InsufficientFundsException if withdrawal would put account under $0, with a message
     *                                      indicating either the maximum amount they could withdraw
     *                                    if the account is in overdraft, with a message indicating
     *                                      the minimum amount they must deposit to make a withdrawal.
     * @return The balance left in the account
     */

    public double withdraw(double amount) throws InsufficientFundsException{
        // TODO
        int over = 35;
        if(this.getBalance()- amount < 0){
            double possibleWithdraw = this.getBalance();
            String exception = "THE MAXIMUM AMOUNT THE USER: (" + this.getId() +
                    ") CAN WITHDRAW FROM THEIR CHECKING ACCOUNT IS " + "($" +
                    possibleWithdraw +")";

            throw new InsufficientFundsException(exception);
        }

        if(this.inOverdraft== true){
            double reqDeposit = this.getBalance() - over;
            String overdraft = "USER: (" + this.getId() + ") CANNOT MAKE A " +
                    "WITHDRAWAL FROM THEIR CHECKING ACCOUNT UNTIL THEY COVER " +
                    "THEIR NEGATIVE BALANCE WITH A DEPOSIT OF AT LEAST ($"
                    + reqDeposit + ")";
        }

        this.balance = this.getBalance() - amount;   // new balance
        return this.balance;
    }



    /**
     * Method pulls money from the users account when someone cashes their check.
     *
     * If the account is in overdraft reject the check and inform the check writer(id) of how much they need to deposit
     * to make withdrawals.
     *
     * Otherwise this check can withdraw any amount of money. If it puts the account balance bellow $0 place the account
     * in overdraft.
     *
     * If the check writer has already used up his free checks, there is an additional $2 fee for this withdrawal.
     *
     * @param amount The amount the customer wishes to withdraw from his account
     * @throws InsufficientFundsException if the account is in overdraft, with a message indicating
     *                                        the minimum amount they must deposit to make a withdrawal.
     * @return the remaining balance in the account*/

    public double withdrawUsingCheck(double amount) throws InsufficientFundsException{
        // TODO
        int over = 35;
        int cheque = 3;
        int cheq = 2;

        if(this.getBalance() < 0 || inOverdraft == true){
            double req = -(this.getBalance());
            String overdraft = "USER: (" + this.getId() + ") CANNOT MAKE A " +
                    "WITHDRAWAL FROM THEIR CHECKING ACCOUNT UNTIL THEY COVER " +
                    "THEIR NEGATIVE BALANCE WITH A DEPOSIT OF AT LEAST ($" +
                    req + ")";
            throw new InsufficientFundsException(overdraft);
        }

        else{
            if(numberOfChecksUsed < cheque){         // checks for adding fee or nah
                if(this.getBalance() -  amount < 0){
                    this.inOverdraft = true;
                    this.numberOfChecksUsed = this.numberOfChecksUsed +1;
                    this.balance = this.getBalance() - amount -over;
                    return this.getBalance();
                }

                else{
                    this.numberOfChecksUsed = this.numberOfChecksUsed +1;
                    this.balance = this.getBalance() - amount;
                    return  this.getBalance();

                }
            }

            if(numberOfChecksUsed >= cheque){
                if(this.getBalance() -amount <0){
                    this.inOverdraft = true;
                    this.numberOfChecksUsed = this.numberOfChecksUsed +1;
                    this.balance = this.getBalance() - amount -over - cheq;  // adds check fee
                    return this.getBalance();
                }

                else{
                    this.numberOfChecksUsed = this.numberOfChecksUsed +1;
                    this.balance = this.getBalance() - amount - cheq;
                    return this.balance;
                }
            }


        }

        this.balance = this.getBalance() - amount;  // new balance
        return this.getBalance();
    }
}

