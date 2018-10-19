package twerszko.postcode

interface Postcode {
    val countryCode : String
    fun parts() : List<PostcodePart>
    fun normalized() : Postcode
}