package com.teamdev.fsmcalc.mathcalc.impl.functions;

import java.util.List;

/**
 * @author Alex Geta
 */
public class SumFunction extends AbstractMathFunction {

    @Override
    public Double calculate(List<Double> arguments) {
        Double sum = 0d;
        for (Double argument : arguments) sum += argument;
        return sum;
    }

}
