package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.fsm.StateAcceptor;
import com.teamdev.fsmcalc.mathcalc.impl.parsers.*;

import java.util.HashMap;
import java.util.Map;
import static com.teamdev.fsmcalc.mathcalc.impl.State.*;

public class EvaluationService implements StateAcceptor<State, EvaluationContext> {

    private final Map<State, MathTokenParser> parsers = new HashMap<State, MathTokenParser>() {{
        put(NUMBER, new NumberParser());
        put(FINISH, new EndOfExpressionParser());
        put(BINARY_OPERATION, new BinaryOperationParser());
        put(MATH_FUNCTION, new MathFunctionParser());
        put(ARGUMENT_SEPARATOR, new ArgumentSeparatorParser());
        put(OPENING_BRACKET, new OpeningBracketParser());
        put(CLOSING_BRACKET, new ClosingBracketParser());
    }};

    @Override
    public boolean acceptState(EvaluationContext context, State possibleState) {

        final MathTokenParser parser = parsers.get(possibleState);
        if (parser == null) {
            throw new IllegalStateException("Parser not found for state: " + possibleState);
        }
        final EvaluationCommand evaluationCommand = parser.parse(context);
        if (evaluationCommand == null) return false;
        evaluationCommand.evaluate(context.getEvaluationStack());
        return true;
    }
}
