package com.teamdev.fsmcalc.mathcalc.impl.parsers;

import com.teamdev.fsmcalc.mathcalc.EvaluationException;
import com.teamdev.fsmcalc.mathcalc.impl.*;
import com.teamdev.fsmcalc.mathcalc.impl.functions.AbstractMathFunction;
import com.teamdev.fsmcalc.mathcalc.impl.operators.OpeningBracket;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import static com.google.common.base.Objects.equal;

/**
 * @author Alex Geta
 */
public class ClosingBracketParser implements MathTokenParser {

    @Override
    public EvaluationCommand parse(final EvaluationContext context) {

        final String CLOSING_BRACKET = ")";
        final MathExpressionReader expressionReader = context.getExpressionReader();
        final String currentToken = expressionReader.getCurrentToken();
        if (!equal(currentToken, CLOSING_BRACKET)) return null;

        return new EvaluationCommand() {
            @Override
            public void evaluate(EvaluationStack stack) throws EvaluationException {

                if (!stack.getFunctionsStack().isEmpty()) {
                    processMathFunction(stack);
                    return;
                }

                try {
                    final Deque<Double> operandStack = stack.getOperandStack();
                    final Deque<BinaryOperator> operatorsStack = stack.getOperatorsStack();
                    BinaryOperator topOfStackOperator = operatorsStack.pop();
                    while (topOfStackOperator.getClass() != OpeningBracket.class) {
                        final Double rightOperand = operandStack.pop();
                        final Double leftOperand = operandStack.pop();
                        final Double result = topOfStackOperator.calculate(leftOperand, rightOperand);
                        operandStack.push(result);
                        topOfStackOperator = operatorsStack.pop();
                    }
                } catch (NoSuchElementException e) {
                    throw new EvaluationException("Opening bracket missed");
                }
            }
        };
    }

    private void processMathFunction(EvaluationStack stack) {
        final MathFunction mathFunction = stack.getFunctionsStack().pop();
        final Deque<Double> operandStack = stack.getOperandStack();
        final List<Double> functionArguments = new ArrayList<Double>();
        final int argumentsAmount = ((AbstractMathFunction) mathFunction).getArgsCounter();
        for (int i = 0; i < argumentsAmount; i++) {
            functionArguments.add(operandStack.pop());
        }
        final Double result = mathFunction.calculate(functionArguments);
        operandStack.push(result);
    }

}
