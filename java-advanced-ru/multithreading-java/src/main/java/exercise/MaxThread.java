package exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxThread extends Thread{
    private int max;
    private int[] array;

    public MaxThread() {
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public void run() {
        List<Integer> list = Arrays.stream(array).boxed().toList();

        this.max = list.stream().sorted(Comparator.reverseOrder()).limit(1L).toList().get(0);
    }
}