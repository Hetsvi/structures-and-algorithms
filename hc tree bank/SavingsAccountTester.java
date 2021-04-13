/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class has tests for saving account tests.
 * @author Hetsvi
 * @since 20May
 */
public class SavingsAccountTester {

    private SavingsAccount account;
    private SavingsAccount account1;
    private SavingsAccount account2;
    private SavingsAccount account3;
    private SavingsAccount account4;

    /**
     * Runs before all tests
     * @throws InsufficientFundsException
     */
    @Before
    public void setUp() throws InsufficientFundsException {

            account = new SavingsAccount("abc", 200);
            account3 = new SavingsAccount("abc1", 5000);
            account4 = new SavingsAccount("abc1", 100);

    }

    /**
     * sees if the starting exception works
     * @throws InsufficientFundsException
     */
    @Test(expected = InsufficientFundsException.class)
    public void testStartingExcpetion() throws InsufficientFundsException{
        account1 = new SavingsAccount("100", 50);
        account2 = new SavingsAccount("abcd", 60);

    }

    /**
     * sees if deposit works
     */
    @Test
    public void testDeposit(){
        account.deposit(50);
        assertEquals(250, account.getBalance(),0.001);
        assertEquals("abc", account.getId());
        assertEquals(5250, account3.getBalance(),0.001);
        assertEquals(100, account4.getBalance(), 0.001);

    }

    /**
     * see if withdraw works
     * @throws InsufficientFundsException
     */
    @Test
    public void testWithdraw() throws InsufficientFundsException{
            account3.withdraw(4000);
            account.withdraw(10);
            assertEquals(188, account.getBalance(), 0.001);
            assertEquals(1248, account3.getBalance(), 0.001);

    }

    /**
     * tests addInterest
     */
    @Test
    public void testAddInterest(){
        account.addInterest(5);
        assertEquals(210, account.getBalance(), 0.01);


    }

    /**
     * tests exception for withdraw
     * @throws InsufficientFundsException
     */
    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawException() throws InsufficientFundsException{
            assertEquals(50, account4.withdraw(50), 0.001);
    }


}