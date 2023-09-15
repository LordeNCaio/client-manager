package com.macedocaio.customermanager.annotations;

import com.macedocaio.customermanager.annotations.Cpf.List;
import com.macedocaio.customermanager.validators.CpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ReportAsSingleViolation
@Constraint(validatedBy = CpfValidator.class)
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR,
        PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface Cpf {

    String message() default "Insira um CPF válido sem pontuação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Cpf[] value();
    }
}
