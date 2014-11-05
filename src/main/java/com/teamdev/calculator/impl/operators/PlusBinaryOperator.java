package com.teamdev.calculator.impl.operators;

import com.teamdev.calculator.impl.converter.OperatorVisitor;

import static com.teamdev.calculator.impl.operators.AbstractBinaryOperator.Priority.LOW;

public class PlusBinaryOperator extends AbstractBinaryOperator {

    @Override
    public String getPresentation() {
        return "+";
    }

    @Override
    protected Priority getPriority() {
        return LOW;
    }

    @Override
    public double calculate(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}

