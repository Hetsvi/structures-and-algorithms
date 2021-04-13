/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * The CheckingAccount class extends Account.
 * This account does not give any interest.
 * It allows deposit and withdrawals with no fees.
 * The balance cannot go below $0 through a withdrawal.
 *
 * A checking account also allows the users to use checks may be used to make withdrawals.
 * The first three check uses in a month are free,
 * but subsequent uses add a fee of $2 to each check withdrawal.
 * Checks are allowed to take the balance negative (i.e., an overdraft).
 * If the balance goes negative the user incurs an overdraft fee of $35.
 * If the balance is negative we do not allow any additional
 *     withdrawals until enough money is deposited to cover the fee.
 *
 * Refer to the writeup for a more detailed description!
 * @author Hetsvi
 * @since 20May
 */
public class CheckingAccount extends Account {

    // Stores the number of checks used every month.
    // Starts at 0, and can be reset using a method below.
    private int numberOfChecksUsed;

    // Indicates wether the account currently has an uncovered overdraft fee
    private boolean inOverdraft;

    /**
     * Constructor for our banks checking accounts
     *
     */


    public CheckingAccount(String id, double initialDeposit) throws InsufficientFundsException {
        //TODO: implement

        super(id, initialDeposit);
        double initalDep = 0.01;
        if(initialDeposit < initalDep){
            throw new InsufficientFundsException();
        }
    }

    /**This method makes the numberOfChecksUsed zero.*/
    public void resetChecksUsed() {
        //TODO: implement
        this.numberOfChecksUsed = 0;

    }

    /**
     * This method returns the numberOfChecksUsed.
     *
     */
    public int getChecksUsed() {
        //TODO: implement
        return this.numberOfChecksUsed;
    }

    /**
     * Implements abstract deposit method.
     * Adds money to the checking account and returns the new balance.
     *
     *
     */
    public double deposit(double amount) {
        //TODO: implement
        int over = 35;

        this.balance = this.getBalance() + amount;
        if(inOverdraft == true){
//            if(currBal >= 0){
//                inOverdraft = false;
//            }
            if(this.balance >= over){
                this.balance -= over;
                inOverdraft = false;
            }

        }
        return this.balance;
    }

    /**
     * Implements abstract withdraw method.
     * Rejects withdrawal if the withdrawal would put the account bellow $0 and throws an exception.
     * Rejects withdrawal if the account is in overdraft and throws an exception.
     *
     * Returns the balance after the withdrawal.
     *
     */


    public double withdraw(double amount) throws InsufficientFundsException {
        int over = 35;
        //TODO: implement
        if(this.getBalance() -amount < 0){

            double maxPossibleWithdrawal = this.getBalance(); // need to set this to proper value
            String exceptionMSG =
                    "THE MAXIMUM AMOUNT THE USER: (" + id + ") " +
                            "CAN WITHDRAW FROM THEIR CHECKING ACCOUNT IS " +
                            "($" + maxPossibleWithdrawal + ")";

            throw new InsufficientFundsException(exceptionMSG);
        }

        if(this.inOverdraft == true){

            double requiredDeposit= this.getBalance() - over; // need to set this to proper value
            String overdraftRejection = "USER: (" + id + ") " +
                    "CANNOT MAKE A WITHDRAWAL FROM THEIR CHECKING ACCOUNT " +
                    "UNTIL THEY COVER THEIR OVERDRAFT FEE " +
                    "WITH A DEPOSIT OF AT LEAST ($" + requiredDeposit + ")";
            throw new InsufficientFundsException(overdraftRejection);
        }

        this.balance = this.getBalance()- amount;
        return this.balance;
    }


    /**
     * Method pulls money from the users account when someone cashes their check and
     * returns the balance after the withdrawal
     *
     * If the account is in overdraft reject the check and inform the check writer(id) of
     * how much they need to deposit to make withdrawals.
     *
     * Otherwise this check can withdraw any amount of money. If it puts the account balance
     * below $0 place the account in overdraft.
     *
     * If the check writer has already used up his free checks,
     * there is an additional $2 fee for this withdrawal.
     *
     */
    public double withdrawUsingCheck(double amount) throws InsufficientFundsException {
        int over = 35;
        int cheque = 3;
        int chequeCost =2;
        if(this.getBalance()< 0 || inOverdraft == true) {
            double requiredDeposit = -(this.getBalance() - over);
            String overdraftRejection = "USER: (" + id + ") " +
                    "CANNOT MAKE A WITHDRAWAL FROM THEIR CHECKING ACCOUNT " +
                    "UNTIL THEY COVER THEIR OVERDRAFT FEE " +
                    "WITH A DEPOSIT OF AT LEAST ($" + requiredDeposit + ")";

            //TODO: implement
            throw new InsufficientFundsException(overdraftRejection);
        }

        else {

            if (numberOfChecksUsed < cheque) {
                if (this.getBalance() - amount < 0) {
                    this.inOverdraft = true;
                    numberOfChecksUsed += 1;
                    this.balance = this.getBalance() - amount;//- 35;
                    return this.balance;

                } else {
                    this.numberOfChecksUsed += 1;
                    this.balance = this.getBalance() - amount;
                    return this.balance;
                }


            }

            if (numberOfChecksUsed >= cheque) {
                if (this.getBalance() - amount < 0) {
                    this.inOverdraft = true;
                    this.numberOfChecksUsed += 1;
                    this.balance = this.getBalance() - amount - over - chequeCost;
                    return this.balance;

                } else {
                    this.numberOfChecksUsed += 1;
                    this.balance = this.getBalance() - amount - chequeCost;
                    return this.balance;
                }

            }
        }

        this.balance = this.getBalance() - amount;
      return this.balance;
    }
}
