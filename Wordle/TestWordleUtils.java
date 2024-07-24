package a2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestWordleUtils {

	@Test
	public void test01a_replace() {
		// method args
		String s = "A";
		int index = 0;
		char c = 'B';

		// expected answer
		String exp = "B";

		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}

	@Test
	public void test01b_replace() {
		// method args
		String s = "GUMBO";
		int index = 0;
		char c = 'J';

		// expected answer
		String exp = "JUMBO";

		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}

	@Test
	public void test01c_replace() {
		// method args
		String s = "JIMBO";
		int index = 1;
		char c = 'U';

		// expected answer
		String exp = "JUMBO";

		// run test
		assertEquals(exp, WordleUtils.replace(s, index, c));
	}

	@Test
	public void test01d_replace_throws() {
		// method args
		String s = "JIMBO";
		int index = -1;
		char c = 'U';

		try {
			WordleUtils.replace(s, index, c);
			fail("IndexOutOfBoundsException expected for index = " + index);
		} catch (IndexOutOfBoundsException x) {
			// ok, expected result
		}

	}

	@Test
	public void test02a_isGreen() {
		String guess = "GREEN";
		String ans = "COLOR";

		ArrayList<Boolean> exp = new ArrayList<>();
		for (int i = 0; i < guess.length(); i++) {
			exp.add(false);
		}

		assertEquals(exp, WordleUtils.isGreen(guess, ans));
	}

	@Test
	public void exercise1_isGreen_throws() {
		String guess = "GREEN";
		String ans = "COLORS";

		try {
			WordleUtils.isGreen(guess, ans);
			fail("IllegalArgumentException expected for strings of different lengths");
		} catch (IllegalArgumentException y) {
			// expected result
		}
	}

	@Test
	public void exercise2_isYellow() {
		// method args
		String guess = "KAYAK";
		String ans = "WHACK";

		// expected answer
		ArrayList<Boolean> exp = new ArrayList<>();
		for (int i = 0; i < guess.length(); i++) {
			if (i == 1) {
				exp.add(true);
				continue;
			}
			exp.add(false);
		}

		// run test
		assertEquals(exp, WordleUtils.isYellow(guess, ans));
	}

	@Test
	public void exercise3_getColors() {
		// method args
		String guess = "BATED";
		String ans = "BAKED";
		
		ArrayList<WordleColor> exp = new ArrayList<>();
		for (int i = 0; i < guess.length(); i++) {
			if (i == 2) {
				exp.add(WordleColor.GRAY);
				continue;
			}
			exp.add(WordleColor.GREEN);
		}
		
		assertEquals(exp, WordleUtils.getColors(guess, ans));
	}
}