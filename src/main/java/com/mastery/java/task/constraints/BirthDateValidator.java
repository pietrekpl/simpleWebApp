package com.mastery.java.task.constraints;


import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;
import java.time.Period;


@Slf4j
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class BirthDateValidator implements ConstraintValidator<AgeValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        int yearDifference = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return yearDifference >= 18;
    }
}
