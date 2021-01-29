package effectivejava.chapter2.item3;

import java.io.*;

/**
 * 示例来自《Java设计模式精讲》8-5 单例设计模式-饿汉式
 */
public class HungrySingleton implements Serializable, Cloneable{
    // public final static HungrySingleton hungrySingleton = new HungrySingleton(); // 公有静态成员是final域
    private final static HungrySingleton hungrySingleton; 
    // 类一加载就把实例加载了
    static{
        hungrySingleton = new HungrySingleton();
    }
    // 私有构造器
    private HungrySingleton(){
        if(hungrySingleton != null){
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    // 公有的成员是一个静态工厂方法
    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

    /**
     * 防止因反序列化而重新创建了对象
     */
    private Object readResolve(){
        return hungrySingleton;
    }

    // 单例要么不要实现Cloneable克隆，实现了就返回getInstance()
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getInstance();
    }

    public static void main(String[] args) {
        effectivejava.chapter4.item19.example.HungrySingleton s1 = effectivejava.chapter4.item19.example.HungrySingleton.getInstance();
        effectivejava.chapter4.item19.example.HungrySingleton s2 = null;
        try {
            // 将s1序列化到磁盘
            FileOutputStream fos = new FileOutputStream("a.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);

            oos.flush();

            FileInputStream fis = new FileInputStream("a.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            // 从磁盘反序列化
            s2 = (effectivejava.chapter4.item19.example.HungrySingleton) ois.readObject();
            System.out.println(s1 == s2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
