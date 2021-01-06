package effectivejava.chapter6.item36;

/**
 * Java位运算的基础及使用（意义）
 * https://blog.csdn.net/hhy107107/article/details/82801780
 *
 *   flag     删除  修改 新增  查询
 *  1（0001）	0	 0	  0	   1
 *  2（0010）	0	 0	  1	   0
 *  4（0100）	0	 1	  0	   0
 *  8（1000）	1	 0	  0	   0
 *  3（0011）	0	 0	  1	   1
 *  0（0000）	0	 0	  0	   0
 *  15（1111）	1	 1	  1	   1
 */
public class NewPermission {
	// 是否允许查询，二进制第1位，0表示否，1表示是
	public static final int ALLOW_SELECT = 1 << 0; // 0001   十进制数1

	// 是否允许新增，二进制第2位，0表示否，1表示是
	public static final int ALLOW_INSERT = 1 << 1; // 0010   十进制数2

	// 是否允许修改，二进制第3位，0表示否，1表示是
	public static final int ALLOW_UPDATE = 1 << 2; // 0100   十进制数4

	// 是否允许删除，二进制第4位，0表示否，1表示是
	public static final int ALLOW_DELETE = 1 << 3; // 1000   十进制数8

	// 存储目前的权限状态
	private int flag;

	/**
	 *  重新设置权限
	 */
	public void setPermission(int permission) {
		flag = permission;
	}

	/**
	 *  添加一项或多项权限
	 */
	public void enable(int permission) {
		flag |= permission; // 相当于flag = flag | permission;
	}

	/**
	 *  删除一项或多项权限
	 */
	public void disable(int permission) {
		flag &= ~permission;
	}

	/**
	 *  是否拥某些权限
	 */
	public boolean isAllow(int permission) {
		return (flag & permission) == permission;
	}

	/**
	 *  是否禁用了某些权限
	 */
	public boolean isNotAllow(int permission) {
		return (flag & permission) == 0;
	}

	/**
	 *  是否仅仅拥有某些权限
	 */
	public boolean isOnlyAllow(int permission) {
		return flag == permission;
	}

	public static void main(String[] args) {
		NewPermission permission = new NewPermission();
		// 添加一项Update权限
		permission.enable(NewPermission.ALLOW_UPDATE); // 0100

		//添加Insert、Update、Delete三项权限
		permission.enable(NewPermission.ALLOW_INSERT
				| NewPermission.ALLOW_UPDATE | NewPermission.ALLOW_DELETE); //运算结果是1110

		// 是否拥某些权限
		System.out.println(permission.isAllow(NewPermission.ALLOW_INSERT)); // true

		// 判断是否允许Select和Insert、Update权限
		if (permission. isAllow (NewPermission.ALLOW_SELECT
				| NewPermission.ALLOW_INSERT | NewPermission.ALLOW_UPDATE)){

		}
	}
}
