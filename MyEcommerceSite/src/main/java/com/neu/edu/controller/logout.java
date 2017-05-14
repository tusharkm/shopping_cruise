package com.neu.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.pojo.Login;

@Controller
@RequestMapping("/logout.htm")
public class logout {

    
    @RequestMapping(method = RequestMethod.GET)
      public String handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception{
        hsr.getSession().invalidate();
    return "logout";
    }
    }