package edu.born.overseer.data

import edu.born.overseer.model.OrderType
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val ORDER_TYPE_1_ID = START_SEQUENCE + 42
const val ORDER_TYPE_2_ID = START_SEQUENCE + 43
const val ORDER_TYPE_3_ID = START_SEQUENCE + 44
const val ORDER_TYPE_4_ID = START_SEQUENCE + 45

val ORDER_TYPE_1 = OrderType(ORDER_TYPE_1_ID, "Project")
val ORDER_TYPE_2 = OrderType(ORDER_TYPE_2_ID, "Executive documentation")
val ORDER_TYPE_3 = OrderType(ORDER_TYPE_3_ID, "Estimate")
val ORDER_TYPE_4 = OrderType(ORDER_TYPE_4_ID, "Legal services")