package effectivejava.chapter8.item54;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        System.out.println(integers);

        Object[] objects = integers.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }

        //<T> T[] toArray(T[] var1);
        integers.removeAll(Arrays.asList(1,2,3));
        Integer[] integers1 = integers.toArray(new Integer[0]);
        System.out.println(integers1.length);
    }
}
