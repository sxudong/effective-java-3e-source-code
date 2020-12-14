package effectivejava.chapter6.item38;

/**
 * 第38条：使用接口模拟可扩展的枚举
 */
// Emulated extensible enum using an interface (Page 176)
// 使用接口模拟可扩展枚举
public interface Operation {
    double apply(double x, double y);
}
