package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.converter.OperatorVisitor;

public interface BinaryOperator extends Comparable<BinaryOperator> {
    String getPresentation();
    double calculate(double leftOperand, double rightOperand);
    void accept(OperatorVisitor visitor);
}
