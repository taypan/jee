package cz.cvut.fel.jee.validation.validators;

import cz.cvut.fel.jee.model.Order;
import cz.cvut.fel.jee.validation.anotations.ValidOrder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Vaclav Rechtberger
 */
public class OrderValidator implements
        ConstraintValidator<ValidOrder, Order> {
    @Override
    public void initialize (ValidOrder constraintAnnotation) {
    }

    @Override
    public boolean isValid (Order order,
                            ConstraintValidatorContext context) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            return false;
        }
        return order.getTotal() > 0;

    }
}
