package com.teamdev.fsmcalc.mathcalc;

public interface MathExpressionCalculator {

    Double evaluate(String mathExpression) throws EvaluationException;
    Double[] evaluatePack(String expressions) throws EvaluationException;
}