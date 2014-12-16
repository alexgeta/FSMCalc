package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.impl.*;
import com.teamdev.fsmcalc.mathcalc.impl.functions.AbstractMathFunction;

public class NumberParser implements MathTokenParser {


    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final Double value;
        try {
            value = Double.parseDouble(expressionReader.getCurrentToken());
        } catch (NumberFormatException e) {
            return null;
        }

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                stack.getOperandStack().push(value);
                /*if we are currently inside math function body,
                then currently parsed number belongs to top of stack function as an argument*/
                if (!stack.getFunctionsStack().isEmpty()) {
                    ((AbstractMathFunction) stack.getFunctionsStack().peek()).incrementArgsCounter();
                }
            }
        };
    }
}
