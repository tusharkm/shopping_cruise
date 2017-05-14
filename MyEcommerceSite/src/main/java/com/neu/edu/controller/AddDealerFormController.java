package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.neu.edu.controller.DealerValidator;
import com.neu.edu.dao.CatDAO;
import com.neu.edu.dao.CustomerDAO;
import com.neu.edu.dao.DealerDAO;
import com.neu.edu.dao.ProdDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Dealer;


@Controller
@RequestMapping("/DealerAdded.htm")
@Scope("request")
public class AddDealerFormController {
	@Autowired
	@Qualifier("dealerValidator")
	DealerValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator); // validate the dealer
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("dealer")Dealer dealer, BindingResult result, HttpServletRequest hsr) throws Exception {
		validator.validate(dealer, result);
		if (result.hasErrors()) {
			return "DealerAddForm";
		}

		try {
			
			System.out.print("Inside dealer");
			hsr.getSession().invalidate();
			DealerDAO dealerDao = new DealerDAO();
			CustomerDAO customerDao = new CustomerDAO();
			CatDAO catDao=new CatDAO();
			ProdDAO prodDAO= new ProdDAO();
			 java.util.List catList = catDao.list();
			//  java.util.List prodList = prodDAO.list();
			if((dealerDao.get(dealer.getName())==null) &&(customerDao.get(dealer.getName())==null) &&( catList.size()!=0))
			{
			System.out.print("Dealer Created");
			
			dealerDao.create(dealer.getName(), dealer.getPassword(), dealer.getEmail(), dealer.getFirstName(), dealer.getLastName(),dealer.getCell(),dealer.getAddress(),dealer.getCompany());
			}
			else
				return "UserExsists";
		
		} catch (AdException e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
		//hsr.getSession().setAttribute("DealerName",dealer.getName());
		//hsr.setAttribute("dealer",dealer);
		//hsr.getSession().setAttribute("DealerId",dealer.getPersonID());
		hsr.getSession().invalidate();
		return "DealerAdded";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("dealer") Dealer dealer, BindingResult result, HttpServletRequest hsr) {
		
		return "DealerAddForm";
	}
}