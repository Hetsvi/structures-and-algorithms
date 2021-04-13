/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * The SavingsAccount class extends Account.
 * The minimum amount a customer can keep in a savings account is $100.
 * If the customer deposits at least $5000 initially the bank gives them an additional $250 bonus
 * This account earns interest at a rate specified by the bank.
 * It allows deposits with no fees.
 * Withdrawals are charged $2 per withdrawal.
 * The balance cannot go below $100 through a withdrawal.
 *
 * Refer to the writeup for a more detailed description!
 *
 * @author Hetsvi
 * @since 20May
 */
public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 100.0;

    /**
     * Constructor for our banks savings accounts
     * If initial deposit is at least $5000 the customer receives a $250 bonus
     *
     */
    public SavingsAccount(String userid, double initialDeposit) throws InsufficientFundsException {
        //TODO: Implement
        super(userid, initialDeposit);
        int special = 5000;
        int addSpecial = 250;

        if(initialDeposit < MINIMUM_BALANCE){
            throw new InsufficientFundsException();
        }

        if(initialDeposit >= special){
            this.balance = initialDeposit + addSpecial;
        }
    }


    /**
     * Implements abstract deposit method.
     * Adds money to the savings account.
     *
     */
    public double deposit(double amount) {
        //TODO: Implement
        double balance = this.getBalance();
        this.balance = balance + amount;
        return this.balance;
    }


    /**
     * Implements abstract withdraw method.
     * Savings accounts at our bank charge a WITHDRAWAL_FEE.
     * Rejects withdrawal if the withdrawal would put the account below the minimum balance
     * and throws an exception.
     *
     */
    public double withdraw(double amount) throws InsufficientFundsException {
        //TODO: Implement
        int charge = 2;
        int balanceLess = 102;
        //Get the new balance
        double potentialBalance = this.getBalance() - amount - charge;

        //Reject if withdrawal violates our constraints
        if (potentialBalance < MINIMUM_BALANCE) {
            double maxPossibleWithdrawal =0;
            if(this.balance >= balanceLess){
                 maxPossibleWithdrawal = this.getBalance() - MINIMUM_BALANCE - charge;
            }
            else{
                maxPossibleWithdrawal = 0;

            }
            String exceptionMSG =
                    "THE MAXIMUM AMOUNT THE USER: (" + id + ") CAN "
                            + "WITHDRAW FROM THEIR SAVINGS ACCOUNT "
                            + "IS ($" + maxPossibleWithdrawal + ")";
            throw new InsufficientFundsException(exceptionMSG);
        }
        this.balance = this.getBalance() -amount - charge;
        return this.balance;
    }


    /**
     * Method that the bank will use to add an interest payment to the savings account.
     *
     * NewBalance = OldBalance + OldBalance*InterestRate
     * ie. If OldBalance was $1000.00 and InterestRate = 2%
     *        NewBalance would be $1020.00
     */
    public double addInterest(double rate) {
        //TODO: Implement
        int divider = 100;
        this.balance = this.getBalance() + ((this.getBalance()*rate)/divider);
        return this.balance;
    }
}
