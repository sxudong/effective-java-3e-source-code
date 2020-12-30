package effectivejava.chapter4.item25;

/**
 * 第25条：限制源文件成为单个顶级类
 */
// 在一个文件中定义了两个类。 永远不要这样做！(Page 115)
class Utensil { // 顶级类
    static final String NAME = "pan";
}

class Dessert { // 顶级类
    static final String NAME = "cake";
}
