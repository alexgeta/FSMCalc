package com.teamdev.fsmcalc.mathcalc.impl.operators;

public class PlusBinaryOperator extends AbstractBinaryOperator {

    @Override
    protected Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public Double calculate(Double leftOperand, Double rightOperand) {
        return leftOperand + rightOperand;
    }

}

