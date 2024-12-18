package learn.kafka.security.auth.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import learn.kafka.security.auth.utils.validator.EmailValidator;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
    String message() default "invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
