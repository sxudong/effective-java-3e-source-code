package effectivejava.chapter4.item22.constantutilityclass;

/**
 * 第22条：用接口定义类型
 */
// Constant utility class 常量实用程序类 (Page 85)
public class PhysicalConstants {
  private PhysicalConstants() { }  // Prevents instantiation 防止实例化

  // Avogadro's number (1/mol)
  public static final double AVOGADROS_NUMBER = 6.022_140_857e23;

  // Boltzmann constant (J/K)
  public static final double BOLTZMANN_CONST  = 1.380_648_52e-23;

  // Mass of the electron (kg)
  public static final double ELECTRON_MASS    = 9.109_383_56e-31;
}
