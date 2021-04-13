/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * The SavingsAccount class extends Account.
 * The minimum amount a customer can keep in a savings account is $100.
 * If the customer deposits at least $5000 initially the bank gives them an additional $100 bonus
 * This account earns interest at a rate specified by the bank.
 * It allows deposits with no fees.
 * Withdrawals are charged $2 per withdrawal.
 * The balance cannot go below $100 through a withdrawal.
 *
 * @author Hetsvi
 * @since dec 2018
 */

public class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 100.0;


    /**
     * Constructor for our banks savings accounts
     *
     * If initial deposit is at least $5000 the customer recieves a $100 bonus
     *
     * @param userid The identifier associated with this Savings Account
     * @param initialDeposit The starting balance for this new Savings Account
     * @throws InsufficientFundsException if initialDeposit is too low
     * */
    public SavingsAccount(String userid, double initialDeposit) throws InsufficientFundsException{
    super(userid, initialDeposit);
    int special = 5000;
    int addSpecial = 100;


    if(initialDeposit < MIN_BALANCE ){   // checks balance
        throw new InsufficientFundsException();
    }

    if(initialDeposit >= special){
        this.balance = initialDeposit + addSpecial;
    }
        // TODO
    }



    /**
     * Returns a string that contains the id, the type of account (Checking or Savings), and balance.
     * For example, if the user id is "ghosh", account type is savings account, and balance is "120.30",
     * the string returned will be as follows (identical punctuation and spaces):
     * ID: ghosh, Type: Savings, Balance: 120.30
     * @return the string that represents this saving account
     */

    public String toString() {
        String id = getId();
        double balance = getBalance();

        // TODO: set both local variables to proper value

        return "ID: " + id + ", Type: Savings, Balance: " + balance;  // returns string
    }


    /**
     * Implements abstract withdraw method.
     * Savings accounts at our bank charge a WITHDRAWAL_FEE.
     * Rejects withdrawal if the withdrawal would put the account bellow the minimum balance and throws an exception.
     *
     * @param amount The amount the customer wishes to redeem from the account.
     * @throws InsufficientFundsException if withdrawal would put account under MINIMUM_BALANCE with a message
     *                                      indicating the maximum amount they could withdraw.
     * @return The balance left in the account
     */

    public double withdraw(double amount) throws InsufficientFundsException {
        // TODO
        int charge = 2;
        int balanceLess = 102;

        double potentialBalance = this.getBalance() - amount - charge;
        if(potentialBalance < MIN_BALANCE){
            double possibleWithdraw = 0;
            if(this.getBalance() >= balanceLess){   // withdraw with charge
                possibleWithdraw = this.getBalance() - MIN_BALANCE - charge;

            }

            else{
                possibleWithdraw = 0;
            }

            String exception = "THE MAXIMUM AMOUNT THE USER: (" + this.getId() +
                    ") CAN WITHDRAW ROM THEIR SAVINGS ACCOUNT IS ($" + possibleWithdraw + ")";
            throw new InsufficientFundsException(exception);

        }

        this.balance = this.getBalance() - amount - charge;   // updated balance

        return this.getBalance();
    }



    /**
     * Method that the bank will use to add an interest payment to the savings account.
     *
     * NewBalance = OldBalance + OldBalance*InterestRate
     * ie. If OldBalance was $1000.00 and InterestRate = 2%
     *        NewBalance would be $1020.00
     *
     * @param rate the interest rate in percent. For example 2% => rate is 2.0
     * @return The update balance in the account*/

    public double addInterest(double rate){
        // TODO
        int divier = 100;
        this.balance = this.getBalance() +((this.getBalance() * rate) / divier);
        return this.balance;  // balance with interest
    }
}

