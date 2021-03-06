package edu.born.overseer.repository.impl

import edu.born.overseer.data.INVALID_PAYMENT_FORMAT
import edu.born.overseer.data.OrderData.ORDER_1
import edu.born.overseer.data.getPreparedOrderCreate
import edu.born.overseer.model.Order
import edu.born.overseer.repository.OrderRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.TransactionSystemException
import java.math.BigDecimal.valueOf

internal class OrderRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @BeforeEach
    fun setUp() {
        orderRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedOrderCreate()

        val companyId = prepared.company.id
        val groupId = prepared.group.id
        val managerId = prepared.manager.id
        val orderTypeId = prepared.orderType.id

        val savedId = orderRepository.save(prepared, companyId, groupId, managerId, orderTypeId).id
        val received = orderRepository.getById(savedId)

        assertEquals(received, prepared)
    }

    @Test
    fun createWithInvalidPaymentFormat() {
        val prepared = getPreparedOrderCreate().apply {
            paymentFormat = INVALID_PAYMENT_FORMAT
        }

        val companyId = prepared.company.id
        val groupId = prepared.group.id
        val managerId = prepared.manager.id
        val orderTypeId = prepared.orderType.id

        assertThrows(TransactionSystemException::class.java) {
            orderRepository.save(prepared, companyId, groupId, managerId, orderTypeId)
        }
    }

    @Test
    fun update() {
        val prepared = Order(ORDER_1).apply {
            title = "Updated Project"
            sum = valueOf(1000000.00)
            expectedPayment = valueOf(250000.00)
        }

        val companyId = prepared.company.id
        val groupId = prepared.group.id
        val managerId = prepared.manager.id
        val orderTypeId = prepared.orderType.id

        val updated = orderRepository.save(prepared, companyId, groupId, managerId, orderTypeId)

        assertEquals(updated, prepared)
    }
}