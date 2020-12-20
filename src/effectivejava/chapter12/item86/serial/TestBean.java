package effectivejava.chapter12.item86.serial;

import java.io.*;

/**
 * 当对象序列化的版本和反序列化时的class版本不同时会调用父类BaseBean的readObjectNoData()
 */
//public class TestBean implements Serializable { // 1.版本改变前序列化
public class TestBean extends BaseBean implements Serializable { // 2.版本改变后的反序列化
    public String property1;
    public String property2;
    public int property3;
    public String desc;
    public static final int serialVersionUID = 1;

    @Override
    public String toString() {
        return "TestBean{" +
                "desc='" + desc + '\'' +
                ", property1='" + property1 + '\'' +
                ", property2='" + property2 + '\'' +
                ", property3=" + property3 +
                '}' + super.toString();
    }

    public static void main(String[] args) {
        TestBean object = new TestBean();
        String filename = "test3.ser";

        // Serialization
        try {
            // Saving of object in a file 将对象保存在文件中
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object 对象序列化的方法
            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}