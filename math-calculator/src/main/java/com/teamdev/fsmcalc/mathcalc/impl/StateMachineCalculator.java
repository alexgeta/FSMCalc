package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.fsm.FiniteStateMachine;
import com.teamdev.fsmcalc.mathcalc.EvaluationException;
import com.teamdev.fsmcalc.mathcalc.MathExpressionCalculator;

import java.util.StringTokenizer;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double, EvaluationException>
        implements MathExpressionCalculator {

    @Override
    public Double evaluate(String mathExpression) throws EvaluationException {
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    public Double[] evaluatePack(String expressions) throws EvaluationException {
        final String delim = ";";
        StringTokenizer tokenizer = new StringTokenizer(expressions, delim);
        Double[] results = new Double[tokenizer.countTokens()];
        for (int i = 0; i < results.length; i++) {
            try {
                results[i] = run(new EvaluationContext(tokenizer.nextToken()));
            } catch (EvaluationException e) {
                throw new EvaluationException(e.getMessage() + " in " + (i + 1) + "-th expression");
            }
        }
        return results;
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) throws EvaluationException {
        final String currentToken = context.getExpressionReader().getCurrentToken();
        final int tokenPosition = context.getExpressionReader().getTokenPosition();
        if (currentToken == null) throw new EvaluationException("Expression must end with a number");
        throw new EvaluationException("Not allowed token \"" + currentToken + "\" at position " + tokenPosition);
    }

    @Override
    protected Double finish(EvaluationContext context) {
        return context.getEvaluationStack().getOperandStack().pop();
    }
}
