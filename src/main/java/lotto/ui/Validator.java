package lotto.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    private static final String ERROR_INPUT_IS_NOT_NUMBER= "[ERROR] 입력값이 숫자가 아닙니다.";
    private static final String ERROR_MONEY_NOT_OVER_THOUSAND= "[ERROR] 로또 구입 금액은 1000원 이상이어야합니다. ";
    private static final String ERROR_MONEY_NOT_DIVIDED_WITH_THOUSAND= "[ERROR] 로또 구입 금액은 1000으로 나누어 떨어져야합니다. ";
    private static final String ERROR_NUMBER_IS_NOT_IN_RANGE_FROM_ONE_TO_FOURTYFIVE = "[ERROR] 숫자의 범위는 1에서 45 사이여야합니다.";
    private static final String ERROR_NUMBERS_ARE_NOT_CONSISTS_OF_SIX = "[ERROR] 6자리로 구성된 숫자가 아닙니다.";
    private static final String ERROR_BONUS_NUMBER_DUPLICATED = "[ERROR] 보너스 번호 중복입니다.";


    public void validateMoney(String money) {
        validateConsistOfNumber(money);
        validateProperRangeOfMoney(money);
    }

    public void validateNormalNumbers(String normalNumbers) {
        List<Integer> numbers = transformNumberAsInteger(normalNumbers);
        for (Integer number : numbers) {
            validateProperRangeOfNumber(number);
        }
        validateConsistOfSixNumbers(numbers);
    }

    public void validateBonusNumber(List<Integer> normalNumbers, String bonusNumber) {
        validateConsistOfNumber(bonusNumber);
        validateProperRangeOfNumber(Integer.parseInt(bonusNumber));
        validateNotDuplicatedNumber(normalNumbers, Integer.parseInt(bonusNumber));
    }

    private void validateNotDuplicatedNumber(List<Integer> normalNumbers, Integer bonusNumber) {
        if (normalNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATED);
        }
    }

    private void validateConsistOfNumber(String string) {
        if (!Pattern.matches("[0-9]+", string)) {
            throw new IllegalArgumentException(ERROR_INPUT_IS_NOT_NUMBER);
        }
    }

    private void validateProperRangeOfNumber(int parseInt) {
        if (parseInt < 1 || parseInt > 45) {
            throw new IllegalArgumentException(ERROR_NUMBER_IS_NOT_IN_RANGE_FROM_ONE_TO_FOURTYFIVE);
        }
    }
    private void validateConsistOfSixNumbers(List<Integer> numbersInString) {
        if (numbersInString.size() != 6) {
            throw new IllegalArgumentException(ERROR_NUMBERS_ARE_NOT_CONSISTS_OF_SIX);
        }
    }

    private List<Integer> transformNumberAsInteger(String normalNumbers) {
        List<String> numbersInString = List.of(normalNumbers.split(","));
        List<Integer> numbersInInteger = new ArrayList<>();
        for (String number : numbersInString) {
            numbersInInteger.add(Integer.parseInt(number));
        }
        return numbersInInteger;
    }
    private void validateProperRangeOfMoney(String money) {
        int moneyInt = Integer.parseInt(money);
        if (moneyInt < 1000) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_OVER_THOUSAND);
        }
        if (moneyInt % 1000 != 0) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIVIDED_WITH_THOUSAND);
        }
    }
}