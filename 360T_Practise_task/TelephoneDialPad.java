package automationtesting.Assignment;

import java.util.LinkedList;
import java.util.List;

public class TelephoneDialPad {
    public static String dialPadMap[][] = {
        {"0"}, {"1"}, {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"},
        {"J", "K", "L"}, {"M", "N", "O"}, {"P", "Q", "R", "S"},
        {"T", "U", "V"}, {"W", "X", "Y", "Z"}
    };

  protected  static void calculateAlphabetCombinations(List<String> combinationList, String prefixVar, String remainingVar) {
        int digitVar = Integer.parseInt(remainingVar.substring(0, 1));
        if (remainingVar.length() == 1) {
            for (int i = 0; i < dialPadMap[digitVar].length; i++) {
                combinationList.add(prefixVar + dialPadMap[digitVar][i]);
            }
        } else {
            for (int i = 0; i < dialPadMap[digitVar].length; i++) {
                calculateAlphabetCombinations(combinationList,
                        prefixVar + dialPadMap[digitVar][i], remainingVar.substring(1));
            }
        }
    }
		
	public static LinkedList<String> retrieveCombinations(String digitInput) {
        LinkedList<String> combinationList = new LinkedList<>();
        calculateAlphabetCombinations(combinationList, "", digitInput);
        return combinationList;
    }
	
public static void main(String[] args) {
		
	//		String phNum = "230";
		
        List<String> retComb = retrieveCombinations("12");
        
        for (String s : retComb) {
            System.out.println(s);
	
				
	}
    System.out.println("Length of combination:"+retComb.get(0).length());   
	System.out.println("Size of list:"+retComb.size());

}


}