package com.ajith.joblisting.JobListing.aop;


import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringAop {

    @AfterReturning("execution(public * com.ajith.joblisting.JobListing.controller.JobListingController.getAllInfo())")
    public void log(){

        System.out.println("GetAllInfo Method called");

    }


}
