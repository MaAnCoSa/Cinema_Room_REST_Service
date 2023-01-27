package cinema;

// Imports the Room class.
import cinema.room.Room;

// Spring Boot classes.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main
@SpringBootApplication
public class Main {
    public static Room room = new Room();
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
