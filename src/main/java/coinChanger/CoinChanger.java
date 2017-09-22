package coinChanger;


import java.util.TreeMap;
import java.util.TreeSet;


public class CoinChanger {

	private TreeSet<Integer> currencySet;
	public final static String NOT_ENOUGH_CHANGE_DESCRIPTION = "There's not enough change";
	
	public CoinChanger() {
		this.currencySet = new TreeSet<Integer>();
	}

	public void addCurrencyCoinDenomination(Integer currency) {
		addToCurrencySet(currency);
	}

	private void addToCurrencySet(Integer currency) {
		currencySet.add(currency);
	}

	public TreeSet<Integer> returnCurrencySet() {
		return currencySet;
	}

	public TreeMap<Integer,Integer> getChangeOf(Integer amount) {
		Integer rest = amount;
		TreeMap<Integer, Integer> change = constructHashMapByCurrencySet();
		while (rest >= currencySet.first()) {
			Integer highestLower = currencySet.lower(rest+1);
			change.put(highestLower, change.get(highestLower)+1);
			rest -= highestLower;
		}
		if (rest!=0) notEnoughChange();
		return change;		
	}

	private TreeMap<Integer, Integer> constructHashMapByCurrencySet() {
		TreeMap<Integer, Integer> change = new TreeMap<Integer, Integer>();
		currencySet.forEach(t -> change.put(t, 0));
		return change;
	}
	
	public Throwable notEnoughChange()
	{
		throw new RuntimeException(NOT_ENOUGH_CHANGE_DESCRIPTION);
	}
	
	

	

}
