package com.macedocaio.customermanager.validators;

import com.macedocaio.customermanager.annotations.Cpf;
import com.macedocaio.customermanager.utils.CpfUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {
    @Override
    public void initialize(Cpf constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return CpfUtils.isValid(s);
    }
}
