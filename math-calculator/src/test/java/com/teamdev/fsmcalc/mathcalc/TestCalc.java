package com.teamdev.fsmcalc.mathcalc;

import com.teamdev.fsmcalc.mathcalc.impl.StateMachineCalculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

/**
 * @author Alex Geta
 */
public class TestCalc {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPlusOperation() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 3.25;
        final double actual = calculator.evaluate("1.25+2");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testMinusOperation() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 1.5;
        final double actual = calculator.evaluate("2.5-1");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testMultiplyOperation() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 3;
        final double actual = calculator.evaluate("1.5*2");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testDivideOperation() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 1.75;
        final double actual = calculator.evaluate("3.5/2");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testPowerOperation() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 512;
        final double actual = calculator.evaluate("2^3^2");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testOperatorPrecedence() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 3074;
        final double actual = calculator.evaluate("2+3*4^5");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testMinFunction() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 2;
        final double actual = calculator.evaluate("min(3,2)");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testMaxFunction() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 3;
        final double actual = calculator.evaluate("max(3,2)");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testSumFunction() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 5;
        final double actual = calculator.evaluate("sum(3,2)");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testSqrtFunction() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 3;
        final double actual = calculator.evaluate("sqrt(9)");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testInnerFunctions() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 8;
        final double actual = calculator.evaluate("sum( min(2,4,5), sqrt(9), max(sqrt(4),3,1) )");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testOverallCorrectResult() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        final double expected = 4.9;
        final double actual = calculator.evaluate("2+3-(8-sum(sqrt(4),2))/(2^min(2,3)*max(5,10))");
        assertTrue("expected " + expected + " but was " + actual, actual == expected);
    }

    @Test
    public void testMissedOpenBracket() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Opening bracket missed");
        calculator.evaluate("2+2-1)");
    }

    @Test
    public void testMissedClosingBracket() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Closing bracket missed");
        calculator.evaluate("2+(2-1");
    }

    @Test
    public void testUnknownToken() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Not allowed token \":\" at position 2");
        calculator.evaluate("2 : 1)");
    }

    @Test
    public void testInvalidTokensCount() {
        final MathExpressionCalculator calculator = new StateMachineCalculator();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Expression must contain at least 3 char");
        calculator.evaluate("2+");

    }
}
