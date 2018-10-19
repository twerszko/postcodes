package twerszko.postcode.filter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import twerszko.postcode.country.gb.*
import twerszko.postcode.country.gb.Unit

class WhiteListTest {
    @Test
    fun shouldReturnTrueWhenWhiteListSupportsPostcode() {
        val underTest = WhiteList(listOf(Area("m"), District("b33"), Sector("cr2 6"), Unit("dn551pt")))

        assertThat(underTest.test(Unit("M1 1ae"))).isTrue()
        assertThat(underTest.test(Unit("b338TH"))).isTrue()
        assertThat(underTest.test(Unit("CR2 6XH"))).isTrue()
        assertThat(underTest.test(Unit("DN551PT"))).isTrue()
    }

    @Test
    fun shouldReturnFalseWhenWhiteListDoesNotSupportsPostcode() {
        val underTest = WhiteList(listOf(Area("m"), District("b33"), Sector("cr2 6"), Unit("dn551pt")))

        assertThat(underTest.test(Unit("N1 1ae"))).isFalse()
        assertThat(underTest.test(Unit("b348TH"))).isFalse()
        assertThat(underTest.test(Unit("CR2 7XH"))).isFalse()
        assertThat(underTest.test(Unit("DN551PU"))).isFalse()
    }
}