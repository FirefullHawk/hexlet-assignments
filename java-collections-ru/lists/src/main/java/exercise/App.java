package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static void charByString(String word, List<Character> listToFill) {
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            listToFill.add(temp);
        }
    }

    public static boolean scrabble(String symbols, String word) {
        List<Character> symbolList = new ArrayList<>();
        List<Character> symbolByWordList = new ArrayList<>();

        charByString(symbols, symbolList);
        charByString(word, symbolByWordList);

        int numberOfSymbols = 0;
        for (char checkingSymbol : symbolByWordList) {
            char lowerCaseCheck = Character.toLowerCase(checkingSymbol);
            boolean temp = symbolList.contains(lowerCaseCheck);
            if (temp) {
                int hiddenIndex = symbolList.indexOf(lowerCaseCheck);
                symbolList.set(hiddenIndex, null);
                numberOfSymbols += 1;
            }
        }
        return numberOfSymbols == word.length();
    }
}
//END
