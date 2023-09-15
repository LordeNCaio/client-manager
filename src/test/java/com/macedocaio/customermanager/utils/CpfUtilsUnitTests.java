package com.macedocaio.customermanager.utils;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CpfUtilsUnitTests {

    private final String validCpf = "95239238464";
    private final String invalidCpf = "95236538464";

    @Test
    public void should_Mask_Cpf_Without_Signals() {
        Pattern pattern = Pattern.compile(CpfUtils.CPF_NUMBER_PATTERN);
        String maskedCpf = CpfUtils.applyMask(validCpf, false);

        Matcher matcher = pattern.matcher(maskedCpf);
        assertTrue(matcher.find());
    }

    @Test
    public void should_Mask_Cpf_With_Signals() {
        Pattern pattern = Pattern.compile(CpfUtils.CPF_SIGNAL_PATTERN);
        String maskedCpf = CpfUtils.applyMask(validCpf, true);

        Matcher matcher = pattern.matcher(maskedCpf);
        assertTrue(matcher.find());
    }

    @Test
    public void should_Return_True_With_Valid_Cpf() {
        Boolean isValid = CpfUtils.isValid(validCpf);
        assertTrue(isValid);
    }

    @Test
    public void should_Return_False_With_Invalid_Cpf() {
        Boolean isValid = CpfUtils.isValid(invalidCpf);
        assertFalse(isValid);
    }

    @Test
    public void should_Return_True_With_Valid_Cpf_With_Signals() {
        String cpfWithSignals = CpfUtils.applySignals(validCpf);
        Boolean isValid = CpfUtils.isValid(cpfWithSignals);
        assertTrue(isValid);
    }

    @Test
    public void should_Return_False_With_Invalid_Cpf_With_Signals() {
        String cpfWithSignals = CpfUtils.applySignals(invalidCpf);
        Boolean isValid = CpfUtils.isValid(cpfWithSignals);
        assertFalse(isValid);
    }
}
