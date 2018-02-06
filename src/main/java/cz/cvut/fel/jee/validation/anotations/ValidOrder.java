package cz.cvut.fel.jee.validation.anotations;

import cz.cvut.fel.jee.validation.validators.OrderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vaclav Rechtberger
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrderValidator.class)
//@Documented
public @interface ValidOrder {
    String message () default "total price must be greater than 0 and item list cannot be empty."; //+
            //"Found: ${validatedValue.total}";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
