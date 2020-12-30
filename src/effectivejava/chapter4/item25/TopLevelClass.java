package effectivejava.chapter4.item25;

/**
 * 何谓顶级类？
 * 当你在其它类的外面声明一个类时，Java就认为该类是一个顶级类。也就是任何不是嵌套类的类。
 */
class TopLevelClass { // 顶级类
    static class NestedTopLevelClass { // 嵌套顶级类
    }
}