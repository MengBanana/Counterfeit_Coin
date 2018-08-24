/**
 * The Counterfeit Coin problem is a reduce and conquer problem. Given (N-1) 
 * genuine coins and 1 counterfeit coin that looks the same but weights less, 
 * determine how to detect the counterfeit coin using only a balance scale to 
 * compare equal numbers of coins in the smallest number of weighings.
 * 
 * If the number is even, the two halves are compared and the lighter half 
 * contains the counterfeit coin. If odd, one of the coins is set aside and 
 * the even number is divided and compared. If the two halves are equal, the 
 * coin set aside is counterfeit. Otherwise the counterfeit coin is in the 
 * lighter half. If only two coins remain, the lighter one is counterfeit.
 * 
 * The problem size always decreases by a factor of 2, so the algorithm 
 * requires only O(log2 n) comparisons.
 * 
 * The problem is to implement the function findCounterfeit() that takes
 * a String with (N-1) '1's and 1 '0' as the counterfeit, and returns the 
 * index of the counterfeit, using only string comparisons to simulate a 
 * balance scale, and string length and substring functions to divide the 
 * coins into groups. String comparison works because '0' is lexically less 
 * than '1'. Substrings are efficient because a substring does not copy 
 * characters: it just points to the original string.
 * 
 * You may implement the solution iteratively or recursively. If recursively, 
 * you will want to implement a supporting function that takes the original 
 * string plus the first and last indexes for the recurrence.
 */


import java.util.Arrays;
import java.util.Random;

public class Counterfeit_Coin {
	static final char REGULAR_COIN = '1';
	static final char COUNTERFEIT_COIN = '0';
	
	/**
	 * Crate collection of (n-1) regular coins plus one light 
	 * counterfeit at the specified position in the string.
	 * 
	 * @param n number of coins
	 * @param counterfeit the index of the counterfeit coin
	 * @return collection with one light counterfeit coin.
	 */
	static String makeCoins(int n, int counterfeit) {
		char c[] = new char[n];
		Arrays.fill(c, REGULAR_COIN);
		c[counterfeit] = COUNTERFEIT_COIN; 
		return new String(c);
	}
	
	/**
	 * Crate collection of (n-1) regular coins plus one light 
	 * counterfeit randomly placed in the string.
	 * 
	 * @param n number of coins
	 * @param counterfeit the index of the counterfeit coin
	 * @return collection with one light counterfeit coin.
	 */
	static String makeCoins(int n) {
		return makeCoins(n, new Random().nextInt(n));
	}
	
	/**
	 * Given a collection of coins, returns the index of
	 * the counterfeit coin using only string comparison
	 * to represent a balance scale.
	 * 
	 * @param coins the coins with one light counterfeit
	 * @return the index of the counterfeit in the string
	 */
	static int findCounterfeit(String coins) {
		int start = 0;
		int end = coins.length()-1;
		// call helper function with start and end searching index
		// need the start end end index becuase when recursively call next level, need to 
		// tell next level from which part to search
		return findCounterfeit(coins, start, end);	
	}
	
	/**
	 * Helper function of findCounterfeit, given a collection of coins, 
	 * start searching index and end searching index, returns the index
	 * of the counterfeit coin using string comparison to represent a balance sacle
	 * 
	 * @param coins the coins with one light counterfeit
	 * @param start the start searching index of the string
	 * @param end the end searching index of the string
	 * @return the index of the couterfeit in the string
	 */
	static int findCounterfeit(String coins, int start, int end) {
		if (start == end) { // len is 1, it is the counterfeit coin
			return start;
		}
		int len = end - start + 1;
		if (len % 2 == 1) { // odd len
			end--; // ignore the last coin for now
		}
		// eg. index: 0 1 2 3 4 become 0 1 2 3
		// will alway be even length now, start 0, end 3, mid 1
		int mid = start + (end - start) / 2; // avoid overflow
		// two substring 01 substring of (start, mid+1), 23 substring of (mid+1, end+1)
		String one = coins.substring(start, mid+1);
		String two = coins.substring(mid+1, end+1);
		int dif = one.compareTo(two);
		if (dif == 0) { // result is the extra one we ignored
			return end+1;
		}
		else if (dif < 0) { // counterfeit coin is in one
			return findCounterfeit(coins, start, mid);
		}
		else { // counterfeit coin is in two
			return findCounterfeit(coins, mid+1, end);
		}	
	}
	
	/**
	 * Main function exercises findCounterfeit n repetitions for n=1..5 coins.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("Start");

		// try from 1 to 5 coins
		for (int ncoins = 1; ncoins <= 5; ncoins++) {
			System.out.printf("\nCoins: %d\n", ncoins);

			// repeat once for each counterfeit position 
			for (int nreps = 0; nreps < ncoins; nreps++) {
				// create the collection of coins and counterfeit
				String coins = makeCoins(ncoins, nreps);
				System.out.printf("\ncoins: %s\n", coins);
				
				// find the counterfeit position in the string
				int i = findCounterfeit(coins);
				System.out.printf("counterfeit at: %d\n", i);
			}
		}

		System.out.println("\nEnd");
	}
}