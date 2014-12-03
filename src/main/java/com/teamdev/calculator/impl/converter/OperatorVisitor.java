package com.teamdev.calculator.impl.converter;

import com.teamdev.calculator.impl.BinaryOperator;
import com.teamdev.calculator.impl.operators.*;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Visitor for binary operators.
 *
 * @author Alex Geta
 */
public class OperatorVisitor {

    private final StringBuilder result;
    private final Deque<BinaryOperator> operatorsStack;
    private final String WHITE_SPACE = " ";

    public OperatorVisitor(StringBuilder result, Deque<BinaryOperator> operatorsStack) {
        this.result = result;
        this.operatorsStack = operatorsStack;
    }

    public void visit(DivideBinaryOperator operator) {
        processBinaryOperator(operator);
    }

    public void visit(MinusBinaryOperator operator) {
        processBinaryOperator(operator);
    }

    public void visit(MultiplyBinaryOperator operator) {
        processBinaryOperator(operator);
    }

    public void visit(PlusBinaryOperator operator) {
        processBinaryOperator(operator);
    }

    public void visit(PowerBinaryOperator operator) {
        BinaryOperator topOfStackOperator = operatorsStack.peek();
        while (topOfStackOperator != null && topOfStackOperator.compareTo(operator) > 0) {
            result.append(topOfStackOperator.getPresentation()).append(WHITE_SPACE);
            operatorsStack.pop();
            topOfStackOperator = operatorsStack.peek();
        }
        operatorsStack.push(operator);
    }

    public void visit(LeftBracket operator) {
        operatorsStack.push(operator);
    }

    public void visit(RightBracket operator) {
        BinaryOperator topOfStackOperator = operatorsStack.pop();
        try {
            while (topOfStackOperator.getClass() != LeftBracket.class) {
                result.append(topOfStackOperator.getPresentation()).append(WHITE_SPACE);
                topOfStackOperator = operatorsStack.pop();
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Opening bracket missed");
        }
    }

    private void processBinaryOperator(BinaryOperator operator) {
        BinaryOperator topStackOperator = operatorsStack.peek();
        while (topStackOperator != null && topStackOperator.compareTo(operator) > -1) {
            result.append(topStackOperator.getPresentation()).append(" ");
            operatorsStack.pop();
            topStackOperator = operatorsStack.peek();
        }
        operatorsStack.push(operator);
    }

}
