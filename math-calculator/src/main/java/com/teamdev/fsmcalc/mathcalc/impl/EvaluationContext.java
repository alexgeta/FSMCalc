package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.fsm.StateAcceptor;
import com.teamdev.fsmcalc.fsm.StateMachineContext;
import com.teamdev.fsmcalc.fsm.TransitionMatrix;
import com.teamdev.fsmcalc.mathcalc.EvaluationException;

public class EvaluationContext implements StateMachineContext<State, EvaluationContext, EvaluationException> {

    private final EvaluationMatrix matrix = new EvaluationMatrix();
    private final EvaluationService evaluationService = new EvaluationService();
    private final BinaryOperatorFactory binaryOperatorFactory = new BinaryOperatorFactory();
    private final MathFunctionFactory functionFactory = new MathFunctionFactory();
    private final EvaluationStack evaluationStack = new EvaluationStack();
    private final MathExpressionReader expressionReader;

    public EvaluationContext(String mathExpression) {
        expressionReader = new MathExpressionReader(mathExpression);
    }

    public MathExpressionReader getExpressionReader() {
        return expressionReader;
    }

    public BinaryOperatorFactory getBinaryOperatorFactory() {
        return binaryOperatorFactory;
    }

    public MathFunctionFactory getFunctionFactory() {
        return functionFactory;
    }

    public EvaluationStack getEvaluationStack() {
        return evaluationStack;
    }

    @Override
    public TransitionMatrix<State> getTransitionMatrix() {
        return matrix;
    }

    @Override
    public StateAcceptor<State, EvaluationContext, EvaluationException> getStateAcceptor() {
        return evaluationService;
    }
}
