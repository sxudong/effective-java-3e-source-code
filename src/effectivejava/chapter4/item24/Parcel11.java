package effectivejava.chapter4.item24;


public class Parcel11 {
  // 静态内部类
  private static class ParcelContents {
    private int i = 11;
    public int value() { return i; }
  }
  public static void main(String[] args) {
    // 不需要外围类实例就可以创建静态成员类的实例
    ParcelContents parcelContents = new ParcelContents();
    System.out.println(parcelContents.value()); // 11
  }
}
