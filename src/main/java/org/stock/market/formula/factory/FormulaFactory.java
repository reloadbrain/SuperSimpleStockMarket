package org.stock.market.formula.factory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.stock.market.formula.impl.DividendYield;
import org.stock.market.formula.impl.GeometricMeanPrice;
import org.stock.market.formula.impl.PERatio;
import org.stock.market.formula.impl.VolumeWeightedPrice;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
@Service
public class FormulaFactory {

    private final static Map<String, Supplier<Formula>> map = new HashMap<>();
    static {
        map.put(DividendYield.class.getSimpleName(), DividendYield::new);
        map.put(GeometricMeanPrice.class.getSimpleName(), GeometricMeanPrice::new);
        map.put(VolumeWeightedPrice.class.getSimpleName(),VolumeWeightedPrice::new);
        map.put(PERatio.class.getSimpleName(),()-> new PERatio(new DividendYield()));
    }
    public <T extends Formula> T getFormula(Class<T> formulaType){
        Supplier<Formula> shape = map.get(formulaType.getSimpleName());
        if(shape != null) {
            return (T) shape.get();
        }
        throw new IllegalArgumentException("No such formulaType " + formulaType.getSimpleName());
    }
}
