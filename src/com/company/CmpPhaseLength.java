package com.company;

import java.util.Comparator;

public class CmpPhaseLength implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return (int) (o1.getremainingTime() - o2.getremainingTime());
    }
}
