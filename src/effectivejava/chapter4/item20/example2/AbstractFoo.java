package effectivejava.chapter4.item20.example2;

public abstract class AbstractFoo implements IFoo {

    // 增了自己独有的方法，setVal()和getVal()方法。
    private String val;
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }

    // 并实现了其中的大家会公用的基础方法foo()
    @Override  public void foo() {
        System.out.println("AbstarctFoo");
    }
}