package edu.born.overseer.web.rest;

import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.OrderPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.OrderPaymentRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class OrderPaymentRestController {

    public static final String REST_URL = "/rest/orders/{orderId}/payments";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public OrderPayment create(@RequestBody OrderPayment payment,
                               @PathVariable int orderId) {
        checkNew(payment);
        int companyId = payment.getCompany().getId();
        int ourCompanyId = payment.getOurCompany().getId();
        log.info("create order payment {} for order {} by company {} by ourCompany {}", payment, orderId, companyId, ourCompanyId);
        return orderPaymentRepository.save(payment, orderId, companyId, ourCompanyId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody OrderPayment payment,
                       @PathVariable int id,
                       @PathVariable int orderId) {
        assureIdConsistent(payment, id);
        int companyId = payment.getCompany().getId();
        int ourCompanyId = payment.getOurCompany().getId();
        log.info("update order payment {} for order {} by company {} by ourCompany {}", payment, orderId, companyId, ourCompanyId);
        orderPaymentRepository.save(payment, orderId, companyId, ourCompanyId);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<OrderPayment> getAllByOrder(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "date", required = false) LocalDate date,
                                            @RequestParam(value = "order_id", required = false) Integer orderId) {
        log.info("get all order payments by employee {}", orderId);
        return orderPaymentRepository.getAll(page, date, orderId);
    }
}