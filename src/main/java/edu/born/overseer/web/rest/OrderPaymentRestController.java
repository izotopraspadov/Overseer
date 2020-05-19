package edu.born.overseer.web.rest;

import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.web.AbstractOrderPaymentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = OrderPaymentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderPaymentRestController extends AbstractOrderPaymentController {

    public static final String REST_URL = "/rest/orders/{orderId}/payments";

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderPayment create(@RequestBody OrderPayment payment, @PathVariable int orderId) {
        return super.create(payment,
                orderId,
                payment.getCompany().getId(),
                payment.getOurCompany().getId());
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody OrderPayment payment, @PathVariable int id, @PathVariable int orderId) {
        super.update(payment,
                id,
                orderId,
                payment.getCompany().getId(),
                payment.getOurCompany().getId());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderPayment> getAllByOrder(@PathVariable int orderId) {
        return super.getAllByOrder(orderId);
    }

}
