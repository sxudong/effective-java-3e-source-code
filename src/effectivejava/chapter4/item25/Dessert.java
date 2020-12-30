package effectivejava.chapter4.item25;

/**
 * 第25条：限制源文件成为单个顶级类
 * 两个文件放在同一个包下，不用自已限制，IDEA都会帮你报警。
 */
// Two classes defined in one file. Don't ever do this! (Page 115)
//class Utensil { // 顶级类
//    static final String NAME = "pot";
//}
//
//class Dessert { // 顶级类
//    static final String NAME = "pie";
//}