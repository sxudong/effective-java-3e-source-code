package effectivejava.chapter2.item9.example;

import effectivejava.chapter2.item9.example.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 我第一次使用 JDBC 时，最让我头疼的一件事情就是要不停的捕获异常和释放资源。
 * https://zhuanlan.zhihu.com/p/130494132
 */
public class JDBCUtilOld {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM USER";

        // 捕获 SQLException
        try {
            // JDBCUtil这个工具类封装了加载数据库驱动和连接数据库的方法，它的代码不是重点，故不给出
            //connection = JDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("user_name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {                          // 无论上面的语句是否正常执行，都必须释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}