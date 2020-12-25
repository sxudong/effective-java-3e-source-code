package effectivejava.chapter2.item8;
//: initialization/TerminationCondition.java
// Using finalize() to detect an object that
// hasn't been properly cleaned up.

/**
 * 第8条：避免使用终结方法和清除方法
 */
// 《Java编程思想4》p87 第5章初始化和清理 5.5.1 finalize()的用途何在
class Book {
    boolean checkedOut = false;

    Book(boolean checkOut) {
        checkedOut = checkOut;
    }

    void checkIn() {
        checkedOut = false;
    }

    @Override
    protected void finalize() {
        if (checkedOut)
            System.out.println("Error: checked out");
        // 通常，您还将执行此操作：
        // super.finalize(); // 调用基类版本
    }
}

public class TerminationCondition {
    public static void main(String[] args) {
        Book novel = new Book(true);
        // 正确清理：
        novel.checkIn();
        // 删除引用，忘记清理：
        new Book(true);
        // 强制垃圾收集和完成，调用了finalize()方法
        System.gc();
    }
} /* Output:
Error: checked out
*///:~
