package twerszko.postcode.country.gb

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GBPostcodePartFactoryTest {
    @Test
    @Throws(Exception::class)
    fun shouldCreatePostcodePart() {
        val underTest = GBPostcodePartFactory()

        assertThat(underTest.create("EC")).isInstanceOf(Area::class.java)
        assertThat(underTest.create("EC1A")).isInstanceOf(District::class.java)
        assertThat(underTest.create("EC1A 1")).isInstanceOf(Sector::class.java)
        assertThat(underTest.create("EC1A1")).isInstanceOf(Sector::class.java)
        assertThat(underTest.create("EC1A 1BB")).isInstanceOf(Unit::class.java)
        assertThat(underTest.create("EC1A1BB")).isInstanceOf(Unit::class.java)

        assertThat(underTest.create("M")).isInstanceOf(Area::class.java)
        assertThat(underTest.create("M1")).isInstanceOf(District::class.java)
        assertThat(underTest.create("M11")).isInstanceOf(District::class.java) // NOTABLE
        assertThat(underTest.create("M1 1")).isInstanceOf(Sector::class.java)
        assertThat(underTest.create("M1 1AE")).isInstanceOf(Unit::class.java)
        assertThat(underTest.create("M11AE")).isInstanceOf(Unit::class.java)
    }
}