package exercise;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> dataFirst, Map<String, Object> dataSecond) {
        Map<String, String> result  = new LinkedHashMap<>();

        NavigableSet<String> dataSet = new TreeSet<>();

        for (Map.Entry<String, Object> date1: dataFirst.entrySet()) {
            for (Map.Entry<String, Object> date2: dataSecond.entrySet()) {
                dataSet.add(date1.getKey());
                dataSet.add(date2.getKey());
            }
        }

        if (dataFirst.isEmpty() & !dataSecond.isEmpty()) {
            for (Map.Entry<String, Object> date2: dataSecond.entrySet()) {
                result.put(date2.getKey(), "added");
            }
        }

        if (!dataFirst.isEmpty() & dataSecond.isEmpty()) {
            for (Map.Entry<String, Object> date1: dataFirst.entrySet()) {
                result.put(date1.getKey(), "deleted");
            }
        }

        dataSet
                .forEach(x -> {
                    boolean keyFrom1 = dataFirst.containsKey(x);
                    boolean keyFrom2 = dataSecond.containsKey(x);

                    if (keyFrom1 & keyFrom2) {
                        if (dataFirst.get(x).equals(dataSecond.get(x))) {
                            result.put(x, "unchanged");
                        } else {
                            result.put(x, "changed");
                        }
                    }
                    if (!keyFrom1 & keyFrom2) {
                        result.put(x, "added");
                    }
                    if (keyFrom1 & !keyFrom2) {
                        result.put(x, "deleted");
                    }
                });

        return result;
    }
}
//END
