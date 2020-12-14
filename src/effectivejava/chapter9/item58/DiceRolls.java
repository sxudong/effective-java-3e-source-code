package effectivejava.chapter9.item58;
import java.util.*;

/**
 * 第58条：for-each 循环优于传统 for 循环
 */
// Same bug as NestIteration.java (but different symptom)!! - Page 213
// 与NestIteration.java相同的错误（但症状不同）！ 没有NestIteration.java嵌套迭代这个类。
public class DiceRolls {
    enum Face { ONE, TWO, THREE, FOUR, FIVE, SIX }

    public static void main(String[] args) {
        // Same bug, different symptom! 相同的错误，不同的症状！
        Collection<Face> faces = EnumSet.allOf(Face.class);

        for (Iterator<Face> i = faces.iterator(); i.hasNext(); )
            for (Iterator<Face> j = faces.iterator(); j.hasNext(); )
                System.out.println(i.next() + " " + j.next());

        System.out.println("***************************");

        for (Face f1 : faces)
            for (Face f2 : faces)
                System.out.println(f1 + " " + f2);
    }
}
/* Output:
ONE ONE
TWO TWO
THREE THREE
FOUR FOUR
FIVE FIVE
SIX SIX
***************************
ONE ONE
ONE TWO
ONE THREE
ONE FOUR
ONE FIVE
ONE SIX
TWO ONE
TWO TWO
TWO THREE
TWO FOUR
TWO FIVE
TWO SIX
THREE ONE
THREE TWO
THREE THREE
THREE FOUR
THREE FIVE
THREE SIX
FOUR ONE
FOUR TWO
FOUR THREE
FOUR FOUR
FOUR FIVE
FOUR SIX
FIVE ONE
FIVE TWO
FIVE THREE
FIVE FOUR
FIVE FIVE
FIVE SIX
SIX ONE
SIX TWO
SIX THREE
SIX FOUR
SIX FIVE
SIX SIX
 */