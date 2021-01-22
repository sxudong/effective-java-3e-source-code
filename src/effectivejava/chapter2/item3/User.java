package effectivejava.chapter2.item3;

class Course {
    private int courseID;

    Course(int courseID) {
        this.courseID = courseID;
    }
}

public class User {

    //字段，成员变量
    private String userName;
    private int userAge;
    //自定义构造器

    private User(String name, int age) {
        this.userName = name;
        this.userAge = age;
    }

    void show() {
        System.out.println("User's name = " + this.userName);
    }

    //main 方法
    public static void main(String[] args) {
        User user1 = new User("Daodao", 18);
        User user2 = new User("Lili",20);
        System.out.println(user2);
        user1.show();
        user2.show();

        User user3 = new User("CoCoNaiCha", 3);
        user3.show();
        System.out.println("username: " + user3.userName);
        System.out.println("age: " + user3.userAge);
        //public 类的
        //private 成员变量，可以直接在main方法调用？
        user3.userAge = 20;
        System.out.println("修改private age 变量：" + user3.userAge);

        Course course1 = new Course(20120413);
        //System.out.println(course1.courseID);

		/**
		 * 事实证明，并不能够被同一个包中的其他Java文件调用，
		 * 最后发现原因是因为，
		 *
		 * main()方法，在private成员变量所处的类中，当然可以被调用
		 */
    }
}
