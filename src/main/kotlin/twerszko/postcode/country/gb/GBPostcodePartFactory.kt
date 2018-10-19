package twerszko.postcode.country.gb

import twerszko.postcode.PostcodePart
import twerszko.postcode.PostcodePartFactory
import twerszko.postcode.country.gb.Area.Companion.isArea
import twerszko.postcode.country.gb.District.Companion.isDistrict
import twerszko.postcode.country.gb.Sector.Companion.isSector
import twerszko.postcode.country.gb.Unit.Companion.isUnit

class GBPostcodePartFactory : PostcodePartFactory {
    override fun create(value: String): PostcodePart {
        return when {
            isArea(value) -> Area(value)
            isDistrict(value) -> District(value)
            isSector(value) -> Sector(value)
            isUnit(value) -> Unit(value)
            else -> throw IllegalArgumentException("Invalid GB postcode part '$value'")
        }
    }
}