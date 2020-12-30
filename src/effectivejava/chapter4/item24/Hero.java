package effectivejava.chapter4.item24;

/**
 * 第24条：静态成员优于非静态成员类
 * 常见用法是作为公有的辅助类，只有与它的外部类一起使用才有意义。
 * 比如作为Builder
 */
public class Hero {
    private final String name;  // 名字
    private final String roles; // 角色
    private final int level;    // 等级，最高18级

    private Hero(Builder builder) {
        this.level = builder.level;
        this.name = builder.name;
        this.roles = builder.roles;
    }

    @Override public String toString() {
        return "Hero{" + "name='" + name + '\'' +
                ", roles='" + roles + '\'' +
                ", level=" + level + '}';
    }

    public static class Builder {
        //必传参数
        private final String roles;
        private final String name;
        //可选参数
        private int level = 0;
        public Builder(String roles,String name) {
            this.roles = roles;
            this.name = name;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }
        public Hero build() {
            return new Hero(this);
        }
    }

    public static void main(String[] args) {
        // 不需要外围类实例就可以创建静态成员类的实例
        Hero.Builder builder = new Builder("JUG","Graves");
        builder.level(18);
        Hero hero = builder.build();
        System.out.println(hero); // Hero{name='Graves', roles='JUG', level=18}
    }
}

