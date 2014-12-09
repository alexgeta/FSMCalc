package com.teamdev.fsmcalc.mathcalc.impl.operators;

public class PowerBinaryOperator extends AbstractBinaryOperator {

    @Override
    protected Priority getPriority() {
        return Priority.HIGH;
    }

    @Override
    public Double calculate(Double leftOperand, Double rightOperand) {
        return Math.pow(leftOperand, rightOperand);
    }

}

