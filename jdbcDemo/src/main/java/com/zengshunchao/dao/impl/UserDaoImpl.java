package com.zengshunchao.dao.impl;

import com.zengshunchao.dao.IUserDao;
import com.zengshunchao.entity.User;
import com.zengshunchao.utils.JdbcConnectionsPool;
import com.zengshunchao.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/20 8:37
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public List<User> getUserList() {
        Connection con = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        try {
            con = JdbcUtil.getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
            String sql = "select * from t_user";
            pt = con.prepareStatement(sql);
            rs = pt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassWord(rs.getString(3));
                list.add(user);
            }
            //关闭连接
            JdbcUtil.closeResource(rs, null, con, pt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteUser(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        int row = 0;
        try {
            String sql = "delete from t_user where id = ?";
            //建立连接
            con = JdbcUtil.getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
            //预编译
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            row = ps.executeUpdate();
            //关闭连接
            JdbcUtil.closeResource(null, null, con, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updateUser(int id, String pw) {
        Connection con = null;
        PreparedStatement ps = null;
        int row = 0;
        String sql = "update t_user set passWord = ? where id = ?";
        try {
            //建立连接
            con = JdbcUtil.getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
            //预编译
            ps = con.prepareStatement(sql);
            //添加参数
            ps.setString(1, pw);
            ps.setInt(2, id);

            row = ps.executeUpdate();
            //关闭连接
            JdbcUtil.closeResource(null, null, con, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public void addUser(User user) {
        Connection con = null;
        PreparedStatement ps = null;

        //sql
        String sql = "insert into t_user(userName,passWord) values (?,?)";
        try {
            //建立连接
            con = JdbcUtil.getConnection("jdbc:mysql://localhost:3306/zengshunchao?characterEncoding=utf8", "root", "root");
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            //执行sql
            ps.execute();
            JdbcUtil.closeResource(null, null, con, ps);

        } catch (Exception e) {

        }
    }


    public static void main(String[] args) {

        JdbcConnectionsPool pool = new JdbcConnectionsPool();
        try {
            for (int i = 0; i < 25; i++) {
                pool.getConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
