package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    private final List<Integer> list;

    // BEGIN
    public SafetyList() {
        this.list = new ArrayList<>();
    }

    public SafetyList(List<Integer> list) {
        this.list = new ArrayList<>();
    }

    public synchronized void add(Integer e) {
        this.list.add(e);
    }

    public Integer get(Integer i) {
        return this.list.get(i);
    }

    public Integer getSize() {
        return this.list.size();
    }
    // END
}
