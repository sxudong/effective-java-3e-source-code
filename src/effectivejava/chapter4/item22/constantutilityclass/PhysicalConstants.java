package effectivejava.chapter4.item22.constantutilityclass;

/**
 * 第22条：用接口定义类型
 */
// Constant utility class 常量实用程序类 (Page 85)
public class PhysicalConstants { // 物理常数
  // Prevents instantiation 防止实例化
  private PhysicalConstants() { }
  // 阿伏伽德罗数 (1/mol)
  public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
  // 波兹曼常数 (J/K)
  public static final double BOLTZMANN_CONST  = 1.380_648_52e-23;
  // 电子质量 (kg)
  public static final double ELECTRON_MASS    = 9.109_383_56e-31;
}
