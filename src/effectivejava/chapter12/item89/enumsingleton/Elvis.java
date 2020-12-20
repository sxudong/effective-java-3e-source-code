package effectivejava.chapter12.item89.enumsingleton;

import java.util.*;

/**
 * 第89条：对于实例控制，枚举类型优于 readResolve
 */
// Enum singleton - the preferred approach - Page 311
// 枚举单例-首选方法
public enum Elvis {
    INSTANCE;
    private String[] favoriteSongs = { "Hound Dog", "Heartbreak Hotel" };

    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }
}