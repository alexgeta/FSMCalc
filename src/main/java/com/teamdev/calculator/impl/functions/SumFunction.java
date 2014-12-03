package com.teamdev.calculator.impl.functions;

import com.teamdev.calculator.impl.MathFunction;

import java.util.List;

/**
 * @author Alex Geta
 */
public class SumFunction implements MathFunction {

    @Override
    public double calculate(List<Double> arguments) {
        Double sum = 0d;
        for (Double argument : arguments) sum += argument;
        return sum;
    }

    @Override
    public String toString() {
        return "Sum function";
    }
}
