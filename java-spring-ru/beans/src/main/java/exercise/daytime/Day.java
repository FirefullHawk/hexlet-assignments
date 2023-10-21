package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    private String message;

    @PostConstruct
    public void init() {
        this.message = "Bean is initialized!";
        System.out.println(message);
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up resources or performing final actions!");
    }
    // END
}
