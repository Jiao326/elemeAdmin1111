package com.neusoft.dao;

import com.neusoft.domain.Business;

import java.util.List;

public interface BusinessDao
{
    //显示所有商家 可选输入商家businessName
    public List<Business>listBusiness(String businessName, String businessAddress);
    public int saveBusiness(String businessName);
    public int deleteBusiness(Integer businessId);
}
