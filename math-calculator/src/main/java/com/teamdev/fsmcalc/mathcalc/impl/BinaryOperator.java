package com.teamdev.fsmcalc.mathcalc.impl;

public interface BinaryOperator extends Comparable<BinaryOperator> {

    Double calculate(Double leftOperand, Double rightOperand);

}
