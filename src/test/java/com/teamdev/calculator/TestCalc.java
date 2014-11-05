package com.teamdev.calculator;

import com.teamdev.calculator.impl.StateMachineCalculator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Alex Geta
 */
public class TestCalc {

    private final StateMachineCalculator calculator = new StateMachineCalculator();

    @Test
    public void testPlusOperation() {
        final double expected = 3.25;
        final double actual = calculator.evaluate("1.25+2");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testMinusOperation() {
        final double expected = 1.5;
        final double actual = calculator.evaluate("2.5-1");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testMultiplyOperation() {
        final double expected = 3;
        final double actual = calculator.evaluate("1.5*2");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testDivideOperation() {
        final double expected = 1.75;
        final double actual = calculator.evaluate("3.5/2");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testPowerOperation(){
        final double expected = 512;
        final double actual = calculator.evaluate("2^3^2");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testOperatorPrecedence() {
        final double expected = 3074;
        final double actual = calculator.evaluate("2+3*4^5");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testMinFunction(){
        final double expected = 2;
        final double actual = calculator.evaluate("min(3,2)");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testMaxFunction(){
        final double expected = 3;
        final double actual = calculator.evaluate("max(3,2)");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testSumFunction(){
        final double expected = 5;
        final double actual = calculator.evaluate("sum(3,2)");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test
    public void testOverallCorrectResult(){
        final double expected = 4.9;
        final double actual = calculator.evaluate("2+3-(8-sum(2,2))/(2^min(2,3)*max(5,10))");
        assertTrue("expected "+expected+" but was "+actual, actual == expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissedOpenBracket(){
        calculator.evaluate("2+2-1)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissedClosingBracket(){
        calculator.evaluate("2+(2-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownToken(){
        calculator.evaluate("2+(2;1)");
    }
}
