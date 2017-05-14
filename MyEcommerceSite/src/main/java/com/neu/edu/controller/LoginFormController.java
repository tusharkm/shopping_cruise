package com.neu.edu.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.neu.edu.dao.DealerDAO;
import com.neu.edu.dao.CustomerDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Dealer;
import com.neu.edu.pojo.Login;
import com.neu.edu.pojo.Customer;

/*@Scope("request")*/
@Controller
@RequestMapping("/login.htm")
public class LoginFormController {
@Autowired
@Qualifier("loginValidator")
LoginValidator validator;

@InitBinder
private void initBinder(WebDataBinder binder) {
binder.setValidator(validator);
}

@RequestMapping(method = RequestMethod.POST)
protected String doSubmitAction(@ModelAttribute("login") Login login, BindingResult result,HttpServletRequest request,HttpServletResponse response) throws Exception {
validator.validate(login, result);
if (result.hasErrors()) {
return "login";
}

//String value = request.getParameter("action");
//System.out.println("Action"+value ); 
String uri = request.getScheme() + "://" +
        request.getServerName() + 
        ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +
        request.getRequestURI() +
       (request.getQueryString() != null ? "?" + request.getQueryString() : "");
System.out.println("URL"+uri );
/*if (value.equals("login")) {
    HttpSession session = request.getSession();
    
    if (session.getAttribute("CustomerName") != null) {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/UserView.jsp");
        rd.forward(request, response);
    	return "CustomerLogin";}
    else if (session.getAttribute("DealerName") != null)
    	{
    		 return "DealerLogin";
    	}
}*/
    
    
try {
	if(request.getSession().getAttribute("customer")==null && request.getSession().getAttribute("dealer")==null)
	{
	System.out.print("test");
	CustomerDAO userDao = new CustomerDAO();
	DealerDAO dealerDao = new DealerDAO();
	
	Customer customerRole = userDao.search(login.getName(), login.getPassword());
	Dealer dealerRole = dealerDao.search(login.getName(), login.getPassword());
	
/*	if(userRole.equalsIgnoreCase("Customer")){
		return "userSuccessLoginPage";
	}else if(dealerRole.equalsIgnoreCase("dealer")){
		return "DealerRole";
	}else{
		return "loginErrorPage";
	}*/
	if(customerRole !=null && customerRole.getRoleRegistered().equalsIgnoreCase("Customer")){
		request.getSession().setAttribute("CustomerName",customerRole.getName());
		request.setAttribute("customer", customerRole);
		request.getSession().setAttribute("customer", customerRole);
		return "CustomerLogin";
	}else if(dealerRole !=null && dealerRole.getRoleRegistered().equalsIgnoreCase("Dealer")){
		request.getSession().setAttribute("DealerName",dealerRole.getName());
		request.setAttribute("dealer",dealerRole);// to add dealer to product table
		request.getSession().setAttribute("dealer", dealerRole);
		return "DealerLogin";
		
	}else{
		return "loginErrorPage";
	}
	}
	else{
		return "login";}
	// DAO.close();
} catch (AdException e) {
	System.out.println("Exception: " + e.getMessage());
}

return "home";
}


@RequestMapping(method = RequestMethod.GET)
public String initializeForm(@ModelAttribute("login") Login login, BindingResult result,HttpServletRequest request,HttpServletResponse response) {
	String cName= (String)request.getSession().getAttribute("CustomerName");
	String dName= (String)request.getSession().getAttribute("DealerName");
/*	if(cName!=null){
    return "CustomerLogin";
	}else if (dName!=null)
	{return "DealerLogin";}*/
	/*else */
		return "login";

}
}