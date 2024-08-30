
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import jdk.jfr.Timestamp;

/*
 * This class contains tests for the DonationManager.donate() method.
 * 
 * Implement your test methods here, using mock objects for the FundManager 
 * and UserManager as needed.
 */

public class DonationManagerTest {

	/**
	 * Test #1: FundManager is null
	 */
	@Test(expected=IllegalStateException.class)
	public void testFundManagerNull() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		new DonationManager(null, testUserManager).donate("testUser", "testFund", 30);
	}
	
	/**
	 * Test #2: UserManager is null
	 */
	@Test(expected=IllegalStateException.class)
	public void testUserManagerNull() {
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, null).donate("testUser", "testFund", 30);
	}
	
	/**
	 * Test #3: user parameter is null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testUserParameterNull() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate(null, "testFund", 30);
	}
	
	/**
	 * Test #4: user parameter is an empty string
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testUserParameterEmptyString() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("", "testFund", 30);
	}
	
	/**
	 * Test #5: fund parameter is null
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFundParameterNull() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("testUser", null, 30);
	}
	
	/**
	 * Test #6: fund parameter is an empty string
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testDonationAmountZero () {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("testUser", "", 30);
	}
	
	/**
	 * Test #7: isValidUser returns false
	 */
	@Test(expected=InvalidUserException.class)
	public void testisValidUserFalse() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return false;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 40);
	}
	
	/**
	 * Test #8: isValidFund returns false
	 */
	@Test(expected=InvalidFundException.class)
	public void testisValidFundFalse() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return false;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 40);
	}
	
	/**
	 * Test #9: getFundTarget returns a negative number
	 */
	@Test(expected=IllegalStateException.class)
	public void testgetFundTargetNegative() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 40;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return -1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 40);
	}
	
	/**
	 * Test #10: user’s balance is less than the donation argument
	 */
	@Test(expected=InsufficientBalanceException.class)
	public void testUserInsufficientBalance() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 0;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 1000;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 40);
	}
	
	/**
	 * Test #12: the fund has already reached or exceeded its target
	 */
	@Test
	public void testFundTargetReached() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 100;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 100;
			}
			@Override
			public double getFundBalance(String name) {
				return 100;
			}
		};
		double result = new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 40);
		double expected = 0;
		assertEquals(expected, result, 0.01);
	}
	
	/**
	 * Test #13: the fund hasn't reached its target. The donated amount is less than the required amount
	 */
	@Test
	public void testNormalCaseDonationLessThanRequired() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 100;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 100;
			}
			@Override
			public double getFundBalance(String name) {
				return 60;
			}
		};
		double result = new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 10);
		double expected = 10;
		assertEquals(expected, result, 0.01);
	}
	
	/**
	 * Test #14: the fund hasn't reached its target. The donated amount is more than the required amount
	 */
	@Test
	public void testNormalCaseDonationMoreThanRequired() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 100;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 100;
			}
			@Override
			public double getFundBalance(String name) {
				return 60;
			}
		};
		double result = new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 50);
		double expected = 40;
		assertEquals(expected, result, 0.01);
	}
	
	/**
	 * Test #16: the fund hasn't reached its target. The donated amount is less than 2000 
	 * but the difference between the target and fund is 4000 or more
	 */
	@Test
	public void testNormalCaseLessThan2000() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 1000;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 10000;
			}
			@Override
			public double getFundBalance(String name) {
				return 1000;
			}
		};
		double result = new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 100);
		double expected = 100;
		assertEquals(expected, result, 0.01);
	}
	
	/**
	 * Test #17: the fund hasn't reached its target. The donated amount is more than 2000 
	 * and the difference between the target and fund is 4000 or more
	 * the doubled donation amount is less than the gap
	 */
	@Test
	public void testNormalCaseMoreThan2000LessThanGap() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 10000;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 15000;
			}
			@Override
			public double getFundBalance(String name) {
				return 3000;
			}
		};
		double result = new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 4000);
		double expected = 8000;
		assertEquals(expected, result, 0.01);
	}
	
	/**
	 * Test #17: the fund hasn't reached its target. The donated amount is more than 2000 
	 * and the difference between the target and fund is 4000 or more
	 * the doubled donation amount is more than the gap
	 */
	@Test
	public void testNormalCaseMoreThan2000MoreThanGap() {
		UserManager testUserManager = new UserManager(){
			@Override
			public boolean isValidUser(String name) {
				return true;
			}
			@Override
			public double getBalance(String name) {
				return 10000;
			}
		};
		FundManager testFundManager = new FundManager(){
			@Override
			public boolean isValidFund(String name) {
				return true;
			}
			@Override
			public double getFundTarget(String name) {
				return 15000;
			}
			@Override
			public double getFundBalance(String name) {
				return 3000;
			}
		};
		double result = new DonationManager(testFundManager, testUserManager).donate("testUser", "testFund", 10000);
		double expected = 12000;
		assertEquals(expected, result, 0.01);
	}
}
