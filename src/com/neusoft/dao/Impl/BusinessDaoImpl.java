package com.neusoft.dao.Impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.domain.Business;
import com.neusoft.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao
{
    private Connection conn=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    @Override
    public List<Business> listBusiness(String businessName, String businessAddress)
    {
        List<Business> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1");
        if(businessName!=null && !businessName.equals("")){
            //传入了商家名
            sql.append(" and businessName like '%").append(businessName).append("%' ");
            //System.out.println(sql);
        }
        if(businessAddress!=null && !businessAddress.equals("")){
            //传入了商家名
            sql.append(" and businessAddress like '%").append(businessAddress).append("%' ");
            System.out.println(sql);
        }
        try{
            conn= JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            while (rs.next()){
                Business business =new Business();
                business.setBusinessAddress(rs.getString("BusinessAddress"));
                business.setBusinessName(rs.getNString("BusinessName"));
                business.setPassword(rs.getString("Password"));
                business.setBusinessId(rs.getInt("BusinessId"));
                list.add(business);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return list;
    }

    @Override
    public int saveBusiness(String businessName) {
        int businessId = 0;
        // 附带一个初始密码
        String sql = "insert into business(businessName, password)values(?, '123456')";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 可以在prepareStatement中设置返回自增长列的值
            pstmt.setString(1, businessName);
            pstmt.executeUpdate();
            // 获取自增长的列
            rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                businessId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);
        }
        return businessId;
}

    @Override
    public int deleteBusiness(Integer businessId)
    {
        String sql="delete from business where businessId = ?";
        try {
            conn=JDBCUtils.getConnection();
            pstmt=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,businessId);
            pstmt.executeUpdate();
            rs=pstmt.getGeneratedKeys();
                if(rs.next()){
                    businessId=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return businessId;
    }
}
