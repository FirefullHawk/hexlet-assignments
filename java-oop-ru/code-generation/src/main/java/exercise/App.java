package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static Car extract(Path path) {
        Path fullPath = path.toAbsolutePath().normalize();
        try {
            return Car.unserialize(Files.readString(fullPath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void save(Path path, Car car) {
        Path fullPath = path.toAbsolutePath().normalize();
        try {
            Files.writeString(fullPath, Car.serialize(car), StandardOpenOption.WRITE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
// END
