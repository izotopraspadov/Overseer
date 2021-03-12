package edu.born.overseer.data

import edu.born.overseer.model.Group
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val GROUP_1_ID = START_SEQUENCE + 46
const val GROUP_2_ID = START_SEQUENCE + 47
const val GROUP_3_ID = START_SEQUENCE + 48

val GROUP_1 = Group(GROUP_1_ID, "001", setOf(ORDER_TYPE_1, ORDER_TYPE_2), "Need to get the result! Now!")
val GROUP_2 = Group(GROUP_2_ID, "002", setOf(ORDER_TYPE_3), null)
val GROUP_3 = Group(GROUP_3_ID, "003", setOf(ORDER_TYPE_4), "Good luck...")

fun getPreparedGroupCreate() = Group("New group", setOf(ORDER_TYPE_1), null)

/*
fun getPreparedDuplicateCreate(): Group? {
    return edu.born.overseer.data.kot.getPreparedCreate()
            .title(GroupTestData.GROUP_1.title) // duplicate
}

fun edu.born.overseer.data.kot.getPreparedUpdate(): Group? {
    return Group(GroupTestData.GROUP_1)
            .comment("New Comment!") // update
}
*/
