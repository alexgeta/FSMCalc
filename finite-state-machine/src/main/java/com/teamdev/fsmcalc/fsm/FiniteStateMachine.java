package com.teamdev.fsmcalc.fsm;

public abstract class FiniteStateMachine<State extends Enum,
        Context extends StateMachineContext<State, Context, FSMException>,
        Result,
        FSMException extends Exception> {

    public Result run(Context context) throws FSMException {

        final TransitionMatrix<State> matrix = context.getTransitionMatrix();
        State currentState = matrix.getStartState();

        while (currentState != matrix.getFinishState()) {

            final State nextState = moveForward(context, currentState);
            if (nextState == null) {
                deadlock(context, currentState);
            }
            currentState = nextState;
        }

        return finish(context);
    }

    private State moveForward(Context context, State currentState) throws FSMException {

        final StateAcceptor<State, Context, FSMException> stateAcceptor = context.getStateAcceptor();
        final TransitionMatrix<State> matrix = context.getTransitionMatrix();

        for (State possibleState : matrix.getPossibleStates(currentState)) {
            if (stateAcceptor.acceptState(context, possibleState)) {
                return possibleState;
            }
        }
        return null;
    }

    abstract protected void deadlock(Context context, State currentState) throws FSMException;

    abstract protected Result finish(Context context);
}
