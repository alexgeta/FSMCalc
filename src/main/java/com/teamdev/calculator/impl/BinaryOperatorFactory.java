package com.teamdev.calculator.impl;

import com.teamdev.calculator.impl.operators.*;

import java.util.HashMap;
import java.util.Map;

public class BinaryOperatorFactory {

    private final Map<String, BinaryOperator> registry = new HashMap<String, BinaryOperator>() {{
        put("+", new PlusBinaryOperator());
        put("-", new MinusBinaryOperator());
        put("*", new MultiplyBinaryOperator());
        put("/", new DivideBinaryOperator());
        put("^", new PowerBinaryOperator());
        put("(", new LeftBracket());
        put(")", new RightBracket());
    }};

    public BinaryOperator create(String operatorPresentation) {
        return registry.get(operatorPresentation);
    }

}
