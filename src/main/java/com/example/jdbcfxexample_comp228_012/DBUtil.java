package com.example.jdbcfxexample_comp228_012;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil {

    private static Connection connection = null;
    private static Statement statement = null;

    public static void dbConnect() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
        String username = "COMP228_F21_sh_5";
        String password = "password";
        connection = DriverManager.getConnection(dbUrl, username,password);
        System.out.println("Database is connected!");
        statement = connection.createStatement();
    }

    public static void dbDisconnect()throws SQLException{
        if(connection != null && !connection.isClosed()){
            connection.close();
            System.out.println("Database is closed!");
        }
    }

    public static void dropTable(String tableName)throws SQLException{
        dbConnect();

        String sql = "DROP TABLE " + tableName;
        statement.execute(sql);
        System.out.println("Table " + tableName + " is dropped!");

        if (statement !=null) {
            statement.close();
        }
        dbDisconnect();
    }

    public static void createTable(String tableName)throws SQLException{
        dbConnect();

        String sql = "CREATE TABLE " + tableName + " (s_id INTEGER PRIMARY KEY, s_name VARCHAR2(100))";
        statement.execute(sql);
        System.out.println("Table " + tableName + " is created!");

        if (statement !=null) {
            statement.close();
        }
        dbDisconnect();
    }

    public static void insertData(String tableName, Integer s_id, String s_name)throws SQLException{

        dbConnect();

        String sql = "INSERT INTO " + tableName + " VALUES(" + s_id + ",'" + s_name + "')";
        statement.executeUpdate(sql);
        System.out.println("Data is inserted!");

        if (statement !=null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static ResultSet query(String tableName, String sql)throws SQLException{
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();

        dbConnect();
        ResultSet resultSet = null;
        resultSet = statement.executeQuery(sql+ " " + tableName);
        crs.populate(resultSet);

        if (statement !=null) {
            statement.close();
        }
        dbDisconnect();

        return crs;
    }

    public static void delete(String tableName, Integer s_id)throws SQLException{
        dbConnect();
        String sql = "DELETE FROM " + tableName + " WHERE s_id =" + s_id;
        statement.executeUpdate(sql);
        System.out.println("Data is deleted!");

        if (statement !=null) {
            statement.close();
        }
        dbDisconnect();

    }

    public static void main(String[] arg) throws SQLException{

        //DBUtil.dbConnect();
        //DBUtil.dbDisconnect();
        //DBUtil.dropTable("javaClassTest012");
        //DBUtil.createTable("COMP228_012");
        //DBUtil.insertData("javaClassTest012", 3,"Ben");
        //DBUtil.query("javaClassTest012", "SELECT * FROM");

    }


}
