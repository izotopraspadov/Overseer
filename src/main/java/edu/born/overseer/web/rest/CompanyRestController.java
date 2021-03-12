package edu.born.overseer.web.rest;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.CompanyRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class CompanyRestController {

    public static final String REST_URL = "/rest/companies";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyRepository companyRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Company create(@RequestBody Company company) {
        checkNew(company);
        int regionId = company.getRegion().getId();
        log.info("create company {} by region {}", company, regionId);
        return companyRepository.save(company, regionId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Company company,
                       @PathVariable int id) {
        assureIdConsistent(company, id);
        int regionId = company.getRegion().getId();
        log.info("create company {} by region {}", company, regionId);
        companyRepository.save(company, regionId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete company {}", id);
        companyRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Company getById(@PathVariable int id) {
        log.info("get company {}", id);
        return companyRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Company> getAll(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "contact_person_id", required = false) Integer contactPersonId,
                                @RequestParam(value = "region_id", required = false) Integer regionId,
                                @RequestParam(value = "reliability", required = false) ReliabilityType reliabilityType,
                                @RequestParam(value = "type", required = false) CompanyType type,
                                @RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "address", required = false) String address,
                                @RequestParam(value = "itn", required = false) String itn) {

        return companyRepository.getAll(
                page,
                contactPersonId,
                regionId,
                reliabilityType,
                type,
                title,
                address,
                itn
        );
    }
}