package effectivejava.chapter12.item89.enumsingleton.example;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 第89条：对于实例控制，枚举类型优于 readResolve
 */
// Broken singleton - has nontransient object reference field!
// 破碎的单例-具有非瞬态对象参考字段！
public class Elvis implements Serializable {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    private String[] favoriteSongs = {"Hound Dog", "Heartbreak Hotel"};

    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }

    private Object readResolve() {
        return INSTANCE;
    }
}