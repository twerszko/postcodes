package twerszko.postcode.country.gb

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GBPostcodePartTest {
    @Test
    @Throws(Exception::class)
    fun shouldValidateArea() {
        assertThat(Area.isArea("EC")).isTrue()
        assertThat(Area.isArea("eC")).isTrue()
        assertThat(Area.isArea("ec")).isTrue()
        assertThat(Area.isArea("W")).isTrue()
        assertThat(Area.isArea("w")).isTrue()

        assertThat(Area.isArea("EC1A")).isFalse()
        assertThat(Area.isArea("W1")).isFalse()
        assertThat(Area.isArea("1W")).isFalse()
        assertThat(Area.isArea("1W1")).isFalse()
    }

    @Test
    @Throws(Exception::class)
    fun shouldValidateDistrict() {
        assertThat(District.isDistrict("EC1A")).isTrue()
        assertThat(District.isDistrict("ec1a")).isTrue()
        assertThat(District.isDistrict("eC1a")).isTrue()
        assertThat(District.isDistrict("W1A")).isTrue()
        assertThat(District.isDistrict("M1")).isTrue()
        assertThat(District.isDistrict("DN55")).isTrue()

        assertThat(District.isDistrict("EC1A ")).isFalse()
        assertThat(District.isDistrict(" EC1A")).isFalse()
        assertThat(District.isDistrict("EC1AA")).isFalse()
        assertThat(District.isDistrict("W1AA")).isFalse()
        assertThat(District.isDistrict("DN555")).isFalse()
        assertThat(District.isDistrict("DN55A")).isFalse()
        assertThat(District.isDistrict("DN55 ")).isFalse()
    }

    @Test
    @Throws(Exception::class)
    fun shouldValidateSector() {
        assertThat(Sector.isSector("EC1A 1")).isTrue()
        assertThat(Sector.isSector("EC1A1")).isTrue()
        assertThat(Sector.isSector("ec1a 1")).isTrue()
        assertThat(Sector.isSector("ec1A 1")).isTrue()
        assertThat(Sector.isSector("W1A 0")).isTrue()
        assertThat(Sector.isSector("W1A0")).isTrue()
        assertThat(Sector.isSector("M1 1")).isTrue()
        assertThat(Sector.isSector("B33 8")).isTrue()
        assertThat(Sector.isSector("B338")).isTrue()
        assertThat(Sector.isSector("CR2 6")).isTrue()
        assertThat(Sector.isSector("DN55 1")).isTrue()
        assertThat(Sector.isSector("DN551")).isTrue()

        assertThat(Sector.isSector("EC1A 1A")).isFalse()
        assertThat(Sector.isSector("EC1A")).isFalse()
        assertThat(Sector.isSector("EC1A ")).isFalse()
        assertThat(Sector.isSector("EC1A1A")).isFalse()
        assertThat(Sector.isSector("DN551A")).isFalse()
        assertThat(Sector.isSector("DN5511")).isFalse()
        assertThat(Sector.isSector("M11A")).isFalse()
        assertThat(Sector.isSector("M11")).isFalse()
        assertThat(Sector.isSector("M11 ")).isFalse()
        assertThat(Sector.isSector(" M11")).isFalse()
        assertThat(Sector.isSector("CR26")).isFalse()
    }

    @Test
    @Throws(Exception::class)
    fun shouldValidateUnit() {
        assertThat(Unit.isUnit("EC1A 1BB")).isTrue()
        assertThat(Unit.isUnit("EC1A1BB")).isTrue()
        assertThat(Unit.isUnit("ec1a1bb")).isTrue()
        assertThat(Unit.isUnit("eC1a1Bb")).isTrue()
        assertThat(Unit.isUnit("W1A 0AX")).isTrue()
        assertThat(Unit.isUnit("M1 1AE")).isTrue()
        assertThat(Unit.isUnit("B33 8TH")).isTrue()
        assertThat(Unit.isUnit("CR2 6XH")).isTrue()
        assertThat(Unit.isUnit("DN55 1PT")).isTrue()

        assertThat(Unit.isUnit("EC1A 1BBB")).isFalse()
        assertThat(Unit.isUnit("EC1A 1BB1")).isFalse()
        assertThat(Unit.isUnit("1EC1A 1BB")).isFalse()
        assertThat(Unit.isUnit("1W1A 0AX")).isFalse()
        assertThat(Unit.isUnit("W1A 0AX1")).isFalse()
        assertThat(Unit.isUnit("W1A-0AX")).isFalse()
        assertThat(Unit.isUnit("W1A 0AX ")).isFalse()
        assertThat(Unit.isUnit(" W1A 0AX")).isFalse()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNormalize() {
        assertThat(Area("EC").normalized()).isEqualTo(Area("EC"))
        assertThat(Area("ec").normalized()).isEqualTo(Area("EC"))
        assertThat(Area("eC").normalized()).isEqualTo(Area("EC"))

        assertThat(District("EC1A").normalized()).isEqualTo(District("EC1A"))
        assertThat(District("ec1a").normalized()).isEqualTo(District("EC1A"))
        assertThat(District("ec1A").normalized()).isEqualTo(District("EC1A"))

        assertThat(Sector("EC1A 1").normalized()).isEqualTo(Sector("EC1A 1"))
        assertThat(Sector("EC1A1").normalized()).isEqualTo(Sector("EC1A 1"))
        assertThat(Sector("ec1a 1").normalized()).isEqualTo(Sector("EC1A 1"))
        assertThat(Sector("ec1a1").normalized()).isEqualTo(Sector("EC1A 1"))

        assertThat(Unit("EC1A 1BB").normalized()).isEqualTo(Unit("EC1A 1BB"))
        assertThat(Unit("EC1A1BB").normalized()).isEqualTo(Unit("EC1A 1BB"))
        assertThat(Unit("ec1a 1bb").normalized()).isEqualTo(Unit("EC1A 1BB"))
        assertThat(Unit("ec1A 1bB").normalized()).isEqualTo(Unit("EC1A 1BB"))
    }
}