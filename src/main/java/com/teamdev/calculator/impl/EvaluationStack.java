package com.teamdev.calculator.impl;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationStack {

    private final Deque<Double> operandStack = new ArrayDeque<Double>();

    public Deque<Double> getOperandStack() {
        return operandStack;
    }
}
