package com.neu.edu.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.edu.pojo.Dealer;

import org.springframework.validation.ValidationUtils;

public class DealerValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Dealer.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Dealer dealer = (Dealer) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.dealer", "First Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.dealer", "Last Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.dealer", "Customer Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cell","error.invalid.dealer", "Cell Number required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address","error.invalid.dealer", "Enter address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company","error.invalid.company", "Enter Company Name");
    }
}
