package com.mastery.java.task.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;
import java.time.Period;



@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class BirthDateValidator implements ConstraintValidator<AgeValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        int yearsDifference = Period.between(dateOfBirth, LocalDate.now()).getYears();
        return yearsDifference >= 18;
    }
}
