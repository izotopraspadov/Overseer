package edu.born.overseer.data

import edu.born.overseer.data.CompanyData.COMPANY_1
import edu.born.overseer.data.CompanyData.COMPANY_2
import edu.born.overseer.data.CompanyData.COMPANY_3
import edu.born.overseer.data.OrderData.ORDER_1
import edu.born.overseer.data.OrderData.ORDER_3
import edu.born.overseer.model.Order
import edu.born.overseer.model.OrderPayment
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE
import java.math.BigDecimal.valueOf
import java.time.LocalDate.now
import java.time.LocalDate.of

const val ORDER_PAYMENT_1_ID = START_SEQUENCE + 52
const val ORDER_PAYMENT_2_ID = START_SEQUENCE + 53
const val ORDER_PAYMENT_3_ID = START_SEQUENCE + 54

val ORDER_PAYMENT_1 = OrderPayment(
        ORDER_PAYMENT_1_ID,
        of(2019, 9, 11),
        valueOf(100000.00),
        true,
        COMPANY_1,
        ORDER_1,
        COMPANY_1,
        "$$$"
)

val ORDER_PAYMENT_2 = OrderPayment(
        ORDER_PAYMENT_2_ID,
        of(2019, 9, 19),
        valueOf(100000.00),
        true,
        COMPANY_2,
        ORDER_3,
        COMPANY_1,
        null
)

val ORDER_PAYMENT_3 = OrderPayment(
        ORDER_PAYMENT_3_ID,
        of(2019, 9, 21),
        valueOf(30000.00),
        false,
        COMPANY_3,
        ORDER_1,
        COMPANY_1,
        "Done!"
)

val ORDER_1_PAYMENTS = setOf(ORDER_PAYMENT_1, ORDER_PAYMENT_3)
val ORDER_2_PAYMENTS = emptySet<OrderPayment>()
val ORDER_3_PAYMENTS = setOf(ORDER_PAYMENT_2)

fun getPreparedOrderPaymentCreateList(order: Order) = setOf(
        OrderPayment(now(), valueOf(300000.00), false, COMPANY_1, order, COMPANY_1, "big up")
)