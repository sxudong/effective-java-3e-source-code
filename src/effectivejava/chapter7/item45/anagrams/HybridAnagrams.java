package effectivejava.chapter7.item45.anagrams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * 第45条：坚持使用标准的函数接口
 */
// Tasteful use of streams enhances clarity and conciseness (Page 205)
// 雅致地使用流提高清晰度和简洁性
public class HybridAnagrams {
    public static void main(String[] args) throws IOException {
//        Path dictionary = Paths.get(args[0]);
//        int minGroupSize = Integer.parseInt(args[1]);
        Path dictionary = Paths.get("F:\\code\\effective-java-3e-source-code\\src\\effectivejava\\Examples.txt");
        int minGroupSize = Integer.parseInt("3");

        try (Stream<String> words = Files.lines(dictionary)) {
            // 所有单词的字母按顺序排列做key,值是单词
            words.map(row -> row.split(" "))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(group -> System.out.println(group.size() + ": " + group));

//            words.collect(groupingBy(word -> alphabetize(word)))
//                    .values().stream()
//                    .filter(group -> group.size() >= minGroupSize)
//                    .forEach(group -> System.out.println(group.size() + ": " + group));
        }
    }

    // 使用helper辅助方法，提高代码可读性
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
/* Output:
{=[, ],                                                                               """"',,,...AAACCFFGIIILLNPSTaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbccccccccdddddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeefffffffffffgggggghhhhhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiiijjkkkklllllllllllmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnnoooooooooooooooooooooooooooppppqrrrrrrrrrrrrrrrrrrsssssssssssssssssstttttttttttttttttttttttttttuuuuuuuvvvvvvvwwwyyyzz=["Looking back on a childhood filled with events and memories, I find it rather difficult to pick one that leaves me with the fabled "warm and fuzzy feelings." As the daughter of an Air Force major, I had the pleasure of traveling across America in many moving trips. I have visited the monstrous trees of the Sequoia National Forest, stood on the edge of the Grand Canyon and have jumped on the beds at Caesar's Palace in Lake Tahoe."],                                                             "",,,,...:?ALTUaaaaaaaaaaaaaaaaaabbbbccccccccddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeffffffffffgggggghhhhhhhhhhhhhhhiiiiiiiiiiiiklllllllllmmmmnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooopppppprrrrrrrrrrrrrsssssssssssssssssssssssttttttttttttttttttttttttttuuuuuuuvwwwwwwwwxyyyy=["Looking for houses was supposed to be a fun and exciting process. Unfortunately, none of the ones that we saw seemed to match the specifications that we had established. They were too small, too impersonal, too close to the neighbors. After days of finding nothing even close, we began to wonder: was there really a perfect house out there for us?"],                                                                                                      """"',....IIIIIIIIOTaaaaaaaaaaaaaaaaaaaaaaaabbcccccdddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeffffffffffggggggggghhhhhhhhhhhhhhhhhhhhhhiiiiiiiiiiiiiiiiiiiiiijjjjkkkkkkklllllllllllllmmmmmmmnnnnnnnnnnnnnnnnnnnnnnnnooooooooooooooooooooooooooooooooopppppppppppppppppqrrrrrrrrsssssssssssssssssstttttttttttttttttttttttttttttttttttttuuuuuuuuuuuuuuuuuuuuuvvwwwwwwwwwwwwyyyyyyyyyy…=["The day I picked my dog up from the pound was one of the happiest days of both of our lives. I had gone to the pound just a week earlier with the idea that I would just "look" at a puppy. Of course, you can no more just look at those squiggling little faces so filled with hope and joy than you can stop the sun from setting in the evening. I knew within minutes of walking in the door that I would get a puppy… but it wasn't until I saw him that I knew I had found my puppy."]}

2: [, ]
1: ["Looking back on a childhood filled with events and memories, I find it rather difficult to pick one that leaves me with the fabled "warm and fuzzy feelings." As the daughter of an Air Force major, I had the pleasure of traveling across America in many moving trips. I have visited the monstrous trees of the Sequoia National Forest, stood on the edge of the Grand Canyon and have jumped on the beds at Caesar's Palace in Lake Tahoe."]
1: ["Looking for houses was supposed to be a fun and exciting process. Unfortunately, none of the ones that we saw seemed to match the specifications that we had established. They were too small, too impersonal, too close to the neighbors. After days of finding nothing even close, we began to wonder: was there really a perfect house out there for us?"]
1: ["The day I picked my dog up from the pound was one of the happiest days of both of our lives. I had gone to the pound just a week earlier with the idea that I would just "look" at a puppy. Of course, you can no more just look at those squiggling little faces so filled with hope and joy than you can stop the sun from setting in the evening. I knew within minutes of walking in the door that I would get a puppy… but it wasn't until I saw him that I knew I had found my puppy."]
Disconnected from the target VM, address: '127.0.0.1:8670', transport: 'socket'
 */