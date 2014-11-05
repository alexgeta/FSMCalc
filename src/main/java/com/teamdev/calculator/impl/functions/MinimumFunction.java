package com.teamdev.calculator.impl.functions;

import com.teamdev.calculator.impl.MathFunction;

import java.util.Collections;
import java.util.List;

/**
 * @author Alex Geta
 */
public class MinimumFunction implements MathFunction {

    @Override
    public double calculate(List<Double> arguments) {
        return Collections.min(arguments);
    }
}
