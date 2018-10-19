package twerszko.postcode.country.gb;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class UnitTest {
    @Test
    fun shouldCreatePostcodeOfType1() {
        val postcode = Unit("EC1A 1BB")

        assertThat(postcode.area).isEqualTo(Area("EC"))
        assertThat(postcode.district).isEqualTo(District("EC1A"))
        assertThat(postcode.sector).isEqualTo(Sector("EC1A 1"))
    }

    @Test
    fun shouldCreatePostcodeOfType2(){
        val postcode = Unit("W1A 0AX")

        assertThat(postcode.area).isEqualTo(Area("W"))
        assertThat(postcode.district).isEqualTo(District("W1A"))
        assertThat(postcode.sector).isEqualTo(Sector("W1A 0"))
    }

    @Test
    fun shouldCreatePostcodeOfType3() {
        val postcode = Unit("M1 1AE")

        assertThat(postcode.area).isEqualTo(Area("M"))
        assertThat(postcode.district).isEqualTo(District("M1"))
        assertThat(postcode.sector).isEqualTo(Sector("M1 1"))
    }

    @Test
    fun shouldCreatePostcodeOfType4() {
        val postcode = Unit("B33 8TH")

        assertThat(postcode.area).isEqualTo(Area("B"))
        assertThat(postcode.district).isEqualTo(District("B33"))
        assertThat(postcode.sector).isEqualTo(Sector("B33 8"))
    }

    @Test
    fun shouldCreatePostcodeOfType5() {
        val postcode = Unit("CR2 6XH")

        assertThat(postcode.area).isEqualTo(Area("CR"))
        assertThat(postcode.district).isEqualTo(District("CR2"))
        assertThat(postcode.sector).isEqualTo(Sector("CR2 6"))
    }

    @Test
    fun shouldCreatePostcodeOfType6() {
        val postcode = Unit("DN55 1PT")

        assertThat(postcode.area).isEqualTo(Area("DN"))
        assertThat(postcode.district).isEqualTo(District("DN55"))
        assertThat(postcode.sector).isEqualTo(Sector("DN55 1"))
    }

    @Test
    fun shouldCreatePostcodeOfDenormalizedType6() {
        val postcode = Unit("DN551PT")

        assertThat(postcode.area).isEqualTo(Area("DN"))
        assertThat(postcode.district).isEqualTo(District("DN55"))
        assertThat(postcode.sector).isEqualTo(Sector("DN551"))
    }

    @Test
    fun shouldNormalize() {
        assertThat(Unit("DN551PT").normalized()).isEqualTo(Unit("DN55 1PT"))
        assertThat(Unit ("DN55 1PT").normalized()).isEqualTo(Unit("DN55 1PT"))
    }

    @Test
    fun shouldGetParts(){
        assertThat(Unit("CR2 6XH").parts()).containsExactly(
                Unit("CR2 6XH"), Sector("CR2 6"), District("CR2"), Area("CR"))

        assertThat(Unit("ec1a1bb").parts()).containsExactly(
                Unit("ec1a1bb"), Sector("ec1a1"), District("ec1a"), Area("ec")
        )
    }
}