package coinChanger;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import stack.Stack;

public class testCoinChanger {

	private CoinChanger coinChanger;
	private CoinChanger coinChangerCharged;

	@Before
	public void setUp() throws Exception {
		coinChanger = new CoinChanger();
		coinChangerCharged = new CoinChanger();
		coinChangerCharged.addCurrencyCoinDenomination(5);
		coinChangerCharged.addCurrencyCoinDenomination(10);
		coinChangerCharged.addCurrencyCoinDenomination(25);
		coinChangerCharged.addCurrencyCoinDenomination(100);
	}

	@Test
	public void testNewCoinChangerHasItsCurrencySetEmpty() {
		assertEquals(0, coinChanger.returnCurrencySet().size());
	}
	
	@Test
	public void testInputCurrencyDefaultAmountToCoinChanger() {
		coinChanger.addCurrencyCoinDenomination(1);
		assertEquals(1, coinChanger.returnCurrencySet().size());
	}
	
	@Test
	public void testCurrencyDenominationAreNotRepeated() {
		coinChanger.addCurrencyCoinDenomination(1);
		coinChanger.addCurrencyCoinDenomination(5);
		coinChanger.addCurrencyCoinDenomination(1);
		assertEquals(2, coinChanger.returnCurrencySet().size());
	}
	
	@Test
	public void testEnteringAnAmountThatsEqualToACurrencyAlreadyInWillReturnTheSameAmount() {
		assertTrue((coinChangerCharged.getChangeOf(10)).get(10)==1);
	}
	
	@Test
	public void testItReturnsTheHighestDenominationAvailable() {
		assertTrue((coinChangerCharged.getChangeOf(90)).get(25)==3);
		assertTrue((coinChangerCharged.getChangeOf(90)).get(10)==1);
		assertTrue((coinChangerCharged.getChangeOf(90)).get(5)==1);
	}
	
	@Test
	public void testIfTheresNotEnoughChangeItRaisesAnException() {
		try {
			coinChangerCharged.getChangeOf(99);
			fail();
		} catch (Exception notEnoughChange){
			assertEquals(CoinChanger.NOT_ENOUGH_CHANGE_DESCRIPTION,notEnoughChange.getMessage());
		}	
	}
	
}
