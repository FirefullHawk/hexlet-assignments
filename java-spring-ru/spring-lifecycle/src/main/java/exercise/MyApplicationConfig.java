package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
class MyApplicationConfig{
    @Bean
    public Daytime timesOfDay() {
        int actualTime = LocalDateTime.now().getHour();

        return actualTime < 12 && actualTime >= 6 ? new Morning() :
                actualTime < 18 && actualTime >= 12 ? new Day() :
                        actualTime < 23 && actualTime >= 18 ? new Evening() : new Night();
    }
}
// END
