package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        var words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();

        if (words.length == 1) {
            return wordCount;
        }

        for (String word: words) {
            int newValue = wordCount.getOrDefault(word, 0) + 1;
            wordCount.put(word, newValue);
        }
        return wordCount;
    }

    public static String toString(Map<String, Integer> dictionary) {
        if (dictionary.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{");

        for (Map.Entry<String, Integer> word: dictionary.entrySet()) {
            String line = "\n" + "  " + word.getKey() + ":" + " " + word.getValue();
            result.append(line);
        }
        return result + "\n}";
    }

}
//END
