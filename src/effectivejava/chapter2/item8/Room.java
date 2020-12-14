package effectivejava.chapter2.item8;

import java.lang.ref.Cleaner;

// An autocloseable class using a cleaner as a safety net (Page 32)
// 利用“清除方法”自动清除安全网的过程
public class Room implements AutoCloseable { // 实现了AutoCloseable接口
    private static final Cleaner cleaner = Cleaner.create();

    // Resource that requires cleaning. Must not refer to Room! 需要清理的资源。不能引用Room！
    // 内嵌的静态类State保存清除方法清除房间所需要的资源
    private static class State implements Runnable {
        int numJunkPiles; // Number of junk piles in this room 表示房间的复杂度

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // Invoked by close method or cleaner 通过封闭方法或Cleaner对象调用
        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    // The state of this room, shared with our cleanable
    // 房间的状态
    private final State state;

    // Our cleanable. Cleans the room when it’s eligible for gc
    // 可清除。有资格获得gc清理room
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state); // 传入state用于触发run()方法
    }

    @Override
    public void close() {
        System.out.println("调用close方法");
        cleanable.clean();
    }
}
