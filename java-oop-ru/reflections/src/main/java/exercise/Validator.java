package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// BEGIN
class Validator {
    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();

        for (Field field: address.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(address);
                if (field.isAnnotationPresent(NotNull.class) && Objects.equals(value, null)) {
                    result.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public static Map<String, String> advancedValidate(Address address) {
        Map<String, String> result = new HashMap<>();
        String lesser = "length less than 4";
        String notNull = "can not be null";

        for (Field field: address.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(address);
                MinLength minLength = field.getAnnotation(MinLength.class);

                if (field.isAnnotationPresent(NotNull.class)
                        && Objects.equals(value, null)) {
                    result.put(field.getName(), notNull);
                } else if (field.isAnnotationPresent(MinLength.class)
                        && !Objects.equals(value, null) && value.toString().length() < minLength.minLength()) {
                    result.put(field.getName(), lesser);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
// END
