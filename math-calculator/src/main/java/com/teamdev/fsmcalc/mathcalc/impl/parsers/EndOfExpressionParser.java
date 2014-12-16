package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.EvaluationException;
import com.teamdev.fsmcalc.mathcalc.impl.*;

import java.util.Deque;
import java.util.NoSuchElementException;

public class EndOfExpressionParser implements MathTokenParser {

    @Override
    public EvaluationCommand parse(final EvaluationContext context) {

        if (!context.getExpressionReader().isEndOfExpression()) return null;

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) throws EvaluationException {
                final Deque<BinaryOperator> operatorsStack = stack.getOperatorsStack();
                final Deque<Double> operandStack = stack.getOperandStack();
                while (!operatorsStack.isEmpty()) {
                    final BinaryOperator topOfStackOperator = operatorsStack.pop();
                    try {
                        final Double rightOperand = operandStack.pop();
                        final Double leftOperand = operandStack.pop();
                        final Double result = topOfStackOperator.calculate(leftOperand, rightOperand);
                        operandStack.push(result);
                    } catch (NoSuchElementException e) {
                        throw new EvaluationException("Closing bracket missed");
                    }
                }
            }
        };
    }

}
