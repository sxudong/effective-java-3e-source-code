package effectivejava.chapter12.item86;

import java.io.*;

/**
 * 测试 writeObject() 和 readObject()
 */
public class Test2 implements java.io.Serializable {
    private String name;
    private int age;
    private double a;

    public Test2(String name, int age, double a) {
        this.name = name;
        this.age = age;
        this.a = a;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeOject");
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
        out.writeDouble(a);
    }

    private void readObject(ObjectInputStream in) throws IOException , ClassNotFoundException{
        System.out.println("readOject");
        this.name = ( (StringBuffer)in.readObject() ).reverse().toString() ;
        this.age = in.readInt() ; //这样就跟write时的顺序一致了
        this.a = in.readDouble() ;
    }

    private void readObjectNoData() throws ObjectStreamException {
        System.out.println("NoOject");
        this.name = "无名氏";
        this.age = 0;
        this.a = 99.9;
    }

    @Override
    public String toString() {
        return this.name + "\t" + age + "\t" + a;
    }

    public static void main(String[] args) {
        File tempfile = new File("./", "Out.txt");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempfile));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempfile));

            if (!tempfile.exists()) {
                tempfile.createNewFile();
            }
            Test2 a = new Test2("测试", 56, 4.5);
            oos.writeObject(a);
            Test2 temp = (Test2) ois.readObject();
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/* Output:
writeOject
readOject
测试	56	4.5
 */