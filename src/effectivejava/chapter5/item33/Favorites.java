package effectivejava.chapter5.item33;

import java.util.*;

/**
 * 第33条：优先考虑类型安全的异构容器
 */
// Typesafe heterogeneous container pattern 类型安全的异构容器模式 (Pages 151-4)
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> T getFavorite(Class<T> type) {
        // 使用了Class.cast()方法，检验它的参数是否为Class对象所表示的类型实例
        return type.cast(favorites.get(type));
    }

//    public <T> void putFavorite(Class<T> type, T instance) {
//        favorites.put(Objects.requireNonNull(type), instance);
//    }

    // 确保Favorites永远不违背它的类型约束条件的方式是，让putFavorite()方法检验instance是否真的是type所表示的类型的实例
    // Achieving runtime type safety with a dynamic cast 通过动态强制转换实现运行时类型安全
    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), type.cast(instance));
    }

    // java.util.Collections 中的 checkedSed()方法 用到了改进后的 putFavorite()方法同样的技巧
//    public static <E> Set<E> checkedSet(Set<E> s, Class<E> type) {
//        return new Collections.CheckedSet(s, type);
//    }

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(String.class, "C++");       // 会覆盖相同Class的值
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favoriteString,
                favoriteInteger, favoriteClass.getName());
    }

    // Class#cast(obj)方法源码
//    @HotSpotIntrinsicCandidate
//    public T cast(Object obj) {
//        if (obj != null && !this.isInstance(obj)) {
//            throw new ClassCastException(this.cannotCastMsg(obj));
//        } else {
//            return obj;
//        }
//    }

}
/* Output:
C++ cafebabe effectivejava.chapter5.item33.Favorites
 */