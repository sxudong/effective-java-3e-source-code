package effectivejava.chapter6.item36;

public class Permission {

	// 是否允许查询
	private boolean allowSelect;

	// 是否允许新增
	private boolean allowInsert;

	// 是否允许删除
	private boolean allowDelete;

	// 是否允许更新
	private boolean allowUpdate;

	public boolean isAllowSelect() {
		return allowSelect;
	}

	public void setAllowSelect(boolean allowSelect) {
		this.allowSelect = allowSelect;
	}

	public boolean isAllowInsert() {
		return allowInsert;
	}

	public void setAllowInsert(boolean allowInsert) {
		this.allowInsert = allowInsert;
	}

	public boolean isAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(boolean allowDelete) {
		this.allowDelete = allowDelete;
	}

	public boolean isAllowUpdate() {
		return allowUpdate;
	}

	public void setAllowUpdate(boolean allowUpdate) {
		this.allowUpdate = allowUpdate;
	}

	public static void main(String[] args) {
		Permission permission = new Permission();
		permission.setAllowSelect(true);
		permission.setAllowInsert(true);
		permission.setAllowUpdate(false);
		permission.setAllowDelete(false);

		// 判断是否允许Select和Insert、Update权限
		if (permission.isAllowSelect() && permission.isAllowInsert() && permission.isAllowUpdate()){

		}

	}
}
