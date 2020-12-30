package effectivejava.chapter4.item24;
//: innerclasses/LocalInnerClass.java
// Holds a sequence of Objects.


interface Counter {
  int next();
}

public class LocalInnerClass {
  private int count = 0;
  Counter getCounter(final String name) {
    //局部内部类,不使用访问权限修饰符
    class LocalCounter implements Counter {
      public LocalCounter() {
        // Local inner class can have a constructor
        System.out.println("LocalCounter()");
      }
      public int next() {
        System.out.print(name); // Access local final
        return count++;
      }
    }
    return new LocalCounter();
  }

  public static void main(String[] args) {
    LocalInnerClass lic = new LocalInnerClass();
    Counter c1 = lic.getCounter("Local inner ");
    for(int i = 0; i < 5; i++)
      System.out.println(c1.next());
  }
} /* Output:
LocalCounter()
Counter()
Local inner 0
Local inner 1
Local inner 2
Local inner 3
Local inner 4
*///:~
