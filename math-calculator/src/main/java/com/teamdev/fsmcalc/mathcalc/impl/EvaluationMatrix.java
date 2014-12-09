package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.fsm.TransitionMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;
import static com.teamdev.fsmcalc.mathcalc.impl.State.*;

public class EvaluationMatrix implements TransitionMatrix<State> {

    private final Map<State, Set<State>> transitions = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER, MATH_FUNCTION, OPENING_BRACKET));
        put(NUMBER, of(BINARY_OPERATION, ARGUMENT_SEPARATOR, CLOSING_BRACKET, FINISH));
        put(BINARY_OPERATION, of(NUMBER, MATH_FUNCTION, OPENING_BRACKET));
        put(MATH_FUNCTION, of(OPENING_BRACKET));
        put(ARGUMENT_SEPARATOR, of(NUMBER, MATH_FUNCTION));
        put(OPENING_BRACKET, of(NUMBER, MATH_FUNCTION));
        put(CLOSING_BRACKET, of(BINARY_OPERATION, CLOSING_BRACKET, ARGUMENT_SEPARATOR, FINISH));
        put(FINISH, noneOf(State.class));
    }};

    @Override
    public State getStartState() {
        return START;
    }

    @Override
    public State getFinishState() {
        return FINISH;
    }

    @Override
    public Set<State> getPossibleStates(State state) {
        return transitions.get(state);
    }
}
