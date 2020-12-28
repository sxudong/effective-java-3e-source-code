package effectivejava.chapter4.item16;

/**
 * 第16条：要在公有类而非公有域中使用访问方法
 */
// Encapsulation of data by accessor methods and mutators  (Page 78)
// 用公有设值方法封装数据
class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
