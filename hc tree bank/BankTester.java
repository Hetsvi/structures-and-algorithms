/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import static org.junit.Assert.*;
import org.junit.*;

import java.util.HashMap;

/**
 * This class has tests to check the bank method.
 * @author Hetsvi
 * @since 20 May
 */
public class BankTester {

    Bank bank = new Bank();

    /**
     * This tests runs before every test and creates an checking and
     * saving account.
     * @author Hetsvi
     * @since 20May
     */
    @Before
    public void testSetup(){
        bank.createSavingsAccount("yasmin", 100.00);
        bank.createCheckingAccount("rose", 5);
    }

    /**
     * This tests the set savinf interest
     */
    @Test
    public void testSetSavingsInterest() {
        bank.setSavingsInterest(2);
    }

    /**
     * This tests the number of accounts
     */
    @Test
    public void testGetNumberOfAccounts() {
        assertEquals(2, bank.getNumberOfAccounts());
    }

    /**
     * This checks if the account is made
     */
    @Test
    public void testCreateCheckingAccount() {
        bank.createCheckingAccount("iris", 40);
        assertEquals(3, bank.getNumberOfAccounts());

    }

    /**
     * This tests the savings account
     */
    @Test
    public void testCreateSavingsAccount() {
        bank.createSavingsAccount("lily", 6000);
        assertEquals(3, bank.getNumberOfAccounts());
    }

    /**
     * This tests the deposit
     */
    @Test
    public void testDeposit()  {
        bank.createSavingsAccount("lily", 6000);
        assertEquals(7250, bank.deposit("lily", 1000),
                0.001);

    }

    /**
     * This tests the withdraw
     */
    @Test
    public void testWithdraw() {
        assertEquals(1.0, bank.withdraw("rose", 4),
                0.001);

    }

    /**
     * This tests if the online transfer works
     */
    @Test
    public void testOnlineTransfer() {
        assertEquals(false,bank.onlineTransfer("yasmin",
                "rose", 10));
        assertEquals(true,bank.onlineTransfer("rose",
                "yasmin", 4));
    }

    /**
     * This tests if the checkTransfer method works.
     */
    @Test
    public void testCheckTransfer() {
        bank.createCheckingAccount("iris", 40);
        assertEquals(true,bank.checkTransfer("rose",
                "iris", 5) );

    }


}
