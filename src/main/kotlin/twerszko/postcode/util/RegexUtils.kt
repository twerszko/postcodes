package twerszko.postcode.util

internal class RegexUtils {
    companion object {
        fun extractNamedGroup(value:String, regex : Regex, groupName : String) = regex.find(value)!!.groups[groupName]!!.value
    }
}