package edu.guap.enclave.web.servlets;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.Reliability;
import edu.guap.enclave.model.TypeCompany;
import edu.guap.enclave.repository.CompanyRepository;
import edu.guap.enclave.web.AbstractCompanyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@Controller
@RequestMapping(value = CompanyController.URL)
public class CompanyController extends AbstractCompanyController {

    static final String URL = "/companies";

    @RequestMapping(value = "/switch/{swtch}", method = RequestMethod.GET)
    public String getAll(ModelMap model, @PathVariable(value = "swtch", required = false) String swtch) {
        model.addAttribute("companies", super.getAll());
        model.addAttribute("swtch", swtch);
        return "companies";
    }

    @RequestMapping(value = "/region/{regionId}", method = RequestMethod.GET)
    public String getAllByRegion(ModelMap model, @PathVariable("regionId") int regionId) {
        model.addAttribute("companies", super.getAllByRegion(regionId));
        return "companies";
    }

    @RequestMapping(value = "/contactperson/{contactpersonId}", method = RequestMethod.GET)
    public String findByContactPerson(ModelMap model, @PathVariable("contactpersonId") int contactpersonId) {
        List<Company> companies = null;
        Optional<Company> opCompany = super.findByContactPerson(contactpersonId);
        if (opCompany.isPresent())
            companies = List.of(opCompany.get());
        model.addAttribute("companies", companies);
        return "companies";
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String getAllByToken(ModelMap model, @RequestParam() String data, @RequestParam() String category) {

        List<Company> companies = null;

        switch (category) {
            case "region":
                return "redirect:/regions/switch/cmp";
            case "person":
                return "redirect:/contactpersons";
            case "title":
                companies = super.getAllByTitle(data);
                break;
            case "type":
                Optional<TypeCompany> type = Arrays.stream(TypeCompany.values())
                        .filter(e -> e.getDescription().equalsIgnoreCase(data))
                        .findFirst();

                if (type.isPresent())
                    companies = super.getAllByType(type.get());
                break;
            case "address":
                companies = super.getAllAddress(data);
                break;
            case "itn":
                Optional<Company> opCompany = super.findByItb(data);
                if (opCompany.isPresent())
                    companies = List.of(opCompany.get());
                break;
            case "reliability":
                Optional<Reliability> reliability = Arrays.stream(Reliability.values())
                        .filter(e -> e.getDescription().equalsIgnoreCase(data))
                        .findFirst();
                if (reliability.isPresent())
                    companies = super.getAllByReliability(reliability.get());
                break;
        }

        model.addAttribute("companies", companies);

        return "companies";
    }

}
