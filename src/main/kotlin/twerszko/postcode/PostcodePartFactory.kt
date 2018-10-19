package twerszko.postcode

interface PostcodePartFactory {
    fun create(value : String) : PostcodePart
}