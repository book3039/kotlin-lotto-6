package ui

import camp.nextstep.edu.missionutils.Console

const val MSG_INPUT_MONEY = "구입금액을 입력해 주세요."
const val MSG_INPUT_WIN_NUMBERS = "당첨 번호를 입력해 주세요."
const val MSG_INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요."
const val MSG_INPUT_LOTTO_NUMBER_BY_COMMA = "숫자가 여러 개인 경우 쉼표(,)로 구분해주세요. 예) 1,2,3,4,5,6"

object UserInput {
    private lateinit var duplicateNumbers: List<Int> // 당첨 번호들을 담아 두기 위한 변수(보너스 번호 중복 체크 용도)

    private fun readOnlyDigitAndToInt(): Int {
            val input = Console.readLine()
            InputValidator
                .checkIsDigit(input)
                .checkIsEmptyString(input)

            return input.toInt()
        }

    fun readMoney(): Int {
        while (true) {
            try {
                println(MSG_INPUT_MONEY)
                val money = readOnlyDigitAndToInt()
                InputValidator.checkPurchaseRange(money)
                InputValidator.checkIsDivisibleByThousand(money)
                return money
            } catch (e: NumberFormatException) {
                println(MSG_ERR_INVALIDATE_INPUT)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }

    }

    fun readWinNumbers(): List<Int> {
        while (true) {
            try {
                println(MSG_INPUT_WIN_NUMBERS)
                val winNumbers = inputToNumbersByComma(Console.readLine())
                InputValidator
                    .checkProperNumbersSize(winNumbers)
                    .checkNumberListInRange(winNumbers)
                duplicateNumbers = winNumbers // 입력 받은 당첨 번호를 중복 숫자 리스트에 담는다.(보너스 번호 중복 체크 용도)

                return winNumbers
            } catch (e: NumberFormatException) {
                println(MSG_ERR_INVALIDATE_INPUT)
            } catch (e: IllegalArgumentException) {
                println(e.message + " " + MSG_INPUT_LOTTO_NUMBER_BY_COMMA)
            }
        }
    }

    fun readBonusNumber(): Int {
        while (true) {
            try {
                println(MSG_INPUT_BONUS_NUMBER)
                val bonus = readOnlyDigitAndToInt()
                InputValidator
                    .checkNumberInRange(bonus)
                    .checkIsDuplicateNumber(bonus, duplicateNumbers)

                return bonus
            } catch (e: NumberFormatException) {
                println(MSG_ERR_INVALIDATE_INPUT)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun inputToNumbersByComma(input: String): List<Int> {
        return input.split(",")
            .filter { it.isNotBlank() || it.isNotEmpty() }
            .map {
                val digit = it.trim()
                InputValidator
                    .checkIsDigit(digit)
                    .checkIsEmptyString(digit)
                digit.toInt()
            }.distinct()
    }
}


