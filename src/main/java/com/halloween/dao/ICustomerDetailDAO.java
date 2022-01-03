package com.halloween.dao;

import com.halloween.model.CustomerDetail;

public interface ICustomerDetailDAO extends GenericDAO<CustomerDetail>{
	public CustomerDetail fyByCusID(Integer customerID);
	public Integer insertCustomerDetail(CustomerDetail customer);
	public Boolean updateAddress(CustomerDetail customer);
	public CustomerDetail getInfoCustomer(Integer customerID);
}
