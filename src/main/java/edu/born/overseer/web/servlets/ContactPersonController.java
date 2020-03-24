package edu.born.overseer.web.servlets;

import edu.born.overseer.web.AbstractContactPersonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = ContactPersonController.URL)
public class ContactPersonController extends AbstractContactPersonController {

    static final String URL = "/contactpersons";

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("persons", super.getAll());
        return "contactPersons";
    }

}
