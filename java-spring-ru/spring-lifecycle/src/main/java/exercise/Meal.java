package exercise;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

// BEGIN
@Component
// END
public class Meal {
    public String getMealForDaytime(String daytime) {

        return switch (daytime) {
            case "morning" -> "breakfast";
            case "day" -> "lunch";
            case "evening" -> "dinner";
            default -> "nothing :)";
        };
    }

    // Для самостоятельной работы
    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Init bean Meal");
    }
    // END
}
