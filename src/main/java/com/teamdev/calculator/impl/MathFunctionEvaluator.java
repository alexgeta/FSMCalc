package com.teamdev.calculator.impl;

import java.util.*;

/**
 * Parse, evaluate and replace math function on their result in math expression string.
 *
 * @author Alex Geta
 */
public class MathFunctionEvaluator {

    private final FunctionFactory functionFactory = new FunctionFactory();
    private StringBuilder calculatedFunction;
    private MathFunction function;

    /**
     * Replace every occurrence of math function on their result.
     *
     * @param mathExpression math expression string in infix notation.
     * @return resulting math expression string.
     */
    public String evaluate(String mathExpression) {
        final String DELIMITERS = " ,+-*/()^";
        final StringTokenizer tokenizer = new StringTokenizer(mathExpression, DELIMITERS, true);
        String resultExpression = mathExpression;

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            function = functionFactory.create(token);
            if (function == null) continue;
            calculatedFunction = new StringBuilder();
            calculatedFunction.append(token);
            List<Double> arguments = getArguments(tokenizer);
            if (arguments.isEmpty()) throw new IllegalArgumentException("Empty " + function + " body");
            final Double result = function.calculate(arguments);
            resultExpression = resultExpression.replace(calculatedFunction.toString(), String.valueOf(result));
        }
        return resultExpression;
    }

    /*Parse arguments that belongs currently evaluating math function*/
    private List<Double> getArguments(StringTokenizer tokenizer) {
        final Set<String> allowedTokens = new HashSet<String>() {{
            add("(");
            add(","); //argument separator
            add(" ");
        }};
        final List<Double> arguments = new ArrayList<Double>();
        final String CLOSING_BRACKET = ")";

        String token = tokenizer.nextToken();
        while (!token.equals(CLOSING_BRACKET)) {
            try {
                final Double argument = Double.parseDouble(token);
                arguments.add(argument);
            } catch (NumberFormatException e) {
                if (!allowedTokens.contains(token))
                    throw new IllegalArgumentException("Not allowed token \"" + token + "\" in " + function + " body");
            } finally {
                calculatedFunction.append(token);
            }
            try {
                token = tokenizer.nextToken();
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Closing bracket missed");
            }
        }
        calculatedFunction.append(token);
        return arguments;
    }

}
