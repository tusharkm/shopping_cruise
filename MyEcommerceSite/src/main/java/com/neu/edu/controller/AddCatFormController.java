package com.neu.edu.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.dao.CatDAO;
import com.neu.edu.pojo.Category;



@Controller
@RequestMapping("/CatAdd.htm")
public class AddCatFormController
{
	
	
	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("category")Category category,BindingResult result) throws Exception
    {
		
		
        
        try
        {
            CatDAO catDAO = new CatDAO(); //Create Category Dao for database
            catDAO.create(category.getCategoryName());   //Create Category name
           
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "CatAddedForm";
    }
    
	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("category")Category category, BindingResult result) { 
   
        return "CatAddForm"; 
    } 
}