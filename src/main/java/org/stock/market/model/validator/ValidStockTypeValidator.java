package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.stock.market.model.entity.stock.StockType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Rules for validating StockType for a Stock
 */
@Component
@Slf4j
public class ValidStockTypeValidator implements ConstraintValidator<ValidStockType,String> {


    @Override
    public boolean isValid(final String type, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if(type == null || type.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Type cannot be null/empty").addConstraintViolation();
            return false;
        }
        try {
            final StockType stockType = StockType.strToType(type);
            log.info("Stock Type is "+stockType.name());
            return true;
        }
        catch (NullPointerException  | IllegalArgumentException e){
            log.error("Invalid Enum value ["+type+"]");
            context.buildConstraintViolationWithTemplate("Type ["+type+"] is invalid").addConstraintViolation();
            return false;
        }
    }
}
