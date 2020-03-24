package edu.born.overseer.web.servlets;

import edu.born.overseer.web.AbstractTimeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = TimeController.URL)
public class TimeController extends AbstractTimeController {

    static final String URL = "/time";

    @RequestMapping(value = "/actual/object/{id}", method = RequestMethod.GET)
    public String getAllActualTimeByOrderedObject(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("actualTimeList", super.getAllActualTimeByOrderedObject(id));
        return "actualTime";
    }

    @RequestMapping(value = "/planned/object/{id}", method = RequestMethod.GET)
    public String getAllPlannedTimeByOrderedObject(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("plannedTimeList", super.getAllPlannedTimeByOrderedObject(id));
        return "plannedTime";
    }

}