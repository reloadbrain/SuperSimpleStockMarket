package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ValidLastDividendValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

    @InjectMocks
    private ValidLastDividendValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);

    }

    @Test
    public void verify_invalid_null_value(){
        assertEquals(false,validator.isValid(null,context));
    }

    @Test
    public void verify_invalid_nonnull_ltzero_value(){
        assertEquals(false,validator.isValid(-9.0,context));
    }

    @Test
    public void verify_valid_nonnull_zero_value(){
        assertEquals(true,validator.isValid(0.0,context));
    }

    @Test
    public void verify_valid_nonnull_nonzero_value(){
        assertEquals(true,validator.isValid(1.0,context));
        assertEquals(true,validator.isValid(0.4,context));
        assertEquals(true,validator.isValid(100000.0,context));
        assertEquals(true,validator.isValid(78.2,context));
    }

}