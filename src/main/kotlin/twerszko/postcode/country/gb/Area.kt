package twerszko.postcode.country.gb

import twerszko.postcode.PostcodePart

data class Area(override val value : String) : GBPostcodePart() {
    init {
        if(!isArea(value))
            throw IllegalArgumentException("Invalid GB postcode area '$value'")
    }

    override fun normalized(): Area {
        return Area(value.toUpperCase())
    }

    companion object {
        const val AREA_PATTERN : String = "(?<area>[A-Za-z][A-Ha-hJ-Yj-y]?)"
        private val PATTERN by lazy {
            "^$AREA_PATTERN${'$'}".toRegex()
        }

        fun isArea(value : String) : Boolean = PATTERN.matches(value)
    }
}