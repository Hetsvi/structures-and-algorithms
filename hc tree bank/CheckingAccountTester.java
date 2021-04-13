/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class has tests to check the checking account method.
 * @author Hetsvi
 * @since 20May
 */
public class CheckingAccountTester {

    private CheckingAccount account;
    private CheckingAccount account1;
    private CheckingAccount account2;
    private CheckingAccount account3;
    private CheckingAccount account4;
    private CheckingAccount egAccount;

    /**
     * This runs before every tests and creates new accounts
     * @throws InsufficientFundsException
     */
    @Before
    public void setUp() throws InsufficientFundsException {

            account = new CheckingAccount("abc", 200);
            account2 = new CheckingAccount("abcd", 60);
            account3 = new CheckingAccount("abc1", 5000);
            account4 = new CheckingAccount("abc1", 0.01);
            egAccount = new CheckingAccount("egTest", 100);

    }

    /**
     * This tests if the exception while making an account works
     * @throws InsufficientFundsException
     */
    @Test(expected = InsufficientFundsException.class)
    public void testStartingExcpetion() throws InsufficientFundsException{

        account1 = new CheckingAccount("100", 0);


    }

    /**
     * This checks if the getting checks and resetting works
     * @throws InsufficientFundsException
     */
    @Test
    public void testResetChecksUsed() throws InsufficientFundsException{
        account.withdrawUsingCheck(10);
        account.withdrawUsingCheck(10);
        account.withdrawUsingCheck(10);
        account.withdrawUsingCheck(10);
        assertEquals(4, account.getChecksUsed());
        account.resetChecksUsed();
        assertEquals(0, account.getChecksUsed());
    }

    /**
     * This checks if the deposit works
     */
    @Test
    public void testDeposit(){
        account.deposit(100);
        assertEquals(300, account.getBalance(), 0.001);
        account2.deposit(10);
        assertEquals(70, account2.getBalance(), 0.001);
        account3.deposit(500);
        assertEquals(5500, account3.getBalance(), 0.001);

    }

    /**
     * Checks if withdraw works
     * @throws InsufficientFundsException
     */
    @Test
    public void testWithdraw() throws InsufficientFundsException{
            account2.withdraw(10);
            assertEquals(50, account2.getBalance(), 0.001);

    }

    /**
     * Checks if withdraw using checks work
     * @throws InsufficientFundsException
     */
    @Test
    public void testWithdrawUsingCheck() throws InsufficientFundsException{

            account.withdrawUsingCheck(300);
            account2.withdrawUsingCheck(10);
            account4.withdrawUsingCheck(1);
            assertEquals(-100, account.getBalance(), 0.001);
            assertEquals(50, account2.getBalance(), 0.001);
            assertEquals(-0.99, account4.getBalance(), 0.001);


    }

    /**
     * checks the exception works for withdraw
     * @throws InsufficientFundsException
     */
    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawException() throws InsufficientFundsException{
        account.withdraw(300);
        account4.withdraw(1);
    }

    /**
     * Checks if things work together
     * @throws InsufficientFundsException
     */
    @Test
    public void testExample() throws InsufficientFundsException{
        egAccount.withdraw(50);
        assertEquals(50, egAccount.getBalance(), 0.001);
        assertEquals(0, egAccount.getChecksUsed(),0.001);
      //  egAccount.withdraw(100);
      //  assertEquals(50, egAccount.getBalance(), 0.001);
   //     assertEquals(0, egAccount.getChecksUsed(),0.001);
        egAccount.withdrawUsingCheck(100);
        assertEquals(-50, egAccount.getBalance(), 0.001);
        assertEquals(1, egAccount.getChecksUsed(),0.001);
//        egAccount.withdrawUsingCheck(10);
  //      assertEquals(-50, egAccount.getBalance(), 0.001);
     //   assertEquals(1, egAccount.getChecksUsed(),0.001);
        egAccount.deposit(60);
        assertEquals(10, egAccount.getBalance(), 0.001);
        assertEquals(1, egAccount.getChecksUsed(),0.001);
//        egAccount.withdraw(10);
  //      assertEquals(10, egAccount.getBalance(), 0.001);
    //    assertEquals(1, egAccount.getChecksUsed(),0.001);
        egAccount.deposit(40);
        assertEquals(15, egAccount.getBalance(), 0.001);
        assertEquals(1, egAccount.getChecksUsed(),0.001);
        egAccount.withdraw(10);
        assertEquals(5, egAccount.getBalance(), 0.001);
        assertEquals(1, egAccount.getChecksUsed(),0.001);


    }

    /**
     * checks exception for withdaw UsingCheck
     * @throws InsufficientFundsException
     */
    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawCheckingException() throws InsufficientFundsException{
        account1 = new CheckingAccount("100", 0);
        account1.withdrawUsingCheck(50);

    }


}