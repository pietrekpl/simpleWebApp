package com.mastery.java.task.validation;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;




public class BirthDateValidator implements ConstraintValidator<AgeValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
       return (Period.between(dateOfBirth, LocalDate.now()).getYears()) >= 18;

    }
}
