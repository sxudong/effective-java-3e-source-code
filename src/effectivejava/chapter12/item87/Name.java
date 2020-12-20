package effectivejava.chapter12.item87;

import java.io.Serializable;

// Good candidate for default serialized form
// 默认序列化表格的理想选择
public class Name implements Serializable {

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    /**
     * Last name. Must be non-null.
     *
     * @serial  // @serial标记 的存在告诉 Javadoc 将此文档放在一个特殊的页面上，该页面记录序列化的形式。
     */
    private final String lastName;
    /**
     * First name. Must be non-null.
     *
     * @serial
     */
    private final String firstName;
    /**
     * Middle name, or null if there is none.
     *
     * @serial
     */
    private final String middleName;


    // Remainder omitted

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
}