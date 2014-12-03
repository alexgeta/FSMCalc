package com.teamdev.calculator.impl.parser;

import com.teamdev.calculator.impl.*;

/**
 * @author Alex Geta
 */
public class BinaryOperationParser implements MathExpressionParser {

    @Override
    public EvaluationCommand parse(EvaluationContext context) {

        if (context.getExpressionReader().endOfExpression()) {
            return null;
        }

        final MathExpressionReader expressionReader = context.getExpressionReader();
        final BinaryOperatorFactory factory = context.getBinaryOperatorFactory();
        final String currentToken = String.valueOf(expressionReader.currentChar());
        final BinaryOperator binaryOperator = factory.create(currentToken);

        if (binaryOperator == null) {
            return null;
        }

        expressionReader.incrementIndex(1);

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) {
                Double[] operands = getTwoLastOperands(stack);
                Double result = binaryOperator.calculate(operands[0], operands[1]);
                stack.getOperandStack().push(result);
            }
        };

    }

    private Double[] getTwoLastOperands(EvaluationStack stack) {
        Double[] operands = new Double[2];
        operands[1] = stack.getOperandStack().pop();
        operands[0] = stack.getOperandStack().pop();
        return operands;
    }

}
