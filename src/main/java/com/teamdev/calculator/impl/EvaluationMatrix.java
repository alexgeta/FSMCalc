package com.teamdev.calculator.impl;

import com.teamdev.fsm.TransitionMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.teamdev.calculator.impl.State.*;
import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;

public class EvaluationMatrix implements TransitionMatrix<State> {

    private final Map<State, Set<State>> transitions = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER));
        put(NUMBER, of(BINARY_OPERATION, NUMBER, FINISH));
        put(BINARY_OPERATION, of(BINARY_OPERATION, NUMBER, FINISH));
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
