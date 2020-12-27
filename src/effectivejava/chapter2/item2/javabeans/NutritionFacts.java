package effectivejava.chapter2.item2.javabeans;

/**
 * Bean模式不把参数放在构造器里，通过方法一个一个set进去。
 */
// JavaBeans模式-允许不一致，要求可变性
public class NutritionFacts {
    // Parameters initialized to default values (if any)
    private int servingSize  = -1; // 必须; 没有默认值
    private int servings     = -1; // 必须; 没有默认值
    private int calories     = 0;
    private int fat          = 0;
    private int sodium       = 0;
    private int carbohydrate = 0;

    public NutritionFacts() { }
    // Setters
    public void setServingSize(int val)  { servingSize = val; }
    public void setServings(int val)     { servings = val; }
    public void setCalories(int val)     { calories = val; }
    public void setFat(int val)          { fat = val; }
    public void setSodium(int val)       { sodium = val; }
    public void setCarbohydrate(int val) { carbohydrate = val; }

    public static void main(String[] args) {
        // 哪些参数需要值，哪些参数不需要值，没有一个明确的要求，用的时候依然很迷惑。
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }
}