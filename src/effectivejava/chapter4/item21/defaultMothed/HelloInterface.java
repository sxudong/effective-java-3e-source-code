package effectivejava.chapter4.item21.defaultMothed;

interface HelloInterface {
    public default void hello() {
        System.out.println("default hello");
    }
}