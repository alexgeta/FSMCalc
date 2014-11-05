package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.EvaluationCommand;
import com.teamdev.calculator.impl.EvaluationContext;
import com.teamdev.calculator.impl.EvaluationStack;
import com.teamdev.calculator.impl.MathExpressionParser;

public class EndOfExpressionParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        if (!context.getExpressionReader().endOfExpression()) {
            return null;
        }

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
            }
        };

    }
}
