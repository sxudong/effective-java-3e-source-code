package effectivejava.chapter8.item55;

import java.util.Optional;

/**
 * 第55条：明智审慎的返回 Optional
 */
// Avoiding unnecessary use of Optional's isPresent method (Page 252)
// 避免不必要地使用 Optional 的 isPresent方法
public class ParentPid {
    public static void main(String[] args) {
        // 在 JDK9 中引入的 ProcessHandle类
        ProcessHandle ph = ProcessHandle.current();

        // Inappropriate use of isPresent
        // 不当使用isPresent
        Optional<ProcessHandle> parentProcess = ph.parent();
        System.out.println("Parent PID: " + (parentProcess.isPresent() ?
                String.valueOf(parentProcess.get().pid()) : "N/A"));

        // Equivalent (and superior) code using orElse
        // 使用 orElse 的等效（和上等）代码
        System.out.println("Parent PID: " +
            ph.parent().map(h -> String.valueOf(h.pid())).orElse("N/A"));
    }
}
/* Output:
Parent PID: 13700
Parent PID: 13700
 */