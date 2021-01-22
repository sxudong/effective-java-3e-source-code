package effectivejava.chapter2.item3;

public class User2 {

	/**
	 * 事实证明，并不能够被同一个包中的其他Java文件调用，
	 * 最后发现原因是因为，
	 *
	 * main()方法，在private成员变量所处的类中，当然可以被调用
	 */
	public static void main(String[] args) {
		//不能编译，报错
		//User user1 = new User(10);
		System.out.println("--------");
		//不能编译，报错
		//user1.userAge = 20;
	}
}