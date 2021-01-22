package effectivejava.chapter2.item9.example;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 9. try-with-resources 优于 try-finally
 * https://zhuanlan.zhihu.com/p/130494132
 */
public class JDBCUtilTest {
    public static void main(String[] args) {
        String sql = "SELECT * FROM USER";

        try (JDBCUtil jdbcUtil = new JDBCUtil()) {
            ResultSet resultSet = jdbcUtil.getResultSet(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_name"));
                System.out.println("离开 try-catch 代码块");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("程序执行结束");
    }
}
/* Output:
...  // 查询结果
离开 try-catch 代码块
连接已释放
程序执行结束
 */