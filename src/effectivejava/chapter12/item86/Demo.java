package effectivejava.chapter12.item86;

import java.io.*;

/**
 * 第86条：谨慎的实现Serializable接口
 */
class Demo implements java.io.Serializable {
    public int a;
    public String b;

    // Default constructor 
    public Demo(int a, String b) {
        this.a = a;
        this.b = b;
    }

}

class Test {
    public static void main(String[] args) {
        Demo object = new Demo(1, "geeksforgeeks");
        String filename = "file.ser";

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


        Demo object1 = null;

        // Deserialization 反序列化
        try {
            // Reading the object from a file 从文件中读取对象
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object 对象反序列化的方法
            object1 = (Demo) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("a = " + object1.a);
            System.out.println("b = " + object1.b);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }
}
/* Output:
Object has been serialized
Object has been deserialized
a = 1
b = geeksforgeeks
 */