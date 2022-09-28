package com.mastery.java.task.validation;

import com.mastery.java.task.validation.BirthDateValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Slf4j
class BirthDateValidatorTest {
    @InjectMocks
    ConstraintValidatorContext constraintValidatorContext;

    BirthDateValidator birthDateValidator = new BirthDateValidator();
    @Test
    void isValidAgeEighteenYearsAgo() {
        //given
        LocalDate dateOfBirth = LocalDate.of(LocalDate.now().minusYears(18).getYear(), 1, 1);
        // when
        boolean isValidAge = birthDateValidator.isValid(dateOfBirth, constraintValidatorContext);
        // then
        log.info("Date {}", dateOfBirth);
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
        log.info("Date {}", dateOfBirth);
        Assertions.assertTrue(isValidAge);
    }


    @Test
    void isNotValidFutureYears() {
        //given
        LocalDate dateOfBirth = LocalDate.of(2050, 1, 1);
        // when
        boolean isValidAge = birthDateValidator.isValid(dateOfBirth, constraintValidatorContext);
        // then
        log.info("Date {}", dateOfBirth);
        Assertions.assertFalse(isValidAge);
    }


}