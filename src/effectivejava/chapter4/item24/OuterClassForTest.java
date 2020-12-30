package effectivejava.chapter4.item24;


/**
 *
 */
class OuterClassForTest{
    public void showInnerClass() {
        // 内部类只能被外部类访问
        InnerClassForTest inner = new InnerClassForTest();
        inner.getClass();
    }

    public class InnerClassForTest{
        private String className = "Inner Class";
        public void getClassName() {
            System.out.println(className);
        }
    }
}
