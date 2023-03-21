package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        ArrayList<Process> processes_1 = generateProcesses(1000);
        ArrayList<Process> processes_2 = copyProcesses(processes_1);
        ArrayList<Process> processes_3 = copyProcesses(processes_1);
        ArrayList<Process> processes_4 = copyProcesses(processes_1);
        ArrayList<Process> processes_5 = copyProcesses(processes_1);


        Algorithms test = new Algorithms();

        double fcfs = test.FCFS(processes_2);
        System.out.printf("fcfs: " + fcfs);
        System.out.println();
        double sjf = test.SJF(processes_3);
        System.out.println("sjf: " + sjf);
        double sjfw = test.SJFPreemptive(processes_4);
        System.out.println("sjfw: " + sjfw);
        double rr = test.RR(processes_5, 1);
        System.out.println("rr: " + rr);

    }

    private static ArrayList<Process> generateProcesses(int numberOfProcesses)
    {
        ArrayList<Process> processes = new ArrayList<Process>();
        Random r = new Random();
        for(int i =0; i<numberOfProcesses; i++) {
            processes.add(new Process(r.nextInt(1000), r.nextInt(1000)));
        }
        return processes;
    }

    private static ArrayList<Process> copyProcesses(ArrayList<Process> processes) throws CloneNotSupportedException{

        ArrayList<Process> copiedProcesses = new ArrayList<Process>();
        Iterator<Process> iterator = processes.iterator();

        while(iterator.hasNext()){
            copiedProcesses.add((Process) iterator.next().clone());
        }

        return copiedProcesses;
    }

    private static void printProcesses(ArrayList<Process> processes) {
        for(int i=0; i<processes.size(); i++) {
            System.out.println(processes.get(i));
        }
    }

}
