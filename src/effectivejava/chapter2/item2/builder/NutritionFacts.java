package effectivejava.chapter2.item2.builder;

/**
 * 第2条：构造器参数过多使用Builder
 */
// Builder Pattern 建造者模式 (Page 13)
public class NutritionFacts { // 可口可乐营养成份表
    private final int servingSize;  //份量大小  必须
    private final int servings;     //使用次数  必须
    private final int calories;     //卡路里    可选
    private final int fat;          //脂肪      可选
    private final int sodium;       //纳        可选
    private final int carbohydrate; //碳水化合物 可选

    public static class Builder {
        // Required parameters 必要参数
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        // 可选参数-初始化为默认值
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        // builder模式，它结合了可伸缩构造方法模式的“安全性”和JavaBean模式的“可读性”。
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
    }
}