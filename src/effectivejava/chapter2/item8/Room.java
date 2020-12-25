package effectivejava.chapter2.item8;

import java.lang.ref.Cleaner;

// 利用“清除方法”自动清除安全网的过程
public class Room implements AutoCloseable { // 实现了AutoCloseable接口
    private static final Cleaner cleaner = Cleaner.create();

    // 需要清理的资源。不能引用Room！
    // 内嵌的静态类State保存清除方法清除房间所需要的资源
    private static class State implements Runnable {
        int numJunkPiles; // 表示房间的复杂度

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // 通过封闭方法或Cleaner对象调用
        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    // 房间的状态
    private final State state;

    // 可清除。有资格获得gc清理room
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        // 传入state用于触发run()方法
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        System.out.println("调用close方法");
        cleanable.clean();
    }
}
