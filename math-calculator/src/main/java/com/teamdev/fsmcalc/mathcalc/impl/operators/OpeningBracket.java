package com.teamdev.fsmcalc.mathcalc.impl.operators;

import static com.teamdev.fsmcalc.mathcalc.impl.operators.AbstractBinaryOperator.Priority.ZERO;

/**
 * @author Alex Geta
 */
public class OpeningBracket extends AbstractBinaryOperator {
    @Override
    protected Priority getPriority() {
        return ZERO;
    }

    @Override
    public Double calculate(Double leftOperand, Double rightOperand) {
        return 0d;
    }

}
