package effectivejava.chapter4.item20;

import java.util.AbstractMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map.Entry<String,String> entry = new AbstractMap.SimpleEntry<String, String>("name", "野猿新一");
        System.out.println("new AbstractMap.SimpleEntry:" + entry);
        System.out.println("getKey:" + entry.getKey());
        System.out.println("getValue:" + entry.getValue());
        entry.setValue("野猿新二");
        System.out.println("setValue:" + entry);
    }
}
/* Output:
new AbstractMap.SimpleEntry:name=野猿新一
getKey:name
getValue:野猿新一
setValue:name=野猿新二
 */