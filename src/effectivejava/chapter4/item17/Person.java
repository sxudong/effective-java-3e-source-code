package effectivejava.chapter4.item17;

import java.math.BigInteger;

/**
 * 第17条：使可变性最小化
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name;}
    public void setName(String name) {this.name = name; }
    public int getAge() {return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Person p = new Person("Bob", 30);
        System.out.println(p);
        // 改变实例的状态
        p.setAge(40);
        p.setName("Robert");
        System.out.println(p);

        // Java 内置的不可变类String
        String s = "hello";
        s.replace("l", "x");
        System.out.println(s); // hello
        // 返回值修改了
        String replace = s.replace("l", "x");
        System.out.println(replace); // hexxo

        // BigInteger（JDK不可变类）
        BigInteger fiveThousand = BigInteger.valueOf(5000);
        BigInteger fourThousand = BigInteger.valueOf(4000);
        BigInteger result = fiveThousand.add(fourThousand);
        System.out.println(fiveThousand); // 5000 没有改变
        System.out.println(result);       // 9000 返回的新值
    }
}
/* 输出的String类没有改变，证明String是不可变类
Person{name='Bob', age=30}
Person{name='Robert', age=40}
hello
hexxo
5000
9000
 */