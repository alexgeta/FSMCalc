package com.teamdev.fsmcalc.mathcalc.impl.functions;

import java.util.Collections;
import java.util.List;

/**
 * @author Alex Geta
 */
public class MaximumFunction extends AbstractMathFunction {

    @Override
    public Double calculate(List<Double> arguments) {
        return Collections.max(arguments);
    }

}
