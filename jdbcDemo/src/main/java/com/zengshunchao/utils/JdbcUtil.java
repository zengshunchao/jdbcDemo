package com.zengshunchao.utils;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/19 18:01
 */

import java.sql.*;

/**
 * 工具类
 */
public class JdbcUtil {

    private static JdbcConnectionsPool pool = new JdbcConnectionsPool();

    /**
     * 建立数据库连接
     *
     * @param url
     * @param user
     * @param password
     * @return
     * @throws Exception
     */
    public static Connection getConnection(String url, String user, String password) throws Exception {

        return pool.getConnection();
    }

    /**
     * 释放资源，关闭连接
     *
     * @param rs
     * @param st
     * @param ct
     */
    public static void closeResource(ResultSet rs, Statement st, Connection ct, PreparedStatement ps) {

        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ct != null) {
            try {
                ct.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
            System.out.println(connection);
        } catch (Exception e) {

        }
    }

}
