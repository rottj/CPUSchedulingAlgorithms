package com.company;

public class PreemptivePredicateSJF implements PreemptivePredicate{
    @Override
    public boolean whetherToPreempt(Process currentProcess, Process nextProcess) {
        return currentProcess.getremainingTime() > nextProcess.getremainingTime();
    }
}
