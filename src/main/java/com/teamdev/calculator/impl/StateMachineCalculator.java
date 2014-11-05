package com.teamdev.calculator.impl;

import com.teamdev.calculator.EvaluationException;
import com.teamdev.calculator.MathExpressionCalculator;
import com.teamdev.calculator.impl.converter.InfixToPostfixConverter;
import com.teamdev.fsm.FiniteStateMachine;

public class StateMachineCalculator extends FiniteStateMachine<State, EvaluationContext, Double>
        implements MathExpressionCalculator {

    @Override
    public double evaluate(String mathExpression){

        final MathFunctionEvaluator functionEvaluator = new MathFunctionEvaluator();
        final String infixExpression = functionEvaluator.evaluate(mathExpression);
        final InfixToPostfixConverter converter = new InfixToPostfixConverter();
        final String postfixExpression = converter.convert(infixExpression);
        return run(new EvaluationContext(postfixExpression));
    }

    @Override
    protected void deadlock(EvaluationContext context, State currentState) {
        throw new IllegalStateException("Deadlock in state " + currentState + " at position " +
                context.getExpressionReader().getIndex());
    }

    @Override
    protected Double finish(EvaluationContext context) {
        return context.getEvaluationStack().getOperandStack().pop();
    }

    public static void main(String[] args) throws EvaluationException {

        if(args.length>1){
            throw new IllegalArgumentException("Spaces are not allowed in expression");
        }
        final String mathExpression = args[0];
        /*final String mathExpression = "2+3-(8-sum(2,2))/(2^min(2,3)*max(5,10))";
        final StateMachineCalculator calculator = new StateMachineCalculator();
        final double result = calculator.evaluate(mathExpression);
        System.out.println(mathExpression + " = " + result);*/

    }
}
