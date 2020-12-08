package effectivejava.chapter4.item22.constantinterface;

/**
 * 第22条：用接口定义类型
 */
// Constant interface antipattern - do not use!
// 常量接口模式是对接口的不食使用
public interface PhysicalConstants {
    // Avogadro's number (1/mol)
    static final double AVOGADROS_NUMBER   = 6.022_140_857e23;

    // Boltzmann constant 玻尔兹曼常数 (J/K)
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

    // Mass of the electron 电子质量 (kg)
    static final double ELECTRON_MASS      = 9.109_383_56e-31;
}
