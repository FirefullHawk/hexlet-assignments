package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
class WelcomeController {
    @Autowired
    Daytime daytime;

    @Autowired
    Meal meal;

    @GetMapping("/daytime")
    String getDaytime() {
            return "It is " + daytime.getName() +  " now. Enjoy your " + meal.getMealForDaytime(daytime.getName());
    }
}
// END
