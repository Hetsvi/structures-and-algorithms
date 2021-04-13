/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * Abstract Account class defines a bank accounts id, and the amount in the account(balance)
 * A default constructor, get methods, and a toString method must be implemented here.
 *
 * Abstract methods defining withdraw and deposit operations are left for subclasses to implement
 *
 * @author Hetsvi
 * @since 20May
 */
public abstract class Account {
    protected String id; //Unique account identifier
    protected double balance; // stores the current balance


    /**
     * Even though an abstract class cannot be directly instantiated, this constructor
     *      will be used from a sub-class's constructor.
     *
     */
    public Account(String id, double balance) {
        //TODO: implement
        super();
        this.id = id;
        this.balance = balance;

    }

    /**
     * Returns the account id
     *
     */
    public String getId() {
        //TODO: implement
        return this.id;
    }

    /**
     * Returns the current balance.
     *
     */
    public double getBalance() {
        //TODO: implement
        return this.balance;
    }

    /**
     * Returns a string that contains the id and balance. For example, if the user id is
     * "marina"  and balance is "123.45", the string returned
     * is as follows (identical punctuation and spaces):
     * ID: marina, Balance: 123.45
     *
     *
     */
    public String toString() {
        //TODO: implement
        double balance = getBalance();
        return "ID: "+ getId() + ", Balance: " + Double.toString(balance);
    }

    /**
     * This method will be implemented in the subclasses to allow deposit into this account
     *
     * @param amount the amount the user wishes to put into their account
     * @return the new balance
     */
    public abstract double deposit(double amount);

    /**
     * This method will be implemented in the subclasses to allow withdrawal from this account
     *
     * @param amount the amount the user wishes to take from their account
     * @return the remaining balance
     */
    public abstract double withdraw(double amount) throws InsufficientFundsException;
}
