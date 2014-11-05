package com.teamdev.calculator.impl.operators;

import com.teamdev.calculator.impl.converter.OperatorVisitor;

/**
 * @author Alex Geta
 */
public class RightBracket extends AbstractBinaryOperator{
    @Override
    protected Priority getPriority() {
        return null;
    }

    @Override
    public String getPresentation() {
        return ")";
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
