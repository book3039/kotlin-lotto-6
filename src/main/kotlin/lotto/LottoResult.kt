package lotto

import java.util.*

class LottoResult() {

    lateinit var winLotto: Lotto
    var bonus = 0

    fun countWinNumbers(lotto: Lotto): Int {
        return winLotto.toAscendingList()
            .intersect(lotto.toAscendingList().toSet())
            .size
    }
    fun hasBonus(): Boolean = winLotto.toAscendingList().contains(bonus)

    enum class MatchNumber(val count: Int) {
        THREE_MATCH(3),
        FOUR_MATCH(4),
        FIVE_MATCH(5),
        SIX_MATCH(6)
    }
}
