package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoGeneratorTest {

    @Test
    fun `1 ~ 45 사이의 중복되지 않는 6자리 숫자를 생성해서 로또 리스트에 담아 반환한다`() {
        assertDoesNotThrow{ LottoGenerator.createByMoney(1000) }
    }

    @Test
    fun `구입 금액만큼 로또를 발행한다_값 2000`() {
        assertThat(LottoGenerator.createByMoney(2000)).hasSize(2)
    }

    @Test
    fun `구입 금액만큼 로또를 발행한다_값 10_000`() {
        assertThat(LottoGenerator.createByMoney(10_000)).hasSize(10)
    }

    @Test
    fun `구입 금액만큼 로또를 발행한다_값 100_000`() {
        assertThat(LottoGenerator.createByMoney(100_000)).hasSize(100)
    }
}