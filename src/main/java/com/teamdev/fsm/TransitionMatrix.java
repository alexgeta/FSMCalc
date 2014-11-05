package com.teamdev.fsm;

import java.util.Set;

public interface TransitionMatrix<State extends Enum> {

    State getStartState();

    State getFinishState();

    Set<State> getPossibleStates(State state);
}
