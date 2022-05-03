package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        String query = String.format("CREATE TABLE %s()", tableName);
        executeUpdate(query);
    }

    public void dropTable(String tableName) {
        String query = String.format("DROP TABLE %s", tableName);
        executeUpdate(query);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String query = String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type);
        executeUpdate(query);
    }

    public void dropColumn(String tableName, String columnName) {
        String query = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
        executeUpdate(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String query = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName);
        executeUpdate(query);
    }

    public void executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                tableEditor.createTable("test");
                System.out.println("Create table:");
                System.out.println(getTableScheme(tableEditor.connection, "test"));
                tableEditor.addColumn("test", "name", "TEXT");
                System.out.println("Add column:");
                System.out.println(getTableScheme(tableEditor.connection, "test"));
                tableEditor.renameColumn("test", "name", "new_name");
                System.out.println("Rename column:");
                System.out.println(getTableScheme(tableEditor.connection, "test"));
                tableEditor.dropColumn("test", "new_name");
                System.out.println("Drop column:");
                System.out.println(getTableScheme(tableEditor.connection, "test"));
                tableEditor.dropTable("test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}