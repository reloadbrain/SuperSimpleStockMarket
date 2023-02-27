package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


/**
 * Rules for validating LastDividend for a Stock
 */
@Component
public class ValidLastDividendValidator  implements ConstraintValidator<ValidLastDividend,Double> {

    @Override
    public boolean isValid(
            final Double lastDividend,
            final ConstraintValidatorContext context) {
        if(lastDividend == null)
            return false;

        return lastDividend.doubleValue() >= 0.0;
    }
}
