package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companylist = getCompanies();
        String companyListAsString = String.join("\n", companylist);
        String notFound = "Companies not found";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchStatus = request.getParameter("search");

        if (Objects.equals(searchStatus, null)) {
            out.println(companyListAsString);
            out.close();
        }

        String searchResult = findCompany(companylist, searchStatus).isEmpty()? notFound
                : findCompany(companylist, searchStatus);

        String result = searchStatus.isEmpty()? companyListAsString : searchResult;

        out.println(result);
        out.close();
        // END
    }

    private String findCompany(List<String> companyList, String substring) {
        return companyList
                .stream()
                .filter(companyName -> companyName.contains(substring))
                .collect(Collectors.joining("\n"));
    }
}
