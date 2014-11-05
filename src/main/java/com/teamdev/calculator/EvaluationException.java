package com.teamdev.calculator;

public class EvaluationException extends Exception {

    final private int errorIndex;

    public EvaluationException(String message) {
        super(message);
        errorIndex=0;
    }

    public EvaluationException(String message, int errorIndex) {
        super(message);
        this.errorIndex = errorIndex;
    }



    public int getErrorIndex() {
        return errorIndex;
    }
}
