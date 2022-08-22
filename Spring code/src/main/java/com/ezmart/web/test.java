package com.ezmart.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class test {

   
   @RequestMapping("/test.do")
   public String test(HttpServletRequest request, Model model) {
      System.out.println("접속 요청!");
      
      try {
         
         String data = request.getParameter("data");
         System.out.println("data : " + data);
         model.addAttribute("data", data);
         return "androidTest";
         
      } catch (Exception e) {
         
         e.printStackTrace();
         return "null";
         
      }
   }

}