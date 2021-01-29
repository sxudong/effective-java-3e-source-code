package effectivejava.chapter4.item21.defaultMothed;

class My implements HelloInterface {

    // 覆盖默认方法
    @Override
    public void hello() {
        System.out.println("hello melin");
    }
}