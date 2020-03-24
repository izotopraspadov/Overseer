package edu.born.overseer.web.servlets;

import edu.born.overseer.web.AbstractTaskController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = TaskController.URL)
public class TaskController extends AbstractTaskController {

    static final String URL = "/tasks";

    @RequestMapping(value = "/object/{id}", method = RequestMethod.GET)
    public String getAllByEmployee(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("tasks", super.getAllByOrderedObject(id));
        return "tasks";
    }

}