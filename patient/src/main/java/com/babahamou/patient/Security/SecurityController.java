package com.babahamou.patient.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
    @GetMapping("/notAuthorized")
    public  String error(){
        return "notAuthorized";
    }

    @GetMapping(path="/login")
    public  String login(){
        return "login";
    }
    @GetMapping(path="/logout")
    public  String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }
}
