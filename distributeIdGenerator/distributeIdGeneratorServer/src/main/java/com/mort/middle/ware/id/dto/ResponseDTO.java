package com.mort.middle.ware.id.dto;


import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1725358677670500422L;


    private AtomicLong start;

    private AtomicLong end;

    private int step;


    public AtomicLong getStart() {
        return start;
    }

    public void setStart(AtomicLong start) {
        this.start = start;
    }

    public AtomicLong getEnd() {
        return end;
    }

    public void setEnd(AtomicLong end) {
        this.end = end;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "start=" + start +
                ", end=" + end +
                ", step=" + step +
                '}';
    }
}
