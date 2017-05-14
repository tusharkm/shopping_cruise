package com.neu.edu.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.neu.edu.dao.CatDAO;
import com.neu.edu.dao.ProdDAO;
import com.neu.edu.dao.CustomerDAO;
import com.neu.edu.dao.DealerDAO;
import com.neu.edu.pojo.Category;
import com.neu.edu.pojo.Dealer;
import com.neu.edu.pojo.Product;


@Scope("request")
@Controller
@RequestMapping("/addproduct.htm")
public class AddProdFormController {

	
	
	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("product")Product product,HttpServletRequest hsr,BindingResult result) throws Exception{

 	System.out.println("product controller reached");
		String productName = product.getProductName();   //get posting user from addAdvertForm
		String productDescription = product.getProductDescription();   //get category user from addAdvertForm
		String productImage = product.getProductImage();      //get advert title user from addAdvertForm
		//Category cat = product.getCategory();    //get user message from addAdvertForm
		String categoryName = product.getCategoryName();
		float price=product.getPrice();
		int qty=product.getQty();
		
		// logic for photo
		/* File file=null;
	       String check = File.separator; //Checking if system is linux based or windows based by checking seprator used.
	       String path = null;*/
	      // if(product.getPhoto()!=null)
	       //{
/*	    	   System.out.println("inside file loop");
	       String filenamewithext= System.currentTimeMillis()+ product.getPhoto().getOriginalFilename();
	       file =new File(path+filenamewithext);
	       
	       */
	       //}
		
		//long categoryId  = 	product.getCategory().getCategoryId();
		
        try {
            //CustomerDAO users = new CustomerDAO();
            CatDAO categories = new CatDAO();
            ProdDAO products = new ProdDAO();
           DealerDAO dealerDao= new DealerDAO();
            //searhing from database
            //Customer user = users.get(username);

            //searching from database
            Category category = categories.get(categoryName);

            //insert a new advert
            System.out.println("Product going to create "+productName);
            Product prod = products.create(productName,  productImage,  productDescription,  category.getCategoryId(), category.getCategoryName(),price,qty);
            System.out.println("Product created "+productName);
            //System.out.println("Product file "+file);
            category.addProduct(prod);
            categories.save(category);
            long dealerid=(Long)hsr.getSession().getAttribute("dealerid");
            System.out.println("***dealerid is : ****************** "+dealerid);
          // System.out.println("Dealer details "+dealer.getFirstName());
         // System.out.println("Dealer details "+dealer.getPersonID());
           Dealer dealer2 = dealerDao.getId(dealerid);
            
            System.out.println("Dealer details2 "+dealer2.getFirstName());
            System.out.println("*********************************** ");
            System.out.println("Dealer details "+dealer2.getFirstName());
            
            dealer2.getProductset().add(prod);
             dealerDao.save(dealer2);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("before return addedProduct");
        return "DealerAddedProduct";
    }
    
	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("product")Product product, BindingResult result,HttpServletRequest hsr) { 
		Dealer dealer=(Dealer)hsr.getSession().getAttribute("dealer");
		hsr.setAttribute("dealer",dealer);
	   //  System.out.println("Dealer details "+dealer.getPersonID());
        return "DealerCreateProduct"; 
    } 
}