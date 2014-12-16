package com.teamdev.fsmcalc.mathcalc.impl.functions;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * @author Alex Geta
 */
public class SqrtMathFunction extends AbstractMathFunction {
    @Override
    public Double calculate(List<Double> arguments) {
        Preconditions.checkArgument(arguments.size() == 1,
                "Sqrt function requires 1 argument, but : " + arguments.size());
        return Math.sqrt(arguments.get(0));
    }
}