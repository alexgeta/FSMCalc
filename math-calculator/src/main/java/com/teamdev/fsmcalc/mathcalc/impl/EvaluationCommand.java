package com.teamdev.fsmcalc.mathcalc.impl;

import com.teamdev.fsmcalc.mathcalc.EvaluationException;

public interface EvaluationCommand {
    void evaluate(EvaluationStack stack) throws EvaluationException;
}
