package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.impl.*;

import java.util.Deque;
import java.util.NoSuchElementException;

public class EndOfExpressionParser implements MathTokenParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        if (!context.getExpressionReader().isEndOfExpression()) return null;

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                final Deque<BinaryOperator> operatorsStack = stack.getOperatorsStack();
                final Deque<Double> operandStack = stack.getOperandStack();
                while (!operatorsStack.isEmpty()){
                    final BinaryOperator topOfStackOperator = operatorsStack.pop();
                    final Double rightOperand;
                    final Double leftOperand;
                    try {
                        rightOperand = operandStack.pop();
                        leftOperand = operandStack.pop();
                    } catch (NoSuchElementException e) {
                        throw new IllegalArgumentException("Closing bracket missed");
                    }
                    final Double result = topOfStackOperator.calculate(leftOperand, rightOperand);
                    operandStack.push(result);
                }
            }
        };
    }

}
