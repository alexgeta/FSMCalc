package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.impl.*;
import com.teamdev.fsmcalc.mathcalc.impl.functions.AbstractMathFunction;

import java.util.Deque;

/**
 * @author Alex Geta
 */
public class MathFunctionParser implements MathTokenParser {
    @Override
    public EvaluationCommand parse(EvaluationContext context) {
        final MathExpressionReader expressionReader = context.getExpressionReader();
        if (expressionReader.isEndOfExpression()) return null;
        final MathFunctionFactory functionFactory = context.getFunctionFactory();
        final MathFunction function = functionFactory.create(expressionReader.getCurrentToken());
        if (function == null) return null;
        expressionReader.nextToken();
        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                final Deque<MathFunction> functionsStack = stack.getFunctionsStack();
                /*if we are currently inside math function body,
                then currently parsed function(inner function) belongs to top of stack function as argument*/
                if (!functionsStack.isEmpty()) {
                    ((AbstractMathFunction) functionsStack.peek()).incrementArgsCounter();
                }
                functionsStack.push(function);
            }
        };

    }
}
