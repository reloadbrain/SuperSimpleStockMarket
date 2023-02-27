package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.stock.market.model.entity.trade.TradeIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Rules for validating Trade indicator for a Trade
 */
@Component
@Slf4j
public class ValidTradeIndicatorValidator implements ConstraintValidator<ValidTradeIndicator,String> {

    @Override
    public boolean isValid(final String indicator, final ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if(indicator == null || indicator.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Indicator cannot be null/empty").addConstraintViolation();
            return false;
        }
        try {
            final TradeIndicator tradeIndicator = TradeIndicator.strToIndicator(indicator);
            log.info("Stock Type is "+tradeIndicator.name());
            return true;
        }
        catch (NullPointerException | IllegalArgumentException e){
            log.error("Invalid Enum value ["+indicator+"]");
            context.buildConstraintViolationWithTemplate("Indicator ["+indicator+"] is invalid").addConstraintViolation();
            return false;
        }
    }
}
