package com.mastery.java.task.constraints;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


class BirthDateValidatorTest {
    @InjectMocks
    ConstraintValidatorContext constraintValidatorContext;

    BirthDateValidator birthDateValidator = new BirthDateValidator();
    @Test
    void isValidAgeRandomEighteenYearsAgo() {
        //given
        LocalDate dateOfBirth = LocalDate.of(1990, 8, 10);
        // when
        boolean isValidAge = birthDateValidator.isValid(dateOfBirth, constraintValidatorContext);
        // then
        Assertions.assertTrue(isValidAge);
    }
    @Test
    void isValidAgeExactlyEighteenYearsAgo() {
        //given
        LocalDate dateOfBirth = LocalDate.of(LocalDate.now().minusYears(18).getYear(),
                LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth());
        // when
        boolean isValidAge = birthDateValidator.isValid(dateOfBirth, constraintValidatorContext);
        // then
        Assertions.assertTrue(isValidAge);
    }


    @Test
    void isNotValidFutureYears() {
        //given
        LocalDate dateOfBirth = LocalDate.of(2099, 1, 1);
        // when
        boolean isValidAge = birthDateValidator.isValid(dateOfBirth, constraintValidatorContext);
        // then
        Assertions.assertFalse(isValidAge);
    }


}