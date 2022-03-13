package com.greatlearning.crmapp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.crmapp2.entity.Customer;
import com.greatlearning.crmapp2.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		System.out.println("request recieved");
		
		List<Customer> thecustomers = customerService.findAll();
		

		// add to the spring model
		theModel.addAttribute("Customers", thecustomers);

		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {

		Customer theCustomer = customerService.findbyId(theId);

		theModel.addAttribute("Customer", theCustomer);

		// send over to our form
		return "Customer-form";			
	}


	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if(id!=0)
		{
			theCustomer=customerService.findbyId(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		}
		else
			theCustomer=new Customer(firstName, lastName, email);
		
		customerService.save(theCustomer);


		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";

	}


	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		// delete the Book
		customerService.deletebyId(theId);

		// redirect to /Books/list
		return "redirect:/customer/list";

	}
	

}
