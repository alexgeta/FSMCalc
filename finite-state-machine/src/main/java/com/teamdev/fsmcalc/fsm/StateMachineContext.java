package com.teamdev.fsmcalc.fsm;

public interface StateMachineContext<State extends Enum,
        Context extends StateMachineContext<State, Context, FSMException>,
        FSMException extends Exception> {

    TransitionMatrix<State> getTransitionMatrix();

    StateAcceptor<State, Context, FSMException> getStateAcceptor();
}
