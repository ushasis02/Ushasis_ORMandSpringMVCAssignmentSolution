package com.greatlearning.crmapp2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.crmapp2.entity.Customer;


@Service
public interface CustomerService {
	public List<Customer> findAll();
	public Customer findbyId(int id);
	public void save(Customer customer);
	public void deletebyId(int id);
}
