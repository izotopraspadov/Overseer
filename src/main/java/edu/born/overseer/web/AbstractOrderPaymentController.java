package edu.born.overseer.web;

import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.OrderPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractOrderPaymentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    public OrderPayment create(OrderPayment payment, int orderId, int companyId, int ourCompanyId) {
        log.info("create order payment {} for order {} by company {} by ourCompany {}", payment, orderId, companyId, ourCompanyId);
        return orderPaymentRepository.save(payment, orderId, companyId, ourCompanyId);
    }

    public OrderPayment update(OrderPayment payment, int id, int orderId, int companyId, int ourCompanyId) {
        log.info("update order payment {} for order {} by company {} by ourCompany {}", payment, orderId, companyId, ourCompanyId);
        return orderPaymentRepository.save(payment, orderId, companyId, ourCompanyId);
    }

    public List<OrderPayment> getAll() {
        log.info("get all order payments");
        return orderPaymentRepository.getAll();
    }

    public List<OrderPayment> getAllByDate(LocalDate date) {
        log.info("get all order payments by date {}", date);
        return orderPaymentRepository.getAllByDate(date);
    }

    public List<OrderPayment> getAllByOrder(int orderId) {
        log.info("get all order payments by employee {}", orderId);
        return orderPaymentRepository.getAllByOrder(orderId);
    }

}
