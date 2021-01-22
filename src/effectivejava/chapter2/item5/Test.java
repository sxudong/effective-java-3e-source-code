package effectivejava.chapter2.item5;

public class Test {
    public static void main(String[] args) {
        // 依赖注入
        Person person = Person.getInstance(new AndroidPhone());

        /*
         * 虽然依赖注人极大地提升了灵活性和可测试性，但当项目很庞大时，
         * 依赖关系会变得复杂、凌乱。所以在大项目目中，我们不用手动DI，
         * 而是使用依赖注入框架（如Spring框架）自动DI。
         */
    }
}