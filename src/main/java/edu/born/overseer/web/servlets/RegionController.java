package edu.born.overseer.web.servlets;

import edu.born.overseer.web.AbstractRegionController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = RegionController.URL)
public class RegionController extends AbstractRegionController {

    static final String URL = "/regions";

    @RequestMapping(value = "/switch/{swtch}", method = RequestMethod.GET)
    public String getAll(ModelMap model, @PathVariable(value = "swtch", required = false) String swtch) {
        model.addAttribute("regions", super.getAll());
        model.addAttribute("swtch", swtch);
        return "regions";
    }

}
