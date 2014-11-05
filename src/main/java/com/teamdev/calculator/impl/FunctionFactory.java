package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.functions.MaximumFunction;
import com.teamdev.calculator.impl.functions.MinimumFunction;
import com.teamdev.calculator.impl.functions.SumFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Geta
 */
public class FunctionFactory {

    private final Map<String, MathFunction> functions = new HashMap<String, MathFunction>() {{
        put("min", new MinimumFunction());
        put("max", new MaximumFunction());
        put("sum", new SumFunction());

    }};

    public MathFunction create(String functionName) {
        return functions.get(functionName);
    }
}
