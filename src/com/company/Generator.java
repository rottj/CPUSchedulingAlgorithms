package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public double[] generate(int numberOFProcesses, int timeRR) throws CloneNotSupportedException {

        ArrayList<Process> processes_1 = generateProcesses(numberOFProcesses);
        ArrayList<Process> processes_2 = copyProcesses(processes_1);
        ArrayList<Process> processes_3 = copyProcesses(processes_1);
        ArrayList<Process> processes_4 = copyProcesses(processes_1);
        ArrayList<Process> processes_5 = copyProcesses(processes_1);

        Algorithms test = new Algorithms();

        double fcfs = test.FCFS(processes_2);
        double sjf = test.SJF(processes_3);
        double sjfw = test.SJFPreemptive(processes_4);
        double rr = test.RR(processes_5, timeRR);

        double[] result = new double[4];

        result[0] = fcfs;
        result[1] = sjf;
        result[2] = sjfw;
        result[3] = rr;

        return result;
    }

    private ArrayList<Process> generateProcesses(int numberOfProcesses)
    {
        ArrayList<Process> processes = new ArrayList<>();
        Random r = new Random();
        for(int i =0; i<numberOfProcesses; i++) {
            processes.add(new Process(r.nextInt(1000), r.nextInt(1000)));
        }
        return processes;
    }

    private ArrayList<Process> copyProcesses(ArrayList<Process> processes) throws CloneNotSupportedException{

        ArrayList<Process> copiedProcesses = new ArrayList<>();
        for (Process process : processes) {
            copiedProcesses.add((Process) process.clone());
        }

        return copiedProcesses;
    }
}
