package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.impl.*;
import com.teamdev.fsmcalc.mathcalc.impl.operators.OpeningBracket;

import static com.google.common.base.Objects.equal;

/**
 * @author Alex Geta
 */
public class OpeningBracketParser implements MathTokenParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final String OPENING_BRACKET = "(";
        final MathExpressionReader expressionReader = context.getExpressionReader();
        final String currentToken = expressionReader.getCurrentToken();
        if (!equal(currentToken, OPENING_BRACKET)) return null;

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                /*if we are currently not inside math function body,
                then do default processing of OpeningBracket token*/
                if (stack.getFunctionsStack().isEmpty()) {
                    stack.getOperatorsStack().push(new OpeningBracket());
                }/*otherwise skip opening bracket*/
            }
        };
    }
}
