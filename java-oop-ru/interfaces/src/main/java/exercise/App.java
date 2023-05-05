package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {

        List<String> result = homes
                .stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .collect(Collectors.toList());
        if (count > result.size()) {
            count = result.size();
        }

        return result.subList(0, count);
    }
}
// END
