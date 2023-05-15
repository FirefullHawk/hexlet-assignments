package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String tag;

    private Map<String, String> attribute;

    Tag(String tag, Map<String, String> attribute) {
        this.tag = tag;
        this.attribute = attribute;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }

    public String toString() {
        return getAttribute().keySet()
                .stream()
                .map(key -> " " + key + "=" + "\"" + getAttribute().get(key) + "\"")
                .collect(Collectors.joining());
    }
}
// END
