package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * Rules for validating ParValue for a Stock
 */
@Component
public class ValidParValueValidator implements ConstraintValidator<ValidParValue,Double> {

    @Override
    public boolean isValid(
            final Double parValue,
            final ConstraintValidatorContext context) {
        if(parValue == null)
            return false;
        return parValue.doubleValue() >= 0.0;
    }
}
