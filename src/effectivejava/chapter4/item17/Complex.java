package effectivejava.chapter4.item17;

/**
 * 第17条：使可变性最小化
 */
// Immutable complex number class 不变复数类 (Pages 65-66)
public final class Complex {
    private final double re;
    private final double im;

    // 对于频繁使用的值，可以提供公有的静态final变量
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE  = new Complex(1, 0);
    public static final Complex I    = new Complex(0, 1);

    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart()      { return re; }
    public double imaginaryPart() { return im; }

    // 静态工厂，与私有构造函数结合使用
    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    // 方法名是"介词"，这是为了强调该方法不会改变对象的值
    public Complex plus(Complex c) {
        // 每次返回新的对象，而不是修改这个实例。
        return new Complex(re + c.re, im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re, im - c.im); //每次返回新的对象
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im,
                re * c.im + im * c.re); //每次返回新的对象
    }

    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                (im * c.re - re * c.im) / tmp); //每次返回新的对象
    }

    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;

        // See page 47 to find out why we use compare instead of ==
        return Double.compare(c.re, re) == 0
                && Double.compare(c.im, im) == 0;
    }
    @Override public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override public String toString() {
        return "(" + re + " + " + im + ")";
    }
}
