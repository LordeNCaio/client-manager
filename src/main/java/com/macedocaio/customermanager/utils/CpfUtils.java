package com.macedocaio.customermanager.utils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class CpfUtils {

    public static String applyMask(String cpf, Boolean applySignals) {
        try {
            MaskFormatter mask = !applySignals
                    ? new MaskFormatter("'*'*'*######'*'*")
                    : new MaskFormatter("'*'*'*.###.###-'*'*");
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean isValid(String cpf) {
        if (cpf.length() != 11) {return false;}

        List<Integer> firstDigitNumbers = Stream.of(cpf.substring(0, 9).split(""))
                .map(Integer::parseInt).toList();
        List<Integer> secondDigitNumbers = Stream.of(cpf.substring(0, 10).split(""))
                .map(Integer::parseInt).toList();

        String verifyingDigits = cpf.substring(9, 11);

        Integer firstDigit = calculateDigit(firstDigitNumbers, 10);
        Integer secondDigit = calculateDigit(secondDigitNumbers, 11);

        return verifyingDigits.equals(String.join("", firstDigit.toString(), secondDigit.toString()));
    }

    private static Integer calculateDigit(List<Integer> numbers, Integer startValue) {
        AtomicInteger start = new AtomicInteger(startValue);

        int sum = numbers.stream()
                .reduce(0, (acc, number) -> acc + (number * start.getAndDecrement()));

        int digit = 11 - (sum % 11);

        return digit == 10 ? 0 : digit;
    }
}
