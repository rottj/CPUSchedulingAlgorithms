package com.company;

public class PreemptivePredicateRR implements PreemptivePredicate{
    @Override
    public boolean whetherToPreempt(Process currentProcess, Process nextProcess) {
        return true;
    }
}
