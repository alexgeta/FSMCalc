package com.teamdev.fsmcalc.mathcalc.impl;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static com.google.common.base.Preconditions.checkArgument;

public class MathExpressionReader {

    private final StringTokenizer tokenizer;
    private int tokenPosition = 0;
    private String currentToken;

    public MathExpressionReader(String mathExpression) {
        checkArgument(mathExpression.length() > 2, "Expression must contain at least 3 char");
        final String DELIMITERS = " ,+-*/()^";
        tokenizer = new StringTokenizer(mathExpression, DELIMITERS, true);
        nextToken();
    }

    /*return null if no more tokens*/
    public String nextToken() {
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

    public String getCurrentToken() {
        return currentToken;
    }

    public int getTokenPosition() {
        return tokenPosition;
    }

    public boolean isEndOfExpression() {
        return currentToken == null;
    }

}
