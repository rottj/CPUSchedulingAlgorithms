package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class Algorithms {

    public double FCFS(ArrayList<Process> lista) {
        return simulate(lista, new CmpEntranceTime(), 1, 0, false, null, 0);
    }

    public double SJF(ArrayList<Process> lista) {
        return simulate(lista, new CmpPhaseLength(), 1, 0, false, null, 1000);
    }

    public double SJFPreemptive(ArrayList<Process> lista) {
        return simulate(lista, new CmpPhaseLength(), 1, 0.1, true, new PreemptivePredicateSJF(), 300000);
    }

    public double RR(ArrayList<Process> lista, double kwantCzasu) {
        return simulate(lista, new CmpRR(), kwantCzasu, 0.1, true, new PreemptivePredicateRR(), 0);
    }


    public double simulate(ArrayList<Process> list, Comparator<Process> comparator, double processorTick,
                           double preemptiveTime, boolean whetherToPreempt, PreemptivePredicate preemptivePredicate, double whenStarved) {

        ArrayList<Process> processesQueue = new ArrayList<>(list);
        processesQueue.sort(new CmpEntranceTime());

        Process currentProcess = null;
        ArrayList<Process> finishedProcesses = new ArrayList<>();
        ArrayList<Process> waitingProcesses = new ArrayList<>();
        double currentTime = 0;

        while (finishedProcesses.size() != list.size()) {

            while (processesQueue.size() > 0 && currentTime >= processesQueue.get(0).getEntranceTime()) {

                Process process = processesQueue.remove(0);

                double initialWaitingTime = currentTime - process.getEntranceTime();
                process.setWaitingTime(initialWaitingTime);
                waitingProcesses.add(process);
            }


            if ((whetherToPreempt || currentProcess == null) && waitingProcesses.size() > 0) {

                waitingProcesses.sort(comparator);
                Process nextProces = waitingProcesses.get(0);

                if(currentProcess == null || preemptivePredicate.whetherToPreempt(currentProcess, nextProces)){

                    if(currentProcess != null) {
                        currentTime += preemptiveTime;
                        waitingProcesses.add(currentProcess);

                        for (Process waitingProcess : waitingProcesses) {
                            double waitingTime = waitingProcess.getWaitingTime();
                            waitingProcess.setWaitingTime(waitingTime + preemptiveTime);
                        }
                    }
                    currentProcess = waitingProcesses.remove(0);
                }
            }

            double currentProcessorTick = processorTick;

            if (currentProcess != null) {

                double remainingCurrentProcessTime = (currentProcess.getremainingTime() - currentProcessorTick);
                if(remainingCurrentProcessTime < 0) {

                    currentProcessorTick += remainingCurrentProcessTime;
                    remainingCurrentProcessTime = 0;
                }

                currentProcess.setRemainingTime(remainingCurrentProcessTime);

                if(remainingCurrentProcessTime == 0) {

                    finishedProcesses.add(currentProcess);
                    currentProcess = null;
                }
            }

            currentTime += currentProcessorTick;

            for (Process waitingProcess : waitingProcesses) {
                double waitingTime = waitingProcess.getWaitingTime();
                waitingProcess.setWaitingTime(waitingTime + currentProcessorTick);
            }

        }
        double waitingTime = 0;
        double longestWaitingTime = 0;
        int numberOfStarved = 0;
        for (Process finishedProcess : finishedProcesses) {
            if (finishedProcess.getWaitingTime() > longestWaitingTime) {
                longestWaitingTime = finishedProcess.getWaitingTime();
            }
            if (comparator instanceof CmpPhaseLength && finishedProcess.getWaitingTime() >= whenStarved) {
                numberOfStarved++;
            }
            waitingTime += finishedProcess.getWaitingTime();
        }
        System.out.println("The longest waiting time: " + longestWaitingTime);
        if(comparator instanceof CmpPhaseLength) {
            System.out.println("The number of starved: " + numberOfStarved);
        }
        return waitingTime/finishedProcesses.size();

    }
}
