package leetcode.weeks;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeMap {
    HashMap<String, List<TimeValue>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeValue(value, timestamp));
    }

    public String get(String key, int timestamp) {
        List<TimeValue> list = map.getOrDefault(key, null);
        int start = 0, end = list.size() - 1;

        if ((list.get(start).timestamp > timestamp)) {
            return "";
        }

        if (list.get(end).timestamp <= timestamp) {
            return list.get(end).val;
        }

        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid).timestamp == timestamp) {
                return list.get(mid).val;
            }
            if (list.get(mid).timestamp < timestamp) {
                start = mid + 1;
            } else end = mid - 1;

        }
        return list.get(start - 1).val;

    }
}

class TimeValue {
    String val;
    int timestamp;

    public TimeValue(String val, int time) {
        this.val = val;
        timestamp = time;
    }
}
