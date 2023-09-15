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

    public static final String CPF_NUMBER_PATTERN = "^X{3}[0-9]{6}X{2}$";
    public static final String CPF_SIGNAL_PATTERN = "^X{3}[.][0-9]{3}[.][0-9]{3}-X{2}$";
    private static final String CPF_NUMBER_MASK = "XXX######XX";
    private static final String CPF_SIGNAL_MASK = "XXX.###.###-XX";
    private static final String CPF_SIGNAL_UNMASKED = "###.###.###-##";

    /**
     * Aplica uma máscara para dificultar a leitura do CPF
     *
     * @param cpf          que será mascarado
     * @param applySignals define se será usada a pontuação do CPF
     * @return CPF mascarado
     */
    public static String applyMask(String cpf, boolean applySignals) {
        return applySignals ? applySignalMask(cpf) : applyNumberMask(cpf);
    }

    /**
     * Verifica se um CPF está valido utilizando pela soma dos dígitos verificadores
     *
     * @param cpf que será calculado
     * @return {@link Boolean#TRUE} caso esteja válido ou {@link Boolean#FALSE} caso esteja inválido
     */
    public static Boolean isValid(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

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
     * Aplica a pontuação do CPF
     *
     * @param cpf que terá pontuação adicionada
     * @return CPF com pontuação
     */
    public static String applySignals(String cpf) {
        try {
            MaskFormatter mask = getFormatter(CPF_SIGNAL_UNMASKED);
            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aplica uma máscara para dificultar a leitura do CPF
     *
     * @param cpf que será mascarado
     * @return CPF mascarado
     */
    private static String applyNumberMask(String cpf) {
        try {
            MaskFormatter mask = getFormatter(CPF_NUMBER_MASK);
            mask.setValueContainsLiteralCharacters(false);

            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aplica uma máscara para dificultar a leitura do CPF
     *
     * @param cpf que será mascarado
     * @return CPF mascarado
     */
    private static String applySignalMask(String cpf) {
        try {
            MaskFormatter mask = getFormatter(CPF_SIGNAL_MASK);
            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calcula o digito verificador
     *
     * @param numbers    Lista de números que serão utilizados no cálculo
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

    /**
     * Cria um {@link MaskFormatter} com uma máscara customizada
     *
     * @param mask máscara que será aplicada
     * @return {@link MaskFormatter} com uma máscara customizada
     */
    private static MaskFormatter getFormatter(String mask) throws ParseException {
        MaskFormatter maskFormatter = new MaskFormatter(mask);
        maskFormatter.setValueContainsLiteralCharacters(false);
        return maskFormatter;
    }
}
