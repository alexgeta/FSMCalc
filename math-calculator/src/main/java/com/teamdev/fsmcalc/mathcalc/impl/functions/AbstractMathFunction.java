package com.teamdev.fsmcalc.mathcalc.impl.functions;

import com.teamdev.fsmcalc.mathcalc.impl.MathFunction;

/**
 * @author Alex Geta
 */
public abstract class AbstractMathFunction implements MathFunction {

    private int argsCounter = 0;

    public void incrementArgsCounter() {
        argsCounter++;
    }

    public int getArgsCounter() {
        return argsCounter;
    }
}
