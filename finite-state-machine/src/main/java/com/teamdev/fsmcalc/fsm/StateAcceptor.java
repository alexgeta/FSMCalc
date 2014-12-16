package com.teamdev.fsmcalc.fsm;

public interface StateAcceptor<State extends Enum,
        Context extends StateMachineContext<State, Context, FSMException>,
        FSMException extends Exception> {

    boolean acceptState(Context context, State possibleState) throws FSMException;
}
