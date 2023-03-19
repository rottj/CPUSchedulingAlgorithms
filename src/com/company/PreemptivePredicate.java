package com.company;

public interface PreemptivePredicate {
    boolean whetherToPreempt (Process currentProcess, Process nextProcess);
}
