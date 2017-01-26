package lib.regularExpression;

//import java.util.List;

import lib.regularExpression.util.Iterator;

/**
 * @author neel patel
 *
 */
public class Main {
	public static void main(String[] args) {
		Generex generex = new Generex("[0-3]([a-c]|[e-g]{1,2})");

		// generate the second String in lexicographical order that match the
		// given Regex.
		String secondString = generex.getMatchedString(2);
		System.out.println(secondString);// it print '0b'

		// Generate all String that matches the given Regex.
		//List<String> matchedStrs = generex.getAllMatchedStrings();

		// Using Generex iterator
		Iterator iterator = generex.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
		// it prints:
		// 0a 0b 0c 0e 0ee 0ef 0eg 0f 0fe 0ff 0fg 0g 0ge 0gf 0gg
		// 1a 1b 1c 1e 1ee 1ef 1eg 1f 1fe 1ff 1fg 1g 1ge 1gf 1gg
		// 2a 2b 2c 2e 2ee 2ef 2eg 2f 2fe 2ff 2fg 2g 2ge 2gf 2gg
		// 3a 3b 3c 3e 3ee 3ef 3eg 3f 3fe 3ff 3fg 3g 3ge 3gf 3gg

		// Generate random String
		String randomStr = generex.random();
		System.out.println(randomStr);// a random value from the previous String
										// list

	}
}
