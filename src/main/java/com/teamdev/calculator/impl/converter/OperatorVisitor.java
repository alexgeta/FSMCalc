package com.teamdev.calculator.impl.converter;

import com.teamdev.calculator.impl.BinaryOperator;
import com.teamdev.calculator.impl.operators.*;

import java.util.NoSuchElementException;

/**
 * @author Alex Geta
 */
public class OperatorVisitor{

    private final InfixToPostfixConverter converter;
    private final String WHITE_SPACE = " ";

    public OperatorVisitor(InfixToPostfixConverter converter) {
        this.converter = converter;
    }

    public void visit(DivideBinaryOperator operator){
        processBinaryOperator(operator);
    }

    public void visit(MinusBinaryOperator operator){
        processBinaryOperator(operator);
    }

    public void visit(MultiplyBinaryOperator operator){
        processBinaryOperator(operator);
    }

    public void visit(PlusBinaryOperator operator){
        processBinaryOperator(operator);
    }

    public void visit(PowerBinaryOperator operator){

        BinaryOperator topOfStackOperator = converter.getStack().peek();

        while (topOfStackOperator != null && topOfStackOperator.compareTo(operator)>0){
            converter.getOutput().append(topOfStackOperator.getPresentation()).append(WHITE_SPACE);
            converter.getStack().pop();
            topOfStackOperator = converter.getStack().peek();
        }
        converter.getStack().push(operator);

    }

    public void visit(LeftBracket operator){
        converter.getStack().push(operator);
    }

    public void visit(RightBracket operator){

        BinaryOperator currentOperator = converter.getStack().pop();

        try {
            while (currentOperator.getClass() != LeftBracket.class){
                converter.getOutput().append(currentOperator.getPresentation()).append(WHITE_SPACE);
                currentOperator = converter.getStack().pop();
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Open bracket missed");
        }
    }

    private void processBinaryOperator(BinaryOperator operator){

        BinaryOperator topOfStackOperator = converter.getStack().peek();

        while (topOfStackOperator != null && topOfStackOperator.compareTo(operator)>-1){
            converter.getOutput().append(topOfStackOperator.getPresentation()).append(" ");
            converter.getStack().pop();
            topOfStackOperator = converter.getStack().peek();
        }
        converter.getStack().push(operator);
    }

}
