package effectivejava.chapter12.item89.enumsingleton.example;

import java.io.Serializable;

/**
 * 盗用者类
 */
public class ElvisStealer implements Serializable {
    static Elvis impersonator;
    private static final long serialVersionUID = 0;
    private Elvis payload;

    private Object readResolve() {
        // Save a reference to the "unresolved" Elvis instance
        // 保存对“未解决的” Elvis实例的引用
        impersonator = payload;
        // Return object of correct type for favoriteSongs field
        // 返回最喜欢的歌曲字段的正确类型的对象
        return new String[]{"A Fool Such as I"};
    }
}