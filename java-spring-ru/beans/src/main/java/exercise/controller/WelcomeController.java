package exercise.controller;

import exercise.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    Application getBean;

    @GetMapping
    String greetings() {
        String daytime = Application.getBean().getName();

        return String.format("It is %s now! Welcome to Spring!", daytime);
    }
}
// END
