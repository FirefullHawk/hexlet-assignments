package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        int numOfSymbol1 = 3;
        List<Integer> testTake1 = new ArrayList<>();
        testTake1.add(1);
        testTake1.add(2);
        testTake1.add(3);
        testTake1.add(4);
        List<Integer> rightTake1 = new ArrayList<>();
        rightTake1.add(1);
        rightTake1.add(2);
        rightTake1.add(3);
        List<Integer> getTake1 = App.take(testTake1, numOfSymbol1);
        assertThat(getTake1).isEqualTo(rightTake1);
        List<Integer> testTake2 = new ArrayList<>();

        int numOfSymbol2 = 0;
        List<Integer> rightTake2 = new ArrayList<>();
        List<Integer> getTake2 = App.take(testTake2, numOfSymbol2);
        assertThat(getTake2).isEqualTo(rightTake2);

        int numOfSymbol3 = 10;
        List<Integer> testTake3 = new ArrayList<>();
        testTake3.add(1);
        testTake3.add(2);
        testTake3.add(3);
        testTake3.add(4);
        List<Integer> rightTake3 = new ArrayList<>();
        rightTake3.add(1);
        rightTake3.add(2);
        rightTake3.add(3);
        rightTake3.add(4);
        List<Integer> getTake3 = App.take(testTake3, numOfSymbol3);
        assertThat(getTake3).isEqualTo(rightTake3);
        // END
    }
}
