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
// 反复打印字典中的所有较大的七字组
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
1: [than]
1: [lives.]
1: [Force]
1: [none]
1: [up]
1: [major,]
1: [find]
1: [neighbors.]
1: [specifications]
1: [close]
1: [trees]
1: [wonder:]
1: [stood]
1: [get]
1: [faces]
1: [setting]
1: [have]
1: [week]
1: [seemed]
1: [squiggling]
1: [trips.]
1: [in]
1: [match]
1: [idea]
1: [leaves]
1: [Air]
1: [it]
1: [monstrous]
1: [an]
1: [houses]
1: [the]
1: [out]
1: [look]
1: [at]
1: [established.]
1: [wasn't]
1: [stop]
1: [puppy."]
1: [be]
1: [They]
1: [even]
1: [I]
1: [picked]
1: [those]
1: [dog]
1: [house]
1: [sun]
1: [you]
1: [As]
1: [Tahoe."]
1: [supposed]
1: [course,]
1: ["look"]
1: [nothing]
1: [pound]
1: [beds]
1: [finding]
1: [a]
1: [ones]
1: [but]
1: [back]
1: [pick]
1: [perfect]
1: [us?"]
1: [America]
1: [Caesar's]
1: [with]
1: [visited]
1: [would]
1: [puppy.]
1: [door]
2: [saw, was]
1: [both]
1: ["The]
1: [evening.]
1: [exciting]
1: [across]
1: [pleasure]
1: [him]
1: [can]
1: [walking]
1: [moving]
1: [too]
1: [until]
1: [within]
1: [childhood]
1: [memories,]
1: [happiest]
1: [fuzzy]
1: ["warm]
1: [really]
1: [found]
1: [difficult]
1: [more]
1: [had]
1: [After]
1: [me]
1: [and]
1: [fun]
1: [my]
1: ["Looking]
1: [we]
1: [earlier]
1: [fabled]
1: [there]
1: [close,]
1: [rather]
1: [day]
1: [Unfortunately,]
1: [just]
1: [events]
1: [knew]
2: [no, on]
1: [small,]
1: [Forest,]
1: [for]
1: [of]
1: [were]
1: [that]
1: [gone]
1: [edge]
1: [began]
1: [Palace]
1: [joy]
1: [traveling]
1: [impersonal,]
1: [from]
1: [many]
1: [Lake]
1: [so]
1: [days]
1: [to]
1: [daughter]
1: [one]
1: [filled]
1: [process.]
1: [Sequoia]
1: [hope]
1: [jumped]
1: [puppy…]
1: [feelings."]
1: [our]
1: [little]
1: [Of]
1: [Canyon]
1: [Grand]
1: [minutes]
1: [National]
 */