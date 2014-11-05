package com.teamdev.calculator.impl.operators;

import com.teamdev.calculator.impl.converter.OperatorVisitor;

import static com.teamdev.calculator.impl.operators.AbstractBinaryOperator.Priority.HIGH;

public class PowerBinaryOperator extends AbstractBinaryOperator {

    @Override
    public String getPresentation() {
        return "^";
    }

    @Override
    protected Priority getPriority() {
        return HIGH;
    }

    @Override
    public double calculate(double leftOperand, double rightOperand) {
        return Math.pow(leftOperand, rightOperand);
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}

