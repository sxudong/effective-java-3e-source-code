package effectivejava.chapter5.item33;// Java Program is to demonstrate the example
// of Set checkedSet(Set set, Class ele_ty) of Collections class
 
import java.util.*;

/**
 * Collections.checkedSet()示例
 */
public class CheckedSet {
    public static void main(String args[]) {
        // Create a hashset object    
        HashSet<Integer> hs = new HashSet<>();
 
        // By using ad() method is to add the given elements in hash set
        hs.add(20);
        hs.add(10);
        hs.add(30);
        hs.add(40);
        hs.add(50);
 
        // Display HashSet
        System.out.println("hashset: " + hs);
 
        // By using checkedSet() method is to 
        // represent the type safe view of the given
        // Collection hashset
 
        Set<Integer> set = Collections.checkedSet(hs, Integer.class);
 
        System.out.println();
        System.out.println("Collections.checkedSet(hs, Integer.class) :");
 
        // Display collection
        System.out.println("set : " + set);
    }
}
/* Output:
hashset: [50, 20, 40, 10, 30]

Collections.checkedSet(hs, Integer.class) :
set : [50, 20, 40, 10, 30]
 */
