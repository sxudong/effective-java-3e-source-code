package effectivejava.chapter12.item86.serial;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 第86条：谨慎地实现Serializable接口
 *
 * 如果类的实例字段初始化为默认值（整数类型为 0，布尔值为 false，对象引用类型为 null），
 * 那么必须添加 readObjectNoData() 方法：
 */
public class Test3 {
    // 当对象序列化的版本和反序列化时的class版本不同时会调用父类BaseBean的readObjectNoData()
    public static void main(String[] args) {
        String filename = "test3.ser";
        TestBean object1 = null;

        // Deserialization 反序列化
        try {
            // Reading the object from a file 从文件中读取对象
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object 对象反序列化的方法
            object1 = (TestBean) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("property4 = " + object1.property4);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}
/* Output:
readObjectNoData:TestService
Object has been deserialized
property4 = readObject ....
 */