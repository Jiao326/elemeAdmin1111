package com.neusoft.dao.Impl;

import com.neusoft.domain.Admin;
import com.neusoft.dao.AdminDao;
import com.neusoft.utils.JDBCUtils;


import java.sql.*;
import java.util.ArrayList;

public class AdminDaoImpl implements AdminDao
{
    private  Connection conn = null;
    private PreparedStatement pstmt = null;
    private  ResultSet rs = null;
    ArrayList<Admin> list=null;
    @Override
    public Admin getAdminByNameByPass(String adminName, String password)
    {
        Admin admin=new Admin();
        String sql="select * from admin where adminName = ? and password = ?";
        try{
            conn= JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,adminName);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
            while (rs.next()){
                admin =new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pstmt, conn);
        }
        return admin;
    }

//    @Override
//    public Admin save(Integer adminId, String adminName, String password) throws SQLException
//    {
//        Admin admin1=new Admin();
//        String sql="insert into admin values(?,?,?)";
//        try{
//            conn=JDBCUtils.getConnection();
//            pstmt=conn.prepareStatement(sql);
//            pstmt.setInt(1,adminId);
//            pstmt.setString(2,adminName);
//            pstmt.setString(3,password);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return admin1;
//    }
}
