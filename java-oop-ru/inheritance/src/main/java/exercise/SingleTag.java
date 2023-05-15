package exercise;

import java.util.Map;

// BEGIN
public final class SingleTag extends Tag {
    SingleTag(String tag, Map<String, String> attribute) {
        super(tag, attribute);
    }

    @Override
    public String toString() {
        return "<" + getTag() + super.toString()
                + ">";
    }
}
// END
