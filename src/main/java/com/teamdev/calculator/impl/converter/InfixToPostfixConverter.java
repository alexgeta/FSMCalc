package com.teamdev.calculator.impl.converter;

import com.teamdev.calculator.impl.BinaryOperator;
import com.teamdev.calculator.impl.BinaryOperatorFactory;
import com.teamdev.calculator.impl.operators.LeftBracket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Converter from infix to postfix notation.
 *
 * @author Alex Geta
 */
public class InfixToPostfixConverter {

    static Logger LOGGER = LoggerFactory.getLogger(InfixToPostfixConverter.class);
    final String WHITE_SPACE = " ";
    private final StringBuilder result = new StringBuilder();
    private final BinaryOperatorFactory operatorFactory = new BinaryOperatorFactory();
    private final Deque<BinaryOperator> operatorsStack = new ArrayDeque<BinaryOperator>();
    private final OperatorVisitor visitor = new OperatorVisitor(result, operatorsStack);

    /**
     * Converts math expression from infix to postfix notation
     *
     * @param mathExpression string in infix notation.
     * @return result string in postfix notation.
     */
    public String convert(String mathExpression) {
        final String DELIMITERS = " +-*/()^";
        final StringTokenizer tokenizer = new StringTokenizer(mathExpression, DELIMITERS, true);

        while (tokenizer.hasMoreTokens()) {
            final String currentToken = nextToken(tokenizer);
            try {
                LOGGER.debug("trying to parse double value from token " + currentToken);
                Double value = Double.parseDouble(currentToken);
                result.append(value).append(WHITE_SPACE);
                LOGGER.info("double value " + value + " has successfully parsed and added to result string");
                continue;
            } catch (NumberFormatException e) {
                LOGGER.info("token " + currentToken + " is not a digital value");
            }
            final BinaryOperator binaryOperator = operatorFactory.create(currentToken);
            if (binaryOperator != null) {
                LOGGER.debug("process binary operator " + binaryOperator.getClass().getSimpleName());
                binaryOperator.accept(visitor);
            } else throw new IllegalArgumentException("Unknown token \"" + currentToken + "\"");
        }
        outputRemainingTokens();
        return result.toString();
    }

    private void outputRemainingTokens() {
        while (!operatorsStack.isEmpty()) {
            append(operatorsStack.pop());
        }
    }

    /*checks for unclosed bracket and append operator to result string*/
    private void append(BinaryOperator operator) {
        if (operator.getClass() != LeftBracket.class) {
            result.append(operator.getPresentation()).append(WHITE_SPACE);
        } else throw new IllegalArgumentException("Closing bracket missed");
    }

    /*skip white spaces and get next token*/
    private String nextToken(StringTokenizer tokenizer) {
        String nextToken = tokenizer.nextToken();
        while (nextToken.equals(WHITE_SPACE)) {
            nextToken = tokenizer.nextToken();
        }
        return nextToken;
    }
}
