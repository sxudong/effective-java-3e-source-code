package effectivejava.chapter6.item36;

import java.util.*;

/**
 * 第36条：使用 EnumSet 替代位属性
 */

public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    public void applyStyles(Set<Style> styles) {
        System.out.printf("Applying styles %s to text%n", Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
/* Output:
Applying styles [BOLD, ITALIC] to text
 */