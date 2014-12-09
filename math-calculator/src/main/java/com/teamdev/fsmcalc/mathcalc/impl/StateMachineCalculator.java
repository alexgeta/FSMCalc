package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.fsm.FiniteStateMachine;
import com.teamdev.fsmcalc.mathcalc.MathExpressionCalculator;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double>
        implements MathExpressionCalculator {

    @Override
    public double evaluate(String mathExpression) {
        return run(new EvaluationContext(mathExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) {
        final String currentToken = context.getExpressionReader().getCurrentToken();
        final int tokenPosition = context.getExpressionReader().getTokenPosition();
        throw new IllegalArgumentException("Not allowed token \"" + currentToken + "\" at position " + tokenPosition);
    }

    @Override
    protected Double finish(EvaluationContext context) {
        return context.getEvaluationStack().getOperandStack().pop();
    }
}
