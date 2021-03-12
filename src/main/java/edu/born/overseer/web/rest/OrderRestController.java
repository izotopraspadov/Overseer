package edu.born.overseer.web.rest;

import edu.born.overseer.model.Order;
import edu.born.overseer.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.OrderRestController.REST_URL;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class OrderRestController {

    public static final String REST_URL = "/rest/orders";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRepository orderRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Order create(@RequestBody Order order) {
        checkNew(order);
        int companyId = order.getCompany().getId();
        int groupId = order.getGroup().getId();
        int managerId = order.getManager().getId();
        int orderTypeId = order.getOrderType().getId();
        log.info("create order {} by company {} by group {} by manager {} by orderType {}", order, companyId, groupId, managerId, orderTypeId);
        return orderRepository.save(order, companyId, groupId, managerId, orderTypeId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Order order,
                       @PathVariable int id) {
        assureIdConsistent(order, id);
        int companyId = order.getCompany().getId();
        int groupId = order.getGroup().getId();
        int managerId = order.getManager().getId();
        int orderTypeId = order.getOrderType().getId();
        log.info("update order {} by company {} by group {} by manager {} by orderType {}", order, companyId, groupId, managerId, orderTypeId);
        orderRepository.save(order, companyId, groupId, managerId, orderTypeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete order {}", id);
        orderRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = OK)
    public Order getById(@PathVariable int id) {
        log.info("get order {}", id);
        return orderRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = OK)
    public List<Order> getAll(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "company_id", required = false) Integer companyId,
                              @RequestParam(value = "cashless", required = false) Boolean cashless,
                              @RequestParam(value = "group_id", required = false) Integer groupId,
                              @RequestParam(value = "contract_is_need", required = false) Boolean contractIsNeed,
                              @RequestParam(value = "contract_exists", required = false) Boolean contractExists,
                              @RequestParam(value = "planned_start_date", required = false) LocalDate plannedStartDate,
                              @RequestParam(value = "actual_start_date", required = false) LocalDate actualStartDate,
                              @RequestParam(value = "planned_end_date", required = false) LocalDate plannedEndDate,
                              @RequestParam(value = "actual_end_date", required = false) LocalDate actualEndDate,
                              @RequestParam(value = "current_sum", required = false) BigDecimal currentSum,
                              @RequestParam(value = "manager_id", required = false) Integer managerId,
                              @RequestParam(value = "underway", required = false) Boolean underway,
                              @RequestParam(value = "expected_payment", required = false) BigDecimal expectedPayment,
                              @RequestParam(value = "lines", required = false) Integer numberOfLines,
                              @RequestParam(value = "format", required = false) String format,
                              @RequestParam(value = "title", required = false) String title) {

        return orderRepository.getAll(
                page,
                companyId,
                cashless,
                groupId,
                contractIsNeed,
                contractExists,
                plannedStartDate,
                actualStartDate,
                plannedEndDate,
                actualEndDate,
                currentSum,
                managerId,
                underway,
                expectedPayment,
                numberOfLines,
                format,
                title
        );
    }
}