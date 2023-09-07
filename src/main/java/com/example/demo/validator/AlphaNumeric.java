package com.example.demo.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlphaNumericValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphaNumeric {
    String message() default "No special characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
