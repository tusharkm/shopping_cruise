package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.edu.dao.CatDAO;
import com.neu.edu.dao.CustomerDAO;
import com.neu.edu.dao.DealerDAO;
import com.neu.edu.dao.ProdDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Customer;


@Controller
@RequestMapping("/CustomerAdd.htm")
@Scope("request")
public class AddCustomerFormController {
	@Autowired
	@Qualifier("CustomerValidator")
	CustomerValidator validator;


	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("customer") Customer customer, BindingResult result, @RequestParam("name") String Username,HttpServletRequest hsr) throws Exception {
		validator.validate(customer, result);
		if (result.hasErrors()) {
			return "CustomerAdd";
		}

		try {
			System.out.print("Inside dao");
			hsr.getSession().invalidate();
			CustomerDAO customerDao = new CustomerDAO();
			DealerDAO dealerDao = new DealerDAO();
			CatDAO catDao=new CatDAO();
			ProdDAO prodDAO= new ProdDAO();
			 java.util.List catList = catDao.list();
			  java.util.List prodList = prodDAO.list();
			
			  if(customerDao.get(customer.getName())==null && dealerDao.get(customer.getName())==null && catList.size()!=0 && prodList.size()!=0)
					{
			customerDao.create(customer.getName(), customer.getPassword(), customer.getEmail().getEmailId(), customer.getFirstName(), customer.getLastName(),customer.getCell(),customer.getAddress());
			System.out.print("dao created");
					}
			else
				return "UserExsists";
			// DAO.close();
		} catch (AdException e) {
			
			System.out.println("Exception: " + e.getMessage());
		
		}
	
		//hsr.getSession().setAttribute("CustomerName",customer.getName());
		//hsr.setAttribute("customer",customer);
		hsr.getSession().invalidate();
		return "CustomerAdded";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("customer") Customer customer, BindingResult result, HttpServletRequest hsr) {
		
		return "CustomerAdd";
	}
}