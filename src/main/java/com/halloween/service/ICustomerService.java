package com.halloween.service;

import com.halloween.model.Customer;

import java.util.List;

public interface ICustomerService {
    Integer getCustomerID(String username);

    Customer getCustomer(Integer customerID);

    Integer insertCustomer(Customer customer);

    Integer checkLogin(String userName, String password);

    Boolean isAdmin(Integer customerID);

    List<String> getListProvince(String key);

    List<String> getListDistrict(String province, String key);

    List<String> getListWard(String district,String key);

}
