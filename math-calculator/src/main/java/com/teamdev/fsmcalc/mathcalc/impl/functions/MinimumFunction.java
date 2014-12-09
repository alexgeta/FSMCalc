package com.teamdev.fsmcalc.mathcalc.impl.functions;

import java.util.Collections;
import java.util.List;

/**
 * @author Alex Geta
 */
public class MinimumFunction extends AbstractMathFunction {

    @Override
    public Double calculate(List<Double> arguments) {
        return Collections.min(arguments);
    }

}
