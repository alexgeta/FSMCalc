package com.teamdev.fsmcalc.mathcalc.impl;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private final Deque<Double> operandStack = new ArrayDeque<Double>();
    private final Deque<BinaryOperator> operatorsStack = new ArrayDeque<BinaryOperator>();
    private final Deque<MathFunction> functionsStack = new ArrayDeque<MathFunction>();

    public Deque<Double> getOperandStack() {
        return operandStack;
    }

    public Deque<BinaryOperator> getOperatorsStack() {
        return operatorsStack;
    }

    public Deque<MathFunction> getFunctionsStack() {
        return functionsStack;
    }
}
