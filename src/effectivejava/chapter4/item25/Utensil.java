package effectivejava.chapter4.item25;

/**
 * 第25条：限制源文件成为单个顶级类
 */
// Two classes defined in one file. Don't ever do this!  (Page 115)
class Utensil {
    static final String NAME = "pan";
}

class Dessert {
    static final String NAME = "cake";
}
