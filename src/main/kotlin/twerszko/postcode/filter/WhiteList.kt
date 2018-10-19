package twerszko.postcode.filter

import twerszko.postcode.Postcode
import twerszko.postcode.PostcodePart
import java.util.function.Predicate

class WhiteList(supportedParts : Collection<PostcodePart>) : Predicate<Postcode> {
    private val supportedParts : Set<PostcodePart> = supportedParts.map { postcodePart -> postcodePart.normalized() }.toSet()

    override fun test(postcode: Postcode): Boolean {
        return postcode.normalized().parts().asSequence().map { supportedParts.contains(it) }.find { it == true } ?: false
    }
}