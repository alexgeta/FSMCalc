package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.mathcalc.impl.operators.*;

import java.util.HashMap;
import java.util.Map;

public class BinaryOperatorFactory {

    private final Map<String, BinaryOperator> registry = new HashMap<String, BinaryOperator>() {{
        put("+", new PlusBinaryOperator());
        put("-", new MinusBinaryOperator());
        put("*", new MultiplyBinaryOperator());
        put("/", new DivideBinaryOperator());
        put("^", new PowerBinaryOperator());
    }};

    public BinaryOperator create(String operatorPresentation) {
        return registry.get(operatorPresentation);
    }

}
