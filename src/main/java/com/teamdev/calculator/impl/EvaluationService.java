package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.parser.BinaryOperationParser;
import com.teamdev.calculator.impl.parser.EndOfExpressionParser;
import com.teamdev.calculator.impl.parser.NumberParser;
import com.teamdev.fsm.StateAcceptor;

import java.util.HashMap;
import java.util.Map;

import static com.teamdev.calculator.impl.State.*;

public class EvaluationService implements StateAcceptor<State, EvaluationContext> {

    private final Map<State, MathExpressionParser> parsers = new HashMap<State, MathExpressionParser>() {{
        put(NUMBER, new NumberParser());
        put(FINISH, new EndOfExpressionParser());
        put(BINARY_OPERATION, new BinaryOperationParser());
    }};

    @Override
    public boolean acceptState(EvaluationContext context, State possibleState) {

        final MathExpressionParser parser = parsers.get(possibleState);

        if (parser == null) {
            throw new IllegalStateException("Parser not found for state: " + possibleState);
        }

        context.getExpressionReader().skipWhitespaces();

        final EvaluationCommand evaluationCommand = parser.parse(context);
        if (evaluationCommand == null) {
            return false;
        }

        evaluationCommand.evaluate(context.getEvaluationStack());
        return true;
    }
}
