package com.mastery.java.task.constraints;

import com.mastery.java.task.model.Gender;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenderSubsetValidatorTest {


    private Gender[] genders;

    @InjectMocks
    ConstraintValidatorContext constraintValidatorContext;

    @Test
    void initialize() {
    }

    @Test
    void isValid() {

    }
}