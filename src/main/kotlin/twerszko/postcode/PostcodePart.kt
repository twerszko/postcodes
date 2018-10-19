package twerszko.postcode

interface PostcodePart {
    open val value: String
    open val countryCode : String
    fun normalized() : PostcodePart
}