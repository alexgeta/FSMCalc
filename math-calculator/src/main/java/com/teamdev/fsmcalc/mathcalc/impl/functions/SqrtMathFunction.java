package com.teamdev.fsmcalc.mathcalc.impl.functions;

import java.util.List;

/**
 * @author Alex Geta
 */
public class SqrtMathFunction extends AbstractMathFunction {
    @Override
    public Double calculate(List<Double> arguments) {
        if (arguments.size() == 1) {
            return Math.sqrt(arguments.get(0));
        } else throw new IllegalArgumentException(
                "Sqrt function requires 1 argument, but : " + arguments.size());
    }
}
