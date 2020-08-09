package com.neusoft.test;

import com.neusoft.dao.Impl.BusinessDaoImpl;
import com.neusoft.domain.Admin;

public class TestBusiness
{
    public static void main(String[] args)
    {
        BusinessDaoImpl dao=new BusinessDaoImpl();
        dao.listBusiness(null,  null);
    }
}
