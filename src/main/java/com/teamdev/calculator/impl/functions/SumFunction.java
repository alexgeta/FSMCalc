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
        for (Double value : arguments){
            sum += value;
        }
        return sum;
    }
}
