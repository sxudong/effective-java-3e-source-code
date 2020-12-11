package effectivejava.chapter8.item52;

/**
 * 第52条: 明智审慎的使用重载
 */
// Classification using method overrriding 使用重写方法进行分类 (Page 239)
class Wine {
    String name() { return "wine"; }
}

class Champagne extends SparklingWine {
    @Override String name() { return "champagne"; }
}

class SparklingWine extends Wine {
    @Override String name() { return "sparkling wine"; }
}