package com.teamdev.fsmcalc.mathcalc.impl.operators;

public class DivideBinaryOperator extends AbstractBinaryOperator {

    @Override
    protected Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public Double calculate(Double leftOperand, Double rightOperand) {
        return leftOperand / rightOperand;
    }

}

