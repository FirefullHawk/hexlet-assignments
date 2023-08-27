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
    void testDomains() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> expected = List.of(
            "kohler.info",
            "johnston.biz",
            "bailey.name",
            "rohan.co",
            "sawayn.io",
            "ryan.net",
            "larson.org",
            "berge.info",
            "buckridge.io",
            "ziemann.org"
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/domains").asString();
        String content = response.getBody();
        List<String> actual = mapper.readValue(content, new TypeReference<List<String>>() { });
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

    @Test
    void testPhones() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> expected = List.of(
            "1-069-574-7539",
            "1-257-905-4627",
            "083-821-4120",
            "253.456.8230",
            "(630) 806-4043",
            "(281) 793-9078",
            "1-700-894-5635",
            "(705) 559-9326",
            "454.163.5791",
            "1-008-077-0624"
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/phones").asString();
        String content = response.getBody();
        List<String> actual = mapper.readValue(content, new TypeReference<List<String>>() { });
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }
}
