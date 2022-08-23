package com.mastery.java.task.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = BirthDateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeValidation {

    String message() default "Only employees with age 18 and above are legible to store";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
