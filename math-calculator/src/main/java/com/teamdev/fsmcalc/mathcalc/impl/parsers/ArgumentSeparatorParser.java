package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.impl.*;

import static com.google.common.base.Objects.equal;

/**
 * @author Alex Geta
 */
public class ArgumentSeparatorParser implements MathTokenParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {
        final String ARGUMENT_SEPARATOR = ",";
        final MathExpressionReader expressionReader = context.getExpressionReader();
        final String currentToken = expressionReader.getCurrentToken();
        if (!equal(currentToken, ARGUMENT_SEPARATOR)) return null;

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
            }
        };
    }
}
