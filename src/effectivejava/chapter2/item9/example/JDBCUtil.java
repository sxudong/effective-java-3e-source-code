package effectivejava.chapter2.item9.example;

import java.sql.*;

// 实现AutoCloseable接口，并重写close()方法
public class JDBCUtil implements AutoCloseable {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:port/database_name";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // 重写close()方法，完成释放资源的操作
    @Override
    public void close() throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }

        System.out.println("连接已释放");
    }

    public ResultSet getResultSet(String sql) throws SQLException {
        getStatement();
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
        return connection;
    }

    private Statement getStatement() throws SQLException {
        getConnection();
        statement = connection.createStatement();
        return statement;
    }

    public JDBCUtil() {

    }
}