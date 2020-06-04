package edu.born.overseer.web.rest;

import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.OrderPaymentRepository;
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
@RequestMapping(value = OrderPaymentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderPaymentRestController {

    public static final String REST_URL = "/rest/orders/{orderId}/payments";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OrderPaymentRepository orderPaymentRepository;

    public OrderPaymentRestController(OrderPaymentRepository orderPaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderPayment create(@RequestBody OrderPayment payment,
                               @PathVariable int orderId) {
        checkNew(payment);
        int companyId = payment.getCompany().getId();
        int ourCompanyId = payment.getOurCompany().getId();
        log.info("create order payment {} for order {} by company {} by ourCompany {}", payment, orderId, companyId, ourCompanyId);
        return orderPaymentRepository.save(payment, orderId, companyId, ourCompanyId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody OrderPayment payment,
                       @PathVariable int id,
                       @PathVariable int orderId) {
        assureIdConsistent(payment, id);
        int companyId = payment.getCompany().getId();
        int ourCompanyId = payment.getOurCompany().getId();
        log.info("update order payment {} for order {} by company {} by ourCompany {}", payment, orderId, companyId, ourCompanyId);
        orderPaymentRepository.save(payment, orderId, companyId, ourCompanyId);
    }

    @GetMapping(params = {"page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderPayment> getAllByOrder(@PathVariable int orderId,
                                            @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all order payments by employee {}", orderId);
        return orderPaymentRepository.getAllByOrder(orderId, getFirstByPage(page));
    }

}
