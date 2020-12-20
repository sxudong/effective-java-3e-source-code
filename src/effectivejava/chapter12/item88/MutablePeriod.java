package effectivejava.chapter12.item88;

import java.io.*;
import java.util.Date;

public class MutablePeriod {
    // A period instance
    public final Period period;
    // period's start field, to which we shouldn't have access 期的开始字段，我们不应该访问该字段
    public final Date start;
    // period's end field, to which we shouldn't have access 期末字段，我们不应该访问
    public final Date end;

    public MutablePeriod() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            // Serialize a valid Period instance
            out.writeObject(new Period(new Date(), new Date()));
            /*
             * Append rogue "previous object refs" for internal
             * Date fields in Period. For details, see "Java
             * Object Serialization Specification," Section 6.4.
             */
            byte[] ref = {0x71, 0, 0x7e, 0, 5};
            // Ref #5
            bos.write(ref);
            // The start field
            ref[4] = 4;
            // Ref # 4
            bos.write(ref);
            // The end field
            // Deserialize Period and "stolen" Date references
            // 反序列化期间和“被盗”日期引用
            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            period = (Period) in.readObject();
            start = (Date) in.readObject();
            end = (Date) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        MutablePeriod mp = new MutablePeriod();
        Period p = mp.period;
        Date pEnd = mp.end;
        // Let's turn back the clock 我们回头看看时间
        pEnd.setYear(78);
        System.out.println(p);
        // Bring back the 60s! 带回60年代！
        pEnd.setYear(69);
        System.out.println(p);
    }
}
/*
未用readObject()方法防御性保护输出：
Fri Dec 18 23:01:13 CST 2020 - Mon Dec 18 23:01:13 CST 1978
Fri Dec 18 23:01:13 CST 2020 - Thu Dec 18 23:01:13 CST 1969

使用readObject()方法防御性保护后输出：
Fri Dec 18 22:59:49 CST 2020 - Fri Dec 18 22:59:49 CST 2020
Fri Dec 18 22:59:49 CST 2020 - Fri Dec 18 22:59:49 CST 2020
 */