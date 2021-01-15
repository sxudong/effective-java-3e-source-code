package effectivejava.chapter6.item36;

import java.util.*;

/**
 * 第36条：使用 EnumSet 替代位域
 */

public class Text2 {
    public enum Style {
        BOLD,         // 粗体
        ITALIC,       // 斜体
        UNDERLINE,    // 底线
        STRIKETHROUGH // 删除线
    }

    // 注意此处，使用的是Set而不是EnumSet
    public void applyStyles(Set<Style> styles) {
        System.out.printf("Applying styles %s to text%n", Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        Text2 text = new Text2();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
/* Output:
Applying styles [BOLD, ITALIC] to text
 */