package effectivejava.chapter3.item11;

/**
 * 只覆盖equals,不覆盖hashcode
 * https://xulinjie.blog.csdn.net/article/details/80440112
 */
public class User {
    private int age;

    public User(int age) {
        super();
        this.age = age;
    }

    /**
     * ( non-Javadoc )
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return user.age == age;
    }

    /**
    * 测试equals相同的对象,它们的hashCode的值
    * 大结果:若没有覆盖hashCode,两个对象hashCode没有任何关联
    */
    public static void main(String[] args) {
        User user1 = new User(11);
        User user2 = new User(11);

        System.out.println("equals : " + user1.equals(user2));
        System.out.println("user1_hashcode : " + user1.hashCode());
        System.out.println("user2_hashcode : " + user2.hashCode());
    }
}
/* Output:
equals : true
user1_hashcode : 1534030866
user2_hashcode : 664223387
 */