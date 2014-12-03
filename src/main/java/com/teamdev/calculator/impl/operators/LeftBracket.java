package com.teamdev.calculator.impl.operators;

import com.teamdev.calculator.impl.converter.OperatorVisitor;

import static com.teamdev.calculator.impl.operators.AbstractBinaryOperator.Priority.ZERO;

/**
 * @author Alex Geta
 */
public class LeftBracket extends AbstractBinaryOperator {
    @Override
    protected Priority getPriority() {
        return ZERO;
    }

    @Override
    public String getPresentation() {
        return "(";
    }

    @Override
    public double calculate(double leftOperand, double rightOperand) {
        return 0;
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}
