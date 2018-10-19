package twerszko.postcode.country.gb

import twerszko.postcode.Postcode
import twerszko.postcode.PostcodePart
import twerszko.postcode.country.gb.Sector.Companion.OUTWARD_INWARD_DELIMITER
import twerszko.postcode.util.RegexUtils.Companion.extractNamedGroup

data class Unit(override val value: String) : Postcode, GBPostcodePart() {
    init {
        if (!isUnit(value))
            throw IllegalArgumentException("Invalid GB postcode unit '$value'")
    }

    val sector by lazy { Sector(extractNamedGroup(value, PATTERN, "sector")) }
    val district by lazy { District(extractNamedGroup(value, PATTERN, "district")) }
    val area by lazy { Area(extractNamedGroup(value, PATTERN, "area")) }
    val outwardCode by lazy { district.value }
    val inwardCode by lazy { extractNamedGroup(value, PATTERN, "sectorPart") + extractNamedGroup(value, PATTERN, "unitPart") }

    operator fun component2() = sector
    operator fun component3() = district
    operator fun component4() = area

    override fun normalized(): Unit {
        return Unit("${outwardCode.toUpperCase()}$OUTWARD_INWARD_DELIMITER${inwardCode.toUpperCase()}")
    }

    override fun parts(): List<PostcodePart> {
        return listOf(this, sector, district, area)
    }

    companion object {
        const val UNIT_PATTERN = "${Sector.SECTOR_PATTERN}(?<unitPart>[a-zA-Z]{2})"
        private val PATTERN by lazy {
            "^$UNIT_PATTERN${'$'}".toRegex()
        }

        fun isUnit(value: String): Boolean = PATTERN.matches(value)

        fun isValid(value: String): Boolean = isUnit(value)
    }
}