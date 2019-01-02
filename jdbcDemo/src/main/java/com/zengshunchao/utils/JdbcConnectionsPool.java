package com.zengshunchao.utils;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/20 9:35
 */

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * 数据库连接池
 */
public class JdbcConnectionsPool implements DataSource {

    //创建linkedList
    private static LinkedList<Connection> list = new LinkedList<>();
    private static String url;//数据库url
    private static String username;//数据库登陆名
    private static String password;//数据库的登陆密码
    private static int jdbcConnectionInitSize = 20;//最小连接数量
    private static int max = 50; //当前最大连接数量=max*jdbcConnectionInitSize

    /**
     * 使用静态代码块，初始化连接池
     */
    static {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < jdbcConnectionInitSize; i++) {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
                //将创建好的连接放到linkedList集合
                list.add(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        //如果集合中没有数据库连接对象了，且创建的数据库连接对象没有达到最大连接数量，可以再创建一组数据库连接对象以备使用
        if (list.size() == 0) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < jdbcConnectionInitSize; i++) {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
                System.out.println("获取到了链接" + conn);
                //将创建好的数据库连接对象添加到Linkedlist集合中
                list.add(conn);
            }
            max++;
        }
        if (list.size() > 0) {
            //从linkedlist集合中取出一个数据库链接对象Connection使用
            final Connection conn1 = list.removeFirst();
            System.out.println("linkedlist1数据库连接池大小是" + list.size());
            /*返回一个Connection对象，并且设置Connection对象方法调用的限制，
             *当调用connection类对象的close()方法时会将Connection对象重新收集放入linkedlist集合中。
             */
            return (Connection) Proxy.newProxyInstance(conn1.getClass().getClassLoader(),//这里换成JdbcConnectionsPool.class.getClassLoader();也行
                    conn1.getClass().getInterfaces(), new InvocationHandler() {

                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (!method.getName().equalsIgnoreCase("close")) {
                                return method.invoke(conn1, args);
                            } else {
                                list.add(conn1);
                                System.out.println(conn1 + "对象被释放，重新放回linkedlist集合中！");
                                System.out.println("此时Linkedlist集合中有" + list.size() + "个数据库连接对象！");
                                return null;
                            }
                        }
                    });
        } else {
            System.out.println("连接数据库失败！");
        }
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
