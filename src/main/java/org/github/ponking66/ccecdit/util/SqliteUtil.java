package org.github.ponking66.ccecdit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author pony
 * @date 2023/7/18
 */
public class SqliteUtil {

    public static String SQLITE = "jdbc:sqlite:";
    public static String SQLITE_RESOURCE = "jdbc:sqlite::resource:";
    public static String SQLITE_RESOURCE_JAR = "jdbc:sqlite::resource:jar:";

    private SqliteUtil() {

    }

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection create(String sqliteFilePath) throws SQLException {
        return DriverManager.getConnection(sqliteFilePath);
    }

    public static Connection createConn(String sqliteFilePath) throws SQLException {
        return DriverManager.getConnection(SQLITE + sqliteFilePath);
    }

    public static Connection createConnByResource(String sqliteFilePath) throws SQLException {
        return DriverManager.getConnection(SQLITE_RESOURCE + sqliteFilePath);
    }

    public static Connection createConnByResourceJar(String sqliteFilePath) throws SQLException {
        return DriverManager.getConnection(SQLITE_RESOURCE_JAR + sqliteFilePath);
    }
}
