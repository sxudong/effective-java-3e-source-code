package effectivejava.chapter2.item8.example;

public class A {
    @SuppressWarnings("unused")
    //终结守卫者
    //为了防止继承者粗心大意，没有调用父类的finalize()，都会执行finalizerGuardian，所谓的安全网。
    private final Object finalizerGuardian = new Object() {
        @Override
        //终结守卫者的终结方法将被执行
        protected void finalize () throws Throwable {
            System.out.println("A finalize by the finalizerGuardian");
        }
    };

    @Override
    //由于终结方法被子类覆盖，该终结方法并不会被执行
    protected void finalize() {
        System.out.println("A finalize by the finalize method" );
    }
}


