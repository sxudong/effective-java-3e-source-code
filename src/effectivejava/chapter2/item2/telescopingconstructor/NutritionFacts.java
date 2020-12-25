package effectivejava.chapter2.item2.telescopingconstructor;

// Telescoping constructor pattern - does not scale well! (Pages 10-11)
// 伸缩构造函数模式-伸缩性不佳！
public class NutritionFacts { // 可口可乐营养成份表
    private final int servingSize;  // (mL)            required 份量大小  必须
    private final int servings;     // (per container) required 使用次数  必须
    private final int calories;     // (per serving)   optional 卡路里    可选
    private final int fat;          // (g/serving)     optional 脂肪      可选
    private final int sodium;       // (mg/serving)    optional 纳        可选
    private final int carbohydrate; // (g/serving)     optional 碳水化合物 可选

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings,
                          int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings,
                          int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        // 构造器
        NutritionFacts cocaCola =
                new NutritionFacts(240, 8, 100, 0, 35, 27);
    }
}