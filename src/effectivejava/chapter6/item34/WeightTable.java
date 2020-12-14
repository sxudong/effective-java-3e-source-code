package effectivejava.chapter6.item34;

/**
 * 第34条： 使用枚举类型替代整型常量
 */
// Takes earth-weight and prints table of weights on all planets (Page 160)
// 获取地球重量并打印所有行星的重量表
public class WeightTable {
   public static void main(String[] args) {
      //double earthWeight = Double.parseDouble(args[0]);
      double earthWeight = 1000.00;
      double mass = earthWeight / Planet.EARTH.surfaceGravity();
      for (Planet p : Planet.values())
         System.out.printf("Weight on %s is %f%n",
                 p, p.surfaceWeight(mass));
   }
}
