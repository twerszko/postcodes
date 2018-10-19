package twerszko.postcode.country.gb

import twerszko.postcode.country.gb.Area.Companion.AREA_PATTERN

data class District(override val value: String) : GBPostcodePart() {
    init {
        if (!isDistrict(value))
            throw IllegalArgumentException("Invalid GB postcode district '$value'")
    }

    override fun normalized(): District {
        return District(value.toUpperCase())
    }

    companion object {
        const val DISTRICT_PATTERN = "(?<district>$AREA_PATTERN(?<districtPart>[0-9][A-Za-z0-9]?))"
        private val PATTERN by lazy {
            "^$DISTRICT_PATTERN${'$'}".toRegex()
        }

        fun isDistrict(value: String): Boolean = PATTERN.matches(value)
    }
}