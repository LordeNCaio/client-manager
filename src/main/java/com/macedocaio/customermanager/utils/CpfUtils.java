package com.macedocaio.customermanager.utils;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Classe contendo funções utilitárias para manipulação de CPF
 */
public final class CpfUtils {

    /**
     * Aplica uma máscara para dificultar a leitura do CPF
     *
     * @param cpf          que será mascarado
     * @param applySignals define se será usada a pontuação do CPF
     * @return CPF mascarado
     */
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

    /**
     * Verifica se um CPF está valido utilizando pela soma dos dígitos verificadores
     *
     * @param cpf que será calculado
     * @return {@link Boolean#TRUE} caso esteja válido ou {@link Boolean#FALSE} caso esteja inválido
     */
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

    /**
     * Calcula o digito verificador
     * @param numbers Lista de números que serão utilizados no cálculo
     * @param startValue Valor inicial para multiplicação do dígito
     * @return Digito verificador calculado
     */
    private static Integer calculateDigit(List<Integer> numbers, Integer startValue) {
        AtomicInteger start = new AtomicInteger(startValue);

        int sum = numbers.stream()
                .reduce(0, (acc, number) -> acc + (number * start.getAndDecrement()));

        int rest = (sum % 11);

        return rest < 2 ? 0 : (11 - rest);
    }
}
