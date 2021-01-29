package effectivejava.chapter7.item45.anagrams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * 第45条：坚持使用标准的函数接口
 */
// Overuse of streams - don't do this! (page 205)
// 流的过度使用-不要这样做！
public class StreamAnagrams {
    public static void main(String[] args) throws IOException {
//        Path dictionary = Paths.get(args[0]);
//        int minGroupSize = Integer.parseInt(args[1]);
        Path dictionary = Paths.get("F:\\code\\effective-java-3e-source-code\\src\\effectivejava\\Examples.txt");
        int minGroupSize = Integer.parseInt("2");

        try (Stream<String> words = Files.lines(dictionary)) {
            words.map(row -> row.split(" "))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(
                    groupingBy(word -> word.chars().sorted()
                            .collect(StringBuilder::new,
                                    (sb, c) -> sb.append((char) c),
                                    StringBuilder::append).toString()))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        }
    }
}
/* Output:
3: [cba, abc, bca]
2: [was, saw]
2: [on, no]
 */