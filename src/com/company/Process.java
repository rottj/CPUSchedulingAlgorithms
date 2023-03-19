package com.company;

public class Process implements Cloneable {

    private double phaseLength;
    private double entranceTime;
    private double remainingTime;
    private double waitingTime;

    public Process(double entranceTime, double phaseLength) {
        this.phaseLength = phaseLength;
        this.entranceTime = entranceTime;
        remainingTime = phaseLength;
        waitingTime = 0;
    }

    public double getEhaseLength() {
        return phaseLength;
    }

    public void setEhaseLength(double dlugoscFazy) {
        this.phaseLength = dlugoscFazy;
    }

    public double getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(double entranceTime) {
        this.entranceTime = entranceTime;
    }

    public double getremainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(double d) {
        this.remainingTime = d;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String toString() {
        return String.format("%30f%30f%30f%30f", phaseLength, entranceTime, remainingTime, waitingTime);
    }

    protected Object clone() throws CloneNotSupportedException {
        Process clone = null;
        try
        {
            clone = (Process) super.clone();

        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
        return clone;
    }

}
