package exercise;

import java.util.HashSet;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Set<String> oldKeys = new HashSet<>(storage.toMap().keySet());

        oldKeys
                .forEach(key -> {
                    String tempValue = storage.get(key, "default");
                    storage.unset(key);
                    storage.set(tempValue, key);
                });
    }
}
// END
