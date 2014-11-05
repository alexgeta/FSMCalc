package com.teamdev.calculator.impl;

import com.teamdev.fsm.StateAcceptor;
import com.teamdev.fsm.StateMachineContext;
import com.teamdev.fsm.TransitionMatrix;

public class EvaluationContext implements StateMachineContext<State, EvaluationContext> {

    private final EvaluationMatrix matrix = new EvaluationMatrix();
    private final EvaluationService evaluationService = new EvaluationService();
    private final BinaryOperatorFactory binaryOperatorFactory = new BinaryOperatorFactory();
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

    public EvaluationStack getEvaluationStack() {
        return evaluationStack;
    }

    @Override
    public TransitionMatrix<State> getTransitionMatrix() {
        return matrix;
    }

    @Override
    public StateAcceptor<State, EvaluationContext> getStateAcceptor() {
        return evaluationService;
    }
}
