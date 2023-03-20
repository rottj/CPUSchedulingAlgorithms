package com.company;

import java.util.Comparator;

public class CmpEntranceTimePhaseLEngth implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        if(o1.getEntranceTime() == o2.getEntranceTime()) {
            return (int) (o1.getremainingTime() - o2.getremainingTime());
        }
        else {
            return (int) (o1.getEntranceTime() - o2.getEntranceTime());
        }
    }
}
