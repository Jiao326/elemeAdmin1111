package com.neusoft.test;

import com.neusoft.dao.BusinessDao;
import com.neusoft.dao.Impl.BusinessDaoImpl;

public class TestDelete
{
    public static void main(String[] args)
    {
        BusinessDao dao =new BusinessDaoImpl();
        dao.deleteBusiness(10008);
    }

}
