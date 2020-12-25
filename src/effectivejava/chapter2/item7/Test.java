package effectivejava.chapter2.item7;

import java.util.WeakHashMap;

public class Test {
    public static void main(String[] args) {
//        WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap<>();
//        BigImage bigImage = new BigImage("image id");
//        UniqueImageName imageName = new UniqueImageName( "name _of_big_image");
//        map. put(imageName, bigImage);
//        assertTrue(map.containsKey (imageName));
//        imageName = null;
//        System.gc();
//        await().atMost(10, TimeUnit.SECONDS).until(map::isEmpty);

        // 不要使用基础类型作为WeakHashMap的key
        // objectMap.put方法执行的时候 I 会被封装为Integer类型的，Integer保留了-128到127的缓存。
        // 但是对于int来说范围大很多，因此那些Key <= 127的Entry将不会进行自动回收，
        // 但是那些大于127的将会被回收，因此最后的尺寸总是会稳定在128左右。
        WeakHashMap<Object,Object> objectMap = new WeakHashMap<Object, Object>();
        for (int i = 0; i < 1080; i++) {
            objectMap.put(i, new Object());
            System.gc();
            System.out.println("Map size :" + objectMap.size());
        }

    }
}
