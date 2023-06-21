package com.shop.shopproduct.core;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnDBUtil {
    static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private Context initalContext;
    private static ConnDBUtil connUtil = null;

    public static ConnDBUtil getInstance() {
        if (connUtil == null) {
            connUtil = new ConnDBUtil();
        }
        return connUtil;
    }

    private ConnDBUtil() {
        try {
            this.initalContext = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context ctx = (Context) initalContext.lookup("java:comp/env");
            dataSource = (DataSource) ctx.lookup("jdbc/five");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public DataSource getDataSource(String dataSourceName) {
        DataSource datasource = null;
        try {
            Context ctx = (Context) initalContext.lookup("java:comp/env");
            datasource = (DataSource) ctx.lookup(dataSourceName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    public Connection getConnection() throws SQLException {

        Connection con = threadLocal.get();
        if (con == null) {
            con=getDataSource().getConnection();
            threadLocal.set(con);
        }
        return con;
    }

    public void closeConnection() throws SQLException {
        Connection con = threadLocal.get();
        if (con != null && !con.isClosed()) {
            con.close();
            threadLocal.set(null);
        }
    }

}

