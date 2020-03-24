package edu.born.overseer.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model){
        return "redirect:/employees";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model){
        return "login";
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        return "index";
    }

}
