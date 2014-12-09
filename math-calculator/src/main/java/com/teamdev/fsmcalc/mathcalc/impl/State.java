package com.teamdev.fsmcalc.mathcalc.impl;

public enum State {
    START,
    NUMBER,
    BINARY_OPERATION,
    MATH_FUNCTION,
    ARGUMENT_SEPARATOR,
    OPENING_BRACKET,
    CLOSING_BRACKET,
    FINISH,
}
