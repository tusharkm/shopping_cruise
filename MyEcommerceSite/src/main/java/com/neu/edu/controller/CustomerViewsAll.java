package com.neu.edu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.neu.edu.dao.CartDao;
import com.neu.edu.dao.CatDAO;
import com.neu.edu.dao.CustomerDAO;
import com.neu.edu.dao.ProdDAO;
import com.neu.edu.exception.AdException;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Customer;
import com.neu.edu.pojo.CustomerCart;
import com.neu.edu.pojo.Product;

@Controller
public class CustomerViewsAll {

	
	
	String catName="";
	
	@RequestMapping(value = "/ViewCat.htm", method = RequestMethod.GET)
 	public String initializeForm(@ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
if(isUserLoggedIn(request,response))
{ System.out.println("in sessionmanagment");
/*	
	try {
    request.getRequestDispatcher("redirect").forward(request, response);
} catch (ServletException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}*/
        HttpSession session = request.getSession();
Customer customer = (Customer)session.getAttribute("customer");// need to check
request.setAttribute("customer", customer);

        return "CustomerView";
    }
else {return "logout";}
}	
	
    @RequestMapping(value = "/ViewProdInCart.htm", method = RequestMethod.GET)
    public String initializeForm4(@ModelAttribute("product") Product product, BindingResult result,
            HttpServletRequest hsr, HttpServletResponse response) throws AdException {
    	if(isUserLoggedIn(hsr,response))
    	{  HttpSession session = hsr.getSession();
       long customerId  = (Long) session.getAttribute("customerId");
        CustomerDAO customerDao = new CustomerDAO();
        Customer customer = (Customer)customerDao.getId(customerId);
        System.out.println("enter ViewProdInCart.htm ");
        hsr.setAttribute("CustomerCart", customer.getProdlist());
        CartDao cartDao= new CartDao();
        ArrayList<CustomerCart> CustomerCartlist = new ArrayList<CustomerCart>();
        CustomerCartlist=cartDao.getCartItem(customer.getPersonID());
        hsr.setAttribute("CustomerCartlist", CustomerCartlist);
        
        
        return "CustomerViewsCartFinal";
    }
    
    
    else {return "logout";}
    }	
    
    @RequestMapping(value = "/ViewProd.htm", method = RequestMethod.GET)
    public String initializeForm1(@ModelAttribute("product") Product product, BindingResult result,
            HttpServletRequest hsr, HttpServletResponse response) {
    	if(isUserLoggedIn(hsr,response))
    	{
    	catName = hsr.getParameter("catName");
        System.out.println(hsr.getParameter("catName") + "cat name");
        // return "selectProduct";
        ProdDAO prodtDao = new ProdDAO();
        ArrayList<Product> prodSet = new ArrayList<Product>();
        try {
        	prodSet = prodtDao.get(catName);
            System.out.println("after get product");
        } catch (AdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hsr.setAttribute("prodSet", prodSet);
        return "CustomerView";
    }
    else{return "logout";}
}



    @RequestMapping(value = "/selectProd.htm", method = RequestMethod.GET)
    public String initializeForm2(@ModelAttribute("product") Product product, BindingResult result,
            HttpServletRequest hsr,HttpServletResponse response) throws AdException {
    	// logic for qty
    	//String qty[]=hsr.getParameterValues("valueFrmUser");
        String[] productListID= hsr.getParameterValues("prodId");
        if( hsr.getParameterValues("prodId")==null)
        {
        	return "SelectItem";
        	
        }
    	if(isUserLoggedIn(hsr,response))
    	{
    		double sum=0;
    	CartDao cartDao=new CartDao();
        HttpSession session = hsr.getSession();
        long customerId  = (Long) session.getAttribute("customerId");
        CustomerDAO customerDao = new CustomerDAO();
        Customer customer = (Customer)customerDao.getId(customerId);
        System.out.println("entered selectProd.htm ");
    
    
        Random rand = new Random();  
        int cart_id=rand.nextInt(1000) + 1;  
        for (String prodId : productListID) {
                System.out.println("product id of checked checkbox is: "+prodId);    
                System.out.println("product id of checked checkbox is: "+Long.parseLong(prodId));
                ProdDAO prodDao = new ProdDAO();
                try {
                    Product prod = (Product)prodDao.getId(Long.parseLong(prodId));
                System.out.println("reached  : before user.getProductsCart().add(prod)");
                System.out.println("get something from user "+customer.getFirstName());
                customer.getProdlist().add(prod);
              
                    System.out.println(customer.getProdlist().add(prod));
                    customerDao.save(customer);
               int q=Integer.parseInt(hsr.getParameter(prodId));
               prod.setQty(prod.getQty()-q); 
               prodDao.updateQty(prod);
               System.out.println("Value of Q "+q);
                    cartDao.create(q, prod, customer,cart_id);
                 	// logic for qty
                    sum=sum+(q*prod.getPrice());
                 
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (AdException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               
                
                
                
        }
        ArrayList<CustomerCart> CustomerCartList= new ArrayList<CustomerCart>();
        CustomerCartList=cartDao.getCartItembyId(cart_id);
        hsr.setAttribute("sum", sum);
        hsr.setAttribute("CustomerCart", customer.getProdlist());
        hsr.setAttribute("CustomerCartinprod", CustomerCartList);
        hsr.setAttribute("cart_id", cart_id);
        hsr.getSession().setAttribute("cart_idCK", cart_id);
        return "CustomerViewsCart";
    }
        else{return "logout";}
    }

    @RequestMapping(value = "/chkout.htm", method = RequestMethod.POST)
    public String initializeForm5(@ModelAttribute("product") Product product, BindingResult result,
            HttpServletRequest hsr,HttpServletResponse response) throws AdException {
    	if(isUserLoggedIn(hsr,response))
    	{
    	CartDao cartDao=new CartDao();
    	int id=(Integer)hsr.getSession().getAttribute("cart_idCK");
    	cartDao.updateStatus(id);
    	  hsr.getSession().invalidate();
    	return "Customerchkout";
    }
    	  else{return "logout";}
    }
    private boolean isUserLoggedIn(HttpServletRequest request, HttpServletResponse response){
        
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("customer")!= null){
            
            /*Customer personId = (Customer)httpSession.getAttribute("customer");
            if(personId !=null){
            */    return true;
            
        }
        
        return false;
    }
/*    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        
         return "redirect:index";
    }*/
}
