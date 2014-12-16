package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.mathcalc.impl.functions.MaximumFunction;
import com.teamdev.fsmcalc.mathcalc.impl.functions.MinimumFunction;
import com.teamdev.fsmcalc.mathcalc.impl.functions.SqrtMathFunction;
import com.teamdev.fsmcalc.mathcalc.impl.functions.SumFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Geta
 */
public class MathFunctionFactory {

    private final Map<String, FunctionCreator> functions = new HashMap<String, FunctionCreator>() {{
        put("min", new FunctionCreator() {
            @Override
            public MathFunction createFunction() {
                return new MinimumFunction();
            }
        });
        put("max", new FunctionCreator() {
            @Override
            public MathFunction createFunction() {
                return new MaximumFunction();
            }
        });
        put("sum", new FunctionCreator() {
            @Override
            public MathFunction createFunction() {
                return new SumFunction();
            }
        });
        put("sqrt", new FunctionCreator() {
            @Override
            public MathFunction createFunction() {
                return new SqrtMathFunction();
            }
        });
    }};

    public MathFunction create(String functionName) {
        final FunctionCreator functionCreator = functions.get(functionName);
        return functionCreator != null ? functionCreator.createFunction() : null;
    }

    private interface FunctionCreator {
        MathFunction createFunction();
    }
}
