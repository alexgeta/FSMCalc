package com.teamdev.calculator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Alex Geta
 */
public class MathFunctionEvaluator {

    private final FunctionFactory functionFactory = new FunctionFactory();
    private StringBuilder calculatedFunction;


    public String evaluate(String mathExpression){
        final String DELIMITERS = " ,+-*/()^";
        String resultExpression = mathExpression;
        final StringTokenizer tokenizer = new StringTokenizer(mathExpression, DELIMITERS, true);

        while (tokenizer.hasMoreTokens()){

            String token = tokenizer.nextToken();
            final MathFunction function = functionFactory.create(token);
            if(function == null) continue;
            calculatedFunction = new StringBuilder();
            calculatedFunction.append(token);
            List<Double> arguments = getArguments(tokenizer);
            final Double result = function.calculate(arguments);
            resultExpression = resultExpression.replace(calculatedFunction.toString(),String.valueOf(result));
        }

        return resultExpression;
    }

    private List<Double> getArguments(StringTokenizer tokenizer){

        final List<Double> arguments = new ArrayList<Double>();
        String token = tokenizer.nextToken();

        while (!token.equals(")")){
            calculatedFunction.append(token);
            try {
                final Double value = Double.parseDouble(token);
                arguments.add(value);
            } catch (NumberFormatException e) {
            }
            token = tokenizer.nextToken();
        }
        calculatedFunction.append(token);
        return arguments;
    }

}
