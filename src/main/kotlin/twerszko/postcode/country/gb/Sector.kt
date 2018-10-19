package twerszko.postcode.country.gb

import twerszko.postcode.country.gb.District.Companion.DISTRICT_PATTERN
import twerszko.postcode.util.RegexUtils.Companion.extractNamedGroup

data class Sector(override val value: String) : GBPostcodePart() {
    init {
        if (!isSector(value))
            throw IllegalArgumentException("Invalid GB postcode sector '$value'")
    }

    override fun normalized(): Sector {
        val district = extractNamedGroup(value, PATTERN, "district").toUpperCase()
        val sectorPart = extractNamedGroup(value, PATTERN, "sectorPart").toUpperCase()
        return Sector("$district$OUTWARD_INWARD_DELIMITER$sectorPart")
    }

    companion object {
        const val OUTWARD_INWARD_DELIMITER = " "
        const val SECTOR_PATTERN = "(?<sector>$DISTRICT_PATTERN($OUTWARD_INWARD_DELIMITER?(?<sectorPart>[0-9])))"
        private val PATTERN by lazy {
            "^$SECTOR_PATTERN${'$'}".toRegex()
        }

        fun isSector(value: String): Boolean {
            val matches = PATTERN.matches(value)
            if (matches) {
                val districtPart = extractNamedGroup(value, PATTERN, "districtPart")
                // Avoiding ambiguous sectors
                val spaceNeeded = !districtPart.matches("^([0-9][A-Za-z])|([0-9]{2})$".toRegex())
                if (spaceNeeded) {
                    return value.contains(OUTWARD_INWARD_DELIMITER)
                }
            }
            return matches
        }
    }
}