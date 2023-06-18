package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            try {
                showUsers(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Object> getUsers() throws IOException {
        // BEGIN
        ObjectMapper mapper = new ObjectMapper();

        String userString;
        try {
            userString = getDataFromFile(getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mapper.readValue(userString, new TypeReference<>() {});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        // BEGIN
        StringBuilder body = new StringBuilder();
            body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                        <table>
                    <tr><td>ID</td><td>fullName</td></tr>
                """);

            getUsers()
                    .forEach(user -> {
                        String[] oneUser = user.toString().split(", ");
                        String name = oneUser[0].split("=")[1];
                        String secondName = oneUser[1].split("=")[1];
                        int id = Integer.parseInt(oneUser[2].split("=")[1]);
                        body.append("<tr><td>").append(id).append("</td><td>").append("<a href=\"/users/")
                                .append(id).append("\">").append(name).append(" ").append(secondName)
                                .append("</a></td></tr>");
                        }
                    );

            body.append("""
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {
        // BEGIN
        response.setContentType("text/html;charset=UTF-8");


        boolean userExist = getUsers()
                .stream()
                .map(user -> user.toString().split(", ")[2].split("=")[1])
                .anyMatch(idUser -> Objects.equals(idUser, id));

        if (!userExist) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        StringBuilder body = new StringBuilder();
            body.append("""
                    <!DOCTYPE html>
                    <html lang=\"ru\">
                        <head>
                            <meta charset=\"UTF-8\">
                            <title>Example application | Users</title>
                            <link rel=\"stylesheet\" href=\"mysite.css\">
                        </head>
                        <body>
                            <table>
                        <tr><td>ID</td><td>firstName</td><td>secondName</td><td>email</td></tr>
                    """);

            getUsers()
                    .stream()
                    .filter(user -> {
                        String idUser = user.toString().split(", ")[2].split("=")[1];
                        return Objects.equals(id, idUser);
                    })
                    .forEach(user -> {
                                String[] oneUser = user.toString().split(", ");
                                String name = oneUser[0].split("=")[1];
                                String secondName = oneUser[1].split("=")[1];
                                String email = oneUser[3].split("=")[1].replace("}", "");
                                body.append("<tr><td>").append(id).append("</td><td>").append(name).append("</td><td>")
                                        .append(secondName).append("</td><td>").append(email).append("</td></tr>");
                            }
                    );

            body.append("""
                            </table>
                        </body>
                    </html>
                    """);
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private static Path getAbsolutePath() {
        return Paths.get("./src/main/resources/users.json").toAbsolutePath().normalize();
    }

    private static String getDataFromFile(Path absoluteFilePath) throws Exception {
        return Files.readString(absoluteFilePath).trim();
    }
}
