package com.teamdev.calculator.impl;

public class MathExpressionReader {

    private final String mathExpression;
    private int index = 0;

    public MathExpressionReader(String mathExpression) {
        this.mathExpression = mathExpression;
    }

    public String getMathExpression() {
        return mathExpression;
    }

    public char currentChar() {
        return mathExpression.charAt(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void incrementIndex(int value) {
        index += value;
    }

    public String getRemainingExpression() {
        return mathExpression.substring(index);
    }

    public boolean endOfExpression() {
        return index >= mathExpression.length();
    }

    public void skipWhitespaces() {
        while (!endOfExpression() && Character.isWhitespace(mathExpression.charAt(index))) {
            index++;
        }
    }
}
