package automationtesting.Assignment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestTelephoneDialPad {

// checking null input
	@Test(expectedExceptions = java.lang.NullPointerException.class)
	public void checkNullInput() {
		TelephoneDialPad.retrieveCombinations(null);
	}

// checking empty input
	@Test(expectedExceptions = java.lang.StringIndexOutOfBoundsException.class)
	public void checkEmptyString() {
		TelephoneDialPad.retrieveCombinations("");
	}

// checking blank space input
	@Test(expectedExceptions = java.lang.NumberFormatException.class)
	public void checkBlankInput() {
		TelephoneDialPad.retrieveCombinations(" ");
	}

// checking for special character input
	@Test(expectedExceptions = java.lang.NumberFormatException.class)
	public void checkSpecialCharacter() {
		TelephoneDialPad.retrieveCombinations("*123#");
	}

// checking input "0" and "1"
	@Test
	public void checkSpecialCaseZeroAndOne() {
		assertEquals(TelephoneDialPad.retrieveCombinations("0").size(), 1, "Expected output array size 1 not matched.");
		assertEquals(TelephoneDialPad.retrieveCombinations("0").get(0), "0", "Expected value 0 not matched.");
		assertEquals(TelephoneDialPad.retrieveCombinations("1").size(), 1, "Expected output array size 1 not matched.");
		assertEquals(TelephoneDialPad.retrieveCombinations("1").get(0), "1", "Expected value 1 not matched.");
	}

// checking for n digit input output should contain n digit output
	@Test(dataProvider = "inputStringLength")
	public void checkLengthOfCombination(String inputStr, int expectedLength) {
		int actualLength = TelephoneDialPad.retrieveCombinations(inputStr).get(0).length();

		assertEquals(actualLength, expectedLength, "Expected string length did not match.");
	}

	@DataProvider
	public static Object[][] inputStringLength() {
		return new Object[][] { { "1", 1 }, { "12", 2 }, { "123", 3 }, { "1234", 4 }, { "12345", 5 }, { "123456", 6 },
				{ "1234567", 7 }, { "12345678", 8 }, { "123456789", 9 }, { "1234567890", 10 } };
	}

	
// checking for given input sequence, output combination should not start with anything other than alphabets on first digits button. 
	@Test
	public void checkSequenceInCombinations() {
		assertFalse(TelephoneDialPad.retrieveCombinations("23").contains("DA"));
		assertFalse(TelephoneDialPad.retrieveCombinations("456").contains("OKG"));
		assertFalse(TelephoneDialPad.retrieveCombinations("456").contains("KOG"));

	}
// checking that for input string, output should be correct alphabets combinations as per input sequence digits.
	@Test(dataProvider = "combinationList")
	public void checkPossibleCombination(String inputDialNum, String expectedCombination1,
			String expectedCombination2) {

		assertTrue((TelephoneDialPad.retrieveCombinations(inputDialNum)).contains(expectedCombination1));
		assertTrue((TelephoneDialPad.retrieveCombinations(inputDialNum)).contains(expectedCombination2));
	}

	@DataProvider
	public static Object[][] combinationList() {

		return new Object[][] { { "12", "1A", "1C" }, { "23", "BE", "CF" }, { "456", "HKM", "IJO" },
				{ "7896", "QTYO", "STWM" }, { "52905", "KBX0L", "JAY0K" }, { "679842", "NRWUHC", "OSZVIB" },
				{ "9315712", "XE1KP1A", "ZF1KQ1B" } };

	}
// checking size of output combination list based on digits in input String (e.g. if input string is 27 then size of output list must be 3 * 4)
	@Test(dataProvider = "inputNumberList")
	public void checkSizeOfCombinationList(String inputNumberStr) {
		
		int actualNumberOfCombinations = TelephoneDialPad.retrieveCombinations(inputNumberStr).size();
		int expectedNumberOfCombinations = getNumberOfPossibleCombinations(inputNumberStr);
		
		assertEquals(actualNumberOfCombinations, expectedNumberOfCombinations,"Number of expected combinations did not match.");
		
	}

	@DataProvider
	public static Object[][] inputNumberList() {

		return new Object[][] { { "24" }, { "75" }, { "50" }, { "91" }, { "378" }, { "5407" }, { "954631" },
				{ "987601" } };

	}

	public static int getNumberOfPossibleCombinations(String inputNumber) {
		char res = '0';
		int numOfCombinations = 1;

		for (int i = 0; i < inputNumber.length(); i++) {
			res = (inputNumber.charAt(i));
			switch (res) {
			case '0':
			case '1':
				numOfCombinations *= 1;
				break;
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '8':
				numOfCombinations *= 3;
				break;

			case '7':
			case '9':
				numOfCombinations *= 4;
				break;
			default:
				break;

			}

		}

		return numOfCombinations;
	}

}