package com.halloween.dao;

import com.halloween.model.CustomerDetail;

public interface ICustomerDetailDAO extends GenericDAO<CustomerDetail> {
    CustomerDetail fyByCusID(Integer customerID);

    Integer insertCustomerDetail(CustomerDetail customer);

    Boolean updateProfile(CustomerDetail customer);

    Boolean updateAddress(CustomerDetail customer);

    Boolean updateAddress(Integer customerID, String address);

    CustomerDetail getInfoCustomer(Integer customerID);
}
