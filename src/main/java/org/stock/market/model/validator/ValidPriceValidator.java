package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Rules for validating StockPrice input for a Stock
 */
@Component
public class ValidPriceValidator implements ConstraintValidator<ValidPrice,Number> {



    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if(number == null)
            return false;
        if(number instanceof BigDecimal)
            return ((BigDecimal) number).compareTo(BigDecimal.ZERO) == 1;
        return number instanceof Double || number instanceof Float
                ? number.doubleValue() > 0
                : number.longValue() > 0;
    }
}
