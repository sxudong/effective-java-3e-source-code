package effectivejava.chapter4.item19.example;

import java.io.*;

/**
 * 单例：饿汉式
 * https://zhuanlan.zhihu.com/p/136769959
 */
public class HungrySingleton implements Serializable {
    private HungrySingleton() {
    }

    private static final HungrySingleton hungry = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return hungry;
    }

    /**
     * 防止因反序列化而重新创建了对象
     */
    private Object readResolve() {
        return hungry;
    }

    public static void main(String[] args) {
        HungrySingleton s1 = HungrySingleton.getInstance();
        HungrySingleton s2 = null;
        try {
            // 将s1序列化到磁盘
            FileOutputStream fos = new FileOutputStream("a.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s1);

            oos.flush();

            FileInputStream fis = new FileInputStream("a.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            // 从磁盘反序列化
            s2 = (HungrySingleton) ois.readObject();
            System.out.println(s1 == s2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}