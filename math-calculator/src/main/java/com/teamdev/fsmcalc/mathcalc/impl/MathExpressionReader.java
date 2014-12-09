package com.teamdev.fsmcalc.mathcalc.impl;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MathExpressionReader {

    private final StringTokenizer tokenizer;
    private String currentToken;
    private int tokenPosition = 0;

    public MathExpressionReader(String mathExpression) {
        if(mathExpression.length() < 3) throw new IllegalArgumentException(
                "Expression must contain at least 3 char");
        final String DELIMITERS = " ,+-*/()^";
        this.tokenizer = new StringTokenizer( mathExpression, DELIMITERS, true);
        nextToken();
    }

    public String nextToken(){
        final String WHITE_SPACE = " ";
        try {
            currentToken = tokenizer.nextToken();
            while (currentToken.equals(WHITE_SPACE)) {
                currentToken = tokenizer.nextToken();
            }
            tokenPosition++;
        } catch (NoSuchElementException e) {
            currentToken = null;
        }
        return currentToken;
    }

    public String getCurrentToken(){
        return currentToken;
    }

    public int getTokenPosition() {
        return tokenPosition;
    }

    public boolean isEndOfExpression(){
        return currentToken == null;
    }

}
