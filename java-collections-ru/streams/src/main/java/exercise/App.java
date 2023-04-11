package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> mails = new ArrayList<>();

        long result = 0;

        for (String email: emails) {
            String[] temp = email.split("@");
            mails.add(temp[1]);
        }

        result += mails.stream()
                .filter(email -> email.equals("gmail.com") || email.equals("yandex.ru") || email.equals("hotmail.com"))
                .count();

        return result;
    }
}
// END
