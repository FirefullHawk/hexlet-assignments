package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] input) {
        Map<String, Integer> result = new HashMap<>();

        MinThread thread = new MinThread();
        thread.setArray(input);

        thread.start();

        MaxThread thread1 = new MaxThread();
        thread1.setArray(input);

        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }

        result.put("min", thread.getMin());
        result.put("max", thread1.getMax());

        return result;
    }
    // END
}
