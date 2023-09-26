package exercise;

import java.util.Arrays;
import java.util.List;

// BEGIN
public class MinThread extends Thread{
    private int min;
    private int[] array;

    public MinThread() {
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getMin() {
        return this.min;
    }

    @Override
    public void run() {
    List<Integer> list = Arrays.stream(array).boxed().toList();

    this.min = list.stream().sorted().limit(1L).toList().get(0);
    }
}
// END
