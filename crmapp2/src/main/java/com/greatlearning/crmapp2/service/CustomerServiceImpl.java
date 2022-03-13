package com.greatlearning.crmapp2.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.crmapp2.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {
	
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}
	@Transactional
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		// find all the records from the database table
		List<Customer> customers=session.createQuery("from Customer").list();
		tx.commit();
		return customers;
	}
	@Transactional
	public Customer findbyId(int id) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		Transaction tx = session.beginTransaction();
		customer = session.get(Customer.class, id);
		tx.commit();
		return customer;
	}
	@Transactional
	public void save(Customer thecustomer) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(thecustomer);
		tx.commit();
	}

	@Transactional
	public void deletebyId(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		// get transaction
		Customer customer= session.get(Customer.class, id);
		// delete record
		session.delete(customer);
		tx.commit();

	}
}
