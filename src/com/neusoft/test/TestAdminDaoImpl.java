package com.neusoft.test;

import com.neusoft.domain.Admin;
import com.neusoft.dao.AdminDao;
import com.neusoft.dao.Impl.AdminDaoImpl;

import java.sql.SQLException;

public class TestAdminDaoImpl
{
    public static void main(String[] args) throws SQLException
    {
        AdminDao adminDao=new AdminDaoImpl();
        Admin admin=adminDao.getAdminByNameByPass("王磊","123");
        System.out.println(admin);
        //AdminDao adminDao1=new AdminDaoImpl();
        //Admin admin1=adminDao.save(5,"jiaozi","123");
    }
}
