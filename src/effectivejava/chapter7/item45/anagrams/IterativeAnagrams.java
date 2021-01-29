package effectivejava.chapter7.item45.anagrams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * 第45条：坚持使用标准的函数接口
 */
// Prints all large anagram groups in a dictionary iteratively (Page 204)
// 以迭代方式打印字典中所有大型相同字母异序词组
public class IterativeAnagrams {
    public static void main(String[] args) throws IOException {
//        File dictionary = new File(args[0]);
//        int minGroupSize = Integer.parseInt(args[1]);
        Path dictionary = Paths.get("F:\\code\\effective-java-3e-source-code\\src\\effectivejava\\Examples.txt");
        int minGroupSize = Integer.parseInt("1");

        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word),
                        (unused) -> new TreeSet<>()).add(word);
            }
        }

        for (Set<String> group : groups.values())
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + ": " + group);
    }

    // 使用辅助方法，提高代码可读性
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
/* Output:
2: [saw, was]
2: [no, on]
 */