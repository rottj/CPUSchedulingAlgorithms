package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Algorithms {

    public double simulate(ArrayList<Process> list, Comparator<Process> comparator, double processorTick,
                           double preemptiveTime, boolean whetherToPreempt, PreemptivePredicate preemptivePredicate, double whenStarved) {


        ArrayList<Process> processesQueue = new ArrayList<Process>(list);
        Collections.sort(processesQueue, new CmpEntranceTime());

        Process currentProcess = null;
        ArrayList<Process> finishedProcesses = new ArrayList<Process>();
        ArrayList<Process> waitingProcesses = new ArrayList<Process>();
        double currentTime = 0;

        while (finishedProcesses.size() != list.size()) {

            while (processesQueue.size() > 0 && currentTime >= processesQueue.get(0).getEntranceTime()) {

                Process process = processesQueue.remove(0);

                double initialWaitingTime = currentTime - process.getEntranceTime();
                process.setWaitingTime(initialWaitingTime);
                waitingProcesses.add(process);
            }


            if ((whetherToPreempt || currentProcess == null) && waitingProcesses.size() > 0) {

                Collections.sort(waitingProcesses, comparator);
                Process nextProces = waitingProcesses.get(0);

                if(currentProcess == null || (whetherToPreempt && preemptivePredicate.whetherToPreempt(currentProcess, nextProces))){

                    if(currentProcess != null) {
                        currentTime += preemptiveTime;
                        waitingProcesses.add(currentProcess);

                        for(int i=0; i<waitingProcesses.size(); i++) {
                            double waitingTime = waitingProcesses.get(i).getWaitingTime();
                            waitingProcesses.get(i).setWaitingTime(waitingTime + preemptiveTime);
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

            for(int i=0; i<waitingProcesses.size(); i++) {
                Process waitingProcess = waitingProcesses.get(i);
                double waitingTime = waitingProcess.getWaitingTime();
                waitingProcess.setWaitingTime(waitingTime + currentProcessorTick);
            }

        }
        return 0.0;

    }
}
