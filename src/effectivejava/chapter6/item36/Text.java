package effectivejava.chapter6.item36;

import java.util.*;

// EnumSet - a modern replacement for bit fields (Page 170)
// 第36条：用 EnumSet 代替位域
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // Any Set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles) {
        // %n /n 回车换行
        // System.out.printf("Applying styles %s to text/n", Objects.requireNonNull(styles));
        System.out.printf("Applying styles %s to text%n", Objects.requireNonNull(styles));
    }

    // Sample use
    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
/* Output:
Applying styles [BOLD, ITALIC] to text

 */