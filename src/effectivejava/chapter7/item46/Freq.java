package effectivejava.chapter7.item46;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.*;

/**
 * 第46条：优先选择Stream中无副作用的函数
 */
// Frequency table examples showing improper and proper use of stream (Page 210-11)
// 频率表示例显示了流的不正确使用
public class Freq {
    public static void main(String[] args) throws FileNotFoundException {
        //File file = new File(args[0]);
        File file = new File("F:\\code\\effective-java-3e-source-code\\src\\effectivejava\\Examples.txt");

//        // Uses the streams API but not the paradigm--Don't do this!
//        // 使用流API，但不是范式 - 请勿这样做！
//        Map<String, Long> freq = new HashMap<>();
//        try (Stream<String> words = new Scanner(file).tokens()) {
//            words.forEach(word -> {
//                freq.merge(word.toLowerCase(), 1L, Long::sum); // 这段代码在一个终结操作forEach中完成所有工作，用了一个改变外部状态的lambda
//            });
//        }

        // Proper use of streams to initialize a frequency table
        // 正确使用流来初始化频率表
        Map<String, Long> freq;
        try (Stream<String> words = new Scanner(file).tokens()) { // 使用 Scanner 的 stream 方法来获得 stream。这个方法是在 Java 9 中添加的。
            freq = words.collect(groupingBy(String::toLowerCase, counting()));
        }

        System.out.println(freq);

        // forEach 操作应仅用于报告流计算的结果，而不是用于执行计算。
        // 有时，将 forEach 用于其他目的是有意义的，例如将流计算的结果添加到预先存在的集合中。
        // Pipeline to get a top-ten list of words from a frequency table 从频率表中获取单词的前十名列表的管道
        List<String> topTen = freq.keySet().stream()      //
                .sorted(comparing(freq::get).reversed())
                .limit(10)
                .collect(toList());

        System.out.println(topTen);

    }
}
/* Output:
{monstrous=1, stood=1, none=1, hope=1, trees=1, wasn't=1, setting=1, america=1, neighbors.=1, would=2, pick=1, major,=1, moving=1, they=1, you=2, "the=1, events=1, close,=1, sequoia=1, began=1, in=4, pleasure=1, nothing=1, evening.=1, traveling=1, it=2, an=1, canyon=1, as=1, at=3, stop=1, forest,=1, even=1, fabled=1, force=1, beds=1, dog=1, "looking=2, be=1, air=1, sun=1, lives.=1, our=1, out=1, memories,=1, across=1, found=1, too=3, get=1, have=2, national=1, after=1, so=1, close=1, a=6, childhood=1, seemed=1, within=1, more=1, knew=2, one=2, course,=1, i=11, trips.=1, filled=2, unfortunately,=1, many=1, small,=1, the=18, wonder:=1, exciting=1, days=2, to=6, faces=1, fun=1, but=1, door=1, perfect=1, caesar's=1, happiest=1, process.=1, had=4, specifications=1, him=1, that=6, edge=1, earlier=1, leaves=1, picked=1, puppy…=1, find=1, than=1, established.=1, houses=1, me=1, from=2, up=1, day=1, those=1, puppy.=1, palace=1, impersonal,=1, minutes=1, difficult=1, "look"=1, finding=1, my=2, look=1, gone=1, both=1, "warm=1, were=1, puppy."=1, supposed=1, lake=1, daughter=1, little=1, no=1, week=1, rather=1, idea=1, for=2, jumped=1, back=1, house=1, feelings."=1, we=3, can=2, pound=2, joy=1, walking=1, and=5, us?"=1, of=11, just=3, fuzzy=1, on=3, squiggling=1, tahoe."=1, match=1, saw=2, was=3, grand=1, really=1, with=4, there=2, visited=1, ones=1, until=1}
[the, i, of, a, to, that, and, in, had, with]
 */