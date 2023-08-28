package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;


class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testUsersWithDefaultParams() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> expected = List.of(
            Map.of("firstName", "Nery", "lastName", "Dickens", "id", "17"),
            Map.of("firstName", "Peg", "lastName", "Franecki", "id", "4"),
            Map.of("firstName", "Pearl", "lastName", "Schultz", "id", "13"),
            Map.of("firstName", "Tammera", "lastName", "Olson", "id", "12"),
            Map.of("firstName", "Alva", "lastName", "Cummings", "id", "18")
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();
        String content = response.getBody();
        List<Map<String, String>> actual = mapper.readValue(
            content, new TypeReference<List<Map<String, String>>>() { }
        );
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

    @Test
    void testUsers1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> expected = List.of(
            Map.of("firstName", "Leticia", "lastName", "Wehner", "id", "1")
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/users?page=3&per=1").asString();
        String content = response.getBody();
        List<Map<String, String>> actual = mapper.readValue(
            content, new TypeReference<List<Map<String, String>>>() { }
        );
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

    @Test
    void testUsers2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> expected = List.of(
            Map.of("firstName", "Tameka", "lastName", "Schiller", "id", "29"),
            Map.of("firstName", "Margit", "lastName", "Kertzmann", "id", "15"),
            Map.of("firstName", "Margarett", "lastName", "Beatty", "id", "30")
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/users?page=5&per=3").asString();
        String content = response.getBody();
        List<Map<String, String>> actual = mapper.readValue(
            content, new TypeReference<List<Map<String, String>>>() { }
        );
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }
}
