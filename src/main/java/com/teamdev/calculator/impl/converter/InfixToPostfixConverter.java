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
 * @author Alex Geta
 */
public class InfixToPostfixConverter {

    private final StringBuilder output = new StringBuilder();
    private final BinaryOperatorFactory operatorFactory = new BinaryOperatorFactory();
    private final Deque<BinaryOperator> stack = new ArrayDeque<BinaryOperator>();
    private final OperatorVisitor visitor = new OperatorVisitor(this);
    final String WHITE_SPACE = " ";

    static Logger LOGGER = LoggerFactory.getLogger(InfixToPostfixConverter.class);

    public Deque<BinaryOperator> getStack() {
        return stack;
    }

    public StringBuilder getOutput() {
        return output;
    }

    public String convert(String mathExpression){
        final String DELIMITERS = " +-*/()^";

        final StringTokenizer tokenizer = new StringTokenizer(mathExpression, DELIMITERS, true);

        while (tokenizer.hasMoreTokens()){

            final String currentToken = nextToken(tokenizer);

            try {
                LOGGER.debug("trying to parse double value from token "+currentToken);
                Double value = Double.parseDouble(currentToken);
                output.append(value).append(WHITE_SPACE);
                LOGGER.info("double value "+value+" has successfully parsed and add to output string");
                continue;
            } catch (NumberFormatException e) {
                LOGGER.info("token "+currentToken+" is not a digital value");
            }

            final BinaryOperator binaryOperator = operatorFactory.create(currentToken);

            if(binaryOperator != null){
                LOGGER.debug("process binary operator "+binaryOperator.getClass().getSimpleName());
                binaryOperator.accept(visitor);
            }else throw new IllegalArgumentException("Unknown token "+"'"+currentToken+"'");

        }

        outputRemainingTokens();
        return output.toString();
    }

    private void outputRemainingTokens(){
        while (stack.peek()!=null){
            append(stack.pop());
        }
    }

    private void append(BinaryOperator operator){
        if(operator.getClass() != LeftBracket.class){
            output.append(operator.getPresentation()).append(WHITE_SPACE);
        }else throw new IllegalArgumentException("Closing bracket missed");
    }

    private String nextToken(StringTokenizer tokenizer){
        String nextToken = tokenizer.nextToken();
        while (nextToken.equals(WHITE_SPACE)){
            nextToken = tokenizer.nextToken();
        }
        return nextToken;
    }
}
