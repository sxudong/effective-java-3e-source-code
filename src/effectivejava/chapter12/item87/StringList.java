package effectivejava.chapter12.item87;

import java.io.*;

/**
 * 第87条：考虑使用自定义的序列化形式
 */
// StringList with a reasonable custom serialized form  - Page 349
// 具有合理的自定义序列化格式的StringList
public final class StringList implements Serializable {
    private transient int size   = 0; // transient不序列化字段
    private transient Entry head = null;

    // No longer Serializable!
    // 此类不再实现Serializable接口
    private static class Entry {
        String data;
        Entry  next;
        Entry  previous;
    }

    // Appends the specified string to the list
    // 将指定的字符串追加到列表
    public final void add(String s) {
        size++;
        Entry entry = new Entry();
        entry.data = s;
        head.next = entry;
    }

    /**
     * 自定义序列化
     * Serialize this {@code StringList} instance.
     * 序列化此{@code StringList}实例。
     *
     * @serialData The size of the list (the number of strings
     * it contains) is emitted ({@code int}), followed by all of
     * its elements (each a {@code String}), in the proper
     * sequence.
     * 发出列表的大小（列表中包含的字符串数）（{@code int}），然后按照
     * 适当的顺序发出列表的所有元素（每个元素为{@code String}）。
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        System.out.println("writeOject");
        s.defaultWriteObject();
        s.writeInt(size);

        // Write out all elements in the proper order.
        // 按照正确的顺序写出所有元素。
        for (Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }

    /**
     * 自定义反序列化
     * @param s
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        System.out.println("readOject") ;
        s.defaultReadObject();
        int numElements = s.readInt();

        // Read in all elements and insert them in list
        // 阅读所有元素并将它们插入列表
        for (int i = 0; i < numElements; i++)
            add((String) s.readObject());
    }

    // Remainder omitted 其余省略

    public static void main(String[] args) {
        File tempfile = new File("./", "item87.txt");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempfile));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempfile));

            if (!tempfile.exists()) {
                tempfile.createNewFile();
            }
            StringList a = new StringList();
            oos.writeObject(a);
            StringList temp = (StringList) ois.readObject();
            System.out.println(temp);
            System.out.println(temp.size); // transient修饰字段反序列为默认值0
            System.out.println(temp.head); // transient修饰字段反序列为默认值null
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/* Output:
writeOject
readOject
effectivejava.chapter12.item87.StringList@21b8d17c
0
null
 */