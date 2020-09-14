package chapters.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkWithMap {
    private static void oneMap(List<Integer> data) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer input : data) {
            if (!map.containsKey(input)) {
                map.put(input, 1);
            } else {
                map.put(input, map.get(input) + 1);
            }
        }
        List<Integer> duplicates = map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Stream.of(duplicates).forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(11, 12, 13, 14, 15, 15, 16, 15, 15);
        WorkWithMap.oneMap(array);
    }
}
