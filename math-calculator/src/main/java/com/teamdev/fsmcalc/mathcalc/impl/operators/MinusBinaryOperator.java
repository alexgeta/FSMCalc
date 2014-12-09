package com.teamdev.fsmcalc.mathcalc.impl.operators;

import static com.teamdev.fsmcalc.mathcalc.impl.operators.AbstractBinaryOperator.Priority.LOW;

public class MinusBinaryOperator extends AbstractBinaryOperator {

    @Override
    protected Priority getPriority() {
        return LOW;
    }

    @Override
    public Double calculate(Double leftOperand, Double rightOperand) {
        return leftOperand - rightOperand;
    }

}

