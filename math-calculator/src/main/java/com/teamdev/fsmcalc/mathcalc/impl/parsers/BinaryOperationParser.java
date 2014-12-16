package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.impl.*;
import com.teamdev.fsmcalc.mathcalc.impl.operators.PowerBinaryOperator;

import java.util.Deque;

/**
 * @author Alex Geta
 */
public class BinaryOperationParser implements MathTokenParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final BinaryOperatorFactory operatorFactory = context.getBinaryOperatorFactory();
        final String currentToken = expressionReader.getCurrentToken();
        final BinaryOperator currentBinaryOperator = operatorFactory.create(currentToken);
        if (currentBinaryOperator == null) return null;

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                final Deque<BinaryOperator> operatorsStack = stack.getOperatorsStack();
                final Deque<Double> operandStack = stack.getOperandStack();
                /*due to right precedence of power binary operator,
                comparative difference must be 0 only in this case*/
                int difference = currentBinaryOperator instanceof PowerBinaryOperator ? 0 : -1;

                while (!operatorsStack.isEmpty() &&
                        operatorsStack.peek().compareTo(currentBinaryOperator) > difference) {
                    final BinaryOperator topOfStackOperator = operatorsStack.pop();
                    final Double rightOperand = operandStack.pop();
                    final Double leftOperand = operandStack.pop();
                    final Double result = topOfStackOperator.calculate(leftOperand, rightOperand);
                    operandStack.push(result);
                }
                operatorsStack.push(currentBinaryOperator);
            }
        };

    }

}
