package com.halloween.service;

import java.util.List;

import com.halloween.model.Customer;

public interface ICustomerService {
    Integer getCustomerID(String username);

    Customer getCustomer(Integer customerID);

    Integer insertCustomer(Customer customer);

    Integer checkLogin(String userName, String password);

    Boolean isAdmin(Integer customerID);

    List<String> getListProvince();

    List<String> getListDistrict(String province);

    List<String> getListWard(String district);
}
