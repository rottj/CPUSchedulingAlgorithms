package com.company;

import java.util.Comparator;

public class CmpEntranceTime implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        return (int)(o1.getEntranceTime() - o2.getEntranceTime());
    }
}
