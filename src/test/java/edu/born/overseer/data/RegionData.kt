package edu.born.overseer.data

import edu.born.overseer.model.Region
import edu.born.overseer.model.abstraction.AbstractBaseEntity.START_SEQUENCE

const val REGION_1_ID = START_SEQUENCE
const val REGION_2_ID = START_SEQUENCE + 1
const val REGION_3_ID = START_SEQUENCE + 2

val REGION_1 = Region(REGION_1_ID, "Vladimirskaya oblast")
val REGION_2 = Region(REGION_2_ID, "Moscow")
val REGION_3 = Region(REGION_3_ID, "St. Petersburg")