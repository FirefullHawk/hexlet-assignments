package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void enlargeArrayImageBlankArray() {
        String[][] expected = new String[0][0];
        String[][] actual = App.enlargeArrayImage(expected);
        assertThat(actual).isDeepEqualTo(expected);
    }

    @Test
    void enlargeArrayImageOneSymbolArray() {
        String[][] arraysToFill = {{"*"}};
        String[][] actual = App.enlargeArrayImage(arraysToFill);
        String[][] expected = {{"*", "*"}, {"*", "*"}};
        assertThat(actual).isDeepEqualTo(expected);
    }

    @Test
    void enlargeArrayImage1() {
        String[][] arraysToFill = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] actual = App.enlargeArrayImage(arraysToFill);
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };
        assertThat(actual).isDeepEqualTo(expected);
    }

    @Test
    void enlargeArrayImage2() {
        String[][] arraysToFill = {
                {"*", "*", "*"},
                {"*", " ", "*"},
                {"*", "*", "*"},
        };
        String[][] actual = App.enlargeArrayImage(arraysToFill);
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*"},
        };
        assertThat(actual).isDeepEqualTo(expected);
    }
}
// END
