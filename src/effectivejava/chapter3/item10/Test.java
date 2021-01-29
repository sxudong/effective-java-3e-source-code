package effectivejava.chapter3.item10;


/**
 * 测试包级私有类是否可以equals
 *
 * 试验证明：包级私有类，出了包不可以new对象，equals就更别谈了。
 */
public class Test {
    public static void main(String[] args) {
//        Point2 point1 = new Point2(1, 2);
//        Point2 point2 = new Point2(1, 2);
//        System.out.println(point1.equals(point2));
        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1.equals(o2));
    }
}
