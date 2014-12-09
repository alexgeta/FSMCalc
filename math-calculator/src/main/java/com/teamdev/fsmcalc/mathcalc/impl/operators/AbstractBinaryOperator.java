package com.teamdev.fsmcalc.mathcalc.impl.operators;

import com.teamdev.fsmcalc.mathcalc.impl.BinaryOperator;

abstract public class AbstractBinaryOperator implements BinaryOperator {

    protected abstract Priority getPriority();

    @Override
    public int compareTo(BinaryOperator binaryOperator) {

        final AbstractBinaryOperator other = (AbstractBinaryOperator) binaryOperator;
        return getPriority().compareTo(other.getPriority());

    }

    static enum Priority {
        ZERO,
        LOW,
        MEDIUM,
        HIGH
    }
}
