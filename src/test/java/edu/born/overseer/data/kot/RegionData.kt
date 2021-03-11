package edu.born.overseer.data.kot

import edu.born.overseer.model.Region
import edu.born.overseer.model.abstraction.AbstractBaseEntity

const val REGION_1_ID = AbstractBaseEntity.START_SEQUENCE
const val REGION_2_ID = AbstractBaseEntity.START_SEQUENCE + 1
const val REGION_3_ID = AbstractBaseEntity.START_SEQUENCE + 2

val REGION_1 = Region(REGION_1_ID, "Алтайский край")
val REGION_2 = Region(REGION_2_ID, "Амурская область")
val REGION_3 = Region(REGION_3_ID, "Владимирская область")