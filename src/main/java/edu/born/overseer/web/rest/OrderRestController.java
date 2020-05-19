package edu.born.overseer.web.rest;

import edu.born.overseer.model.Order;
import edu.born.overseer.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = OrderRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    public static final String REST_URL = "/rest/orders";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Order create(@RequestBody Order order) {
        int companyId = order.getCompany().getId();
        int groupId = order.getCompany().getId();
        int managerId = order.getCompany().getId();
        int orderTypeId = order.getCompany().getId();
        log.info("create order {} by company {} by group {} by manager {} by orderType {}", order, companyId, groupId, managerId, orderTypeId);
        return orderRepository.save(order, companyId, groupId, managerId, orderTypeId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Order order, @PathVariable int id) {
        int companyId = order.getCompany().getId();
        int groupId = order.getCompany().getId();
        int managerId = order.getCompany().getId();
        int orderTypeId = order.getCompany().getId();
        log.info("update order {} by company {} by group {} by manager {} by orderType {}", order, companyId, groupId, managerId, orderTypeId);
        orderRepository.save(order, companyId, groupId, managerId, orderTypeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete order {}", id);
        return orderRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getById(@PathVariable int id) {
        log.info("get order {}", id);
        return orderRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAll() {
        log.info("get all orders");
        return orderRepository.getAll();
    }

    @GetMapping(params = {"company_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByCompany(@RequestParam("company_id") int companyId) {
        log.info("get all orders by company {}", companyId);
        return orderRepository.getAllByCompany(companyId);
    }

    @GetMapping(params = {"cashless"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByCashless(@RequestParam("cashless") boolean cashless) {
        log.info("get all orders by cashless {}", cashless);
        return orderRepository.getAllByCashless(cashless);
    }

    @GetMapping(params = {"group_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByGroup(@RequestParam("group_id") int groupId) {
        log.info("get all orders by group {}", groupId);
        return orderRepository.getAllByGroup(groupId);
    }

    @GetMapping(params = {"contract_is_need"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByContractIsNeed(@RequestParam("contract_is_need") boolean contractIsNeed) {
        log.info("get all orders by contractIsNeed {}", contractIsNeed);
        return orderRepository.getAllByContractIsNeed(contractIsNeed);
    }

    @GetMapping(params = {"contract_exists"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByContractExists(@RequestParam("contract_exists") boolean contractExists) {
        log.info("get all orders by contractExists {}", contractExists);
        return orderRepository.getAllByContractExists(contractExists);
    }

    @GetMapping(params = {"planned_start_date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByPlannedStartDate(@RequestParam("planned_start_date") LocalDate plannedStartDate) {
        log.info("get all orders by plannedStartDate {}", plannedStartDate);
        return orderRepository.getAllByPlannedStartDate(plannedStartDate);
    }

    @GetMapping(params = {"actual_start_date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByActualStartDate(@RequestParam("actual_start_date") LocalDate actualStartDate) {
        log.info("get all orders by actualStartDate {}", actualStartDate);
        return orderRepository.getAllByActualStartDate(actualStartDate);
    }

    @GetMapping(params = {"planned_endDate"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByPlannedEndDate(@RequestParam("planned_endDate") LocalDate plannedEndDate) {
        log.info("get all orders by plannedEndDate {}", plannedEndDate);
        return orderRepository.getAllByPlannedEndDate(plannedEndDate);
    }

    @GetMapping(params = {"actual_end_date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByActualEndDate(@RequestParam("actual_end_date") LocalDate actualEndDate) {
        log.info("get all orders by actualEndDate {}", actualEndDate);
        return orderRepository.getAllByActualEndDate(actualEndDate);
    }

    @GetMapping(params = {"current_sum"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllBySum(@RequestParam("current_sum") BigDecimal currentSum) {
        log.info("get all orders by currentSum {}", currentSum);
        return orderRepository.getAllBySum(currentSum);
    }

    @GetMapping(params = {"manager_id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByManager(@RequestParam("manager_id") int managerId) {
        log.info("get all orders by managerId {}", managerId);
        return orderRepository.getAllByManager(managerId);
    }

    @GetMapping(params = {"underway"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByUnderway(@RequestParam("underway") boolean underway) {
        log.info("get all orders by underway {}", underway);
        return orderRepository.getAllByUnderway(underway);
    }

    @GetMapping(params = {"expected_payment"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByExpectedPayment(@RequestParam("expected_payment") BigDecimal expectedPayment) {
        log.info("get all orders by expectedPayment {}", expectedPayment);
        return orderRepository.getAllByExpectedPayment(expectedPayment);
    }

    @GetMapping(params = {"lines"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByNumberOfLines(@RequestParam("lines") int lines) {
        log.info("get all orders by numberOfLines {}", lines);
        return orderRepository.getAllByNumberOfLines(lines);
    }

    @GetMapping(params = {"format"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByPaymentFormat(@RequestParam("format") String format) {
        log.info("get all orders by PaymentFormat {}", format);
        return orderRepository.getAllByPaymentFormat(format);
    }

    @GetMapping(params = {"title"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByTitle(@RequestParam("title") String title) {
        log.info("get all orders by title {}", title);
        return orderRepository.getAllByTitle(title);
    }

    @GetMapping(params = {"order_type"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllByOrderType(@RequestParam("order_type") String orderType) {
        log.info("get all orders by orderType {}", orderType);
        return orderRepository.getAllByOrderType(orderType);
    }

}
