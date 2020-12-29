package effectivejava.chapter4.item22;

// 使用静态导入来避免限定常量
import static effectivejava.chapter4.item22.constantutilityclass.PhysicalConstants.*;

public class Test {
    double atoms(double mols) {
        return AVOGADROS_NUMBER * mols;
    }
}