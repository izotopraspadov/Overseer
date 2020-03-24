package edu.born.overseer.web.servlets;

import edu.born.overseer.web.AbstractGroupController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = GroupController.URL)
public class GroupController extends AbstractGroupController {

    static final String URL = "/groups";

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("groups", super.getAll());
        return "groups";
    }

}
