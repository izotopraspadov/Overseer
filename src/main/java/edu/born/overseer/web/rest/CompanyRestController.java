package edu.born.overseer.web.rest;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.util.PageUtil.getFirstByPage;

@RestController
@RequestMapping(value = CompanyRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyRestController {

    public static final String REST_URL = "/rest/companies";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CompanyRepository companyRepository;

    public CompanyRestController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Company create(@RequestBody Company company) {
        checkNew(company);
        int regionId = company.getRegion().getId();
        log.info("create company {} by region {}", company, regionId);
        return companyRepository.save(company, regionId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Company company,
                       @PathVariable int id) {
        assureIdConsistent(company, id);
        int regionId = company.getRegion().getId();
        log.info("create company {} by region {}", company, regionId);
        companyRepository.save(company, regionId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete company {}", id);
        return companyRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Company getById(@PathVariable int id) {
        log.info("get company {}", id);
        return companyRepository.getById(id);
    }

    @GetMapping(params = {"contact_person_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Company getByContactPersonId(@RequestParam("contact_person_id") int contactPersonId) {
        log.info("get company by contact person {}", contactPersonId);
        return companyRepository.getByContactPersonId(contactPersonId);
    }

    @GetMapping(params = {"page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAll(@RequestParam(value = "page", required = false) Integer page) {
        log.info("get all companies");
        return companyRepository.getAll(getFirstByPage(page));
    }

    @GetMapping(params = {"region_id", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAllByRegion(@RequestParam("region_id") int regionId,
                                        @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all companies by region {}", regionId);
        return companyRepository.getAllByRegion(regionId, getFirstByPage(page));
    }

    @GetMapping(params = {"reliability", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAllByReliability(@RequestParam("reliability") ReliabilityType reliabilityType,
                                             @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all companies by reliability {}", reliabilityType);
        return companyRepository.getAllByReliability(reliabilityType, getFirstByPage(page));
    }

    @GetMapping(params = {"type", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAllByType(@RequestParam("type") CompanyType type,
                                      @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all companies by type company {}", type);
        return companyRepository.getAllByType(type, getFirstByPage(page));
    }

    @GetMapping(params = {"title", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAllByTitle(@RequestParam("title") String title,
                                       @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all companies by title {}", title);
        return companyRepository.getAllByTitle(title, getFirstByPage(page));
    }

    @GetMapping(params = {"address", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAllByAddress(@RequestParam("address") String address,
                                         @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all companies by address {}", address);
        return companyRepository.getAllByAddress(address, getFirstByPage(page));
    }

    @GetMapping(params = {"itn", "page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getAllByItb(@RequestParam("itn") String itn,
                                     @RequestParam(value = "page", required = false) Integer page) {
        log.info("get company by itn {}", itn);
        return companyRepository.getAllByItb(itn, getFirstByPage(page));
    }

}
