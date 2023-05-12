package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {

    private final String filePath;

    FileKV(String filePath, Map<String, String> storage) {
        Map<String, String> startMap = new HashMap<>(storage);
        String result = Utils.serialize(startMap);
        Utils.writeFile(filePath, result);
        this.filePath = filePath;
    }

    @Override
    public void set(String key, String value) {
        String fileData = Utils.readFile(this.filePath);
        Map<String, String> unserializedMap = new HashMap<>(Utils.unserialize(fileData));
        unserializedMap.put(key, value);
        String result = Utils.serialize(unserializedMap);
        Utils.writeFile(filePath, result);
    }

    @Override
    public void unset(String key) {
        String fileData = Utils.readFile(this.filePath);
        Map<String, String> unserializedMap = new HashMap<>(Utils.unserialize(fileData));
        unserializedMap.remove(key);
        String result = Utils.serialize(unserializedMap);
        Utils.writeFile(filePath, result);
    }

    @Override
    public String get(String key, String defaultValue) {
        String fileData = Utils.readFile(this.filePath);
        Map<String, String> unserializedMap = new HashMap<>(Utils.unserialize(fileData));
        return unserializedMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(Utils.unserialize(Utils.readFile(filePath)));
    }
}
// END
