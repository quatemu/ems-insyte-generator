package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;
import java.util.HashSet;

public abstract class Generator {
    protected LocalDateTime BeginDateTime;
    protected LocalDateTime CurrentDateTime;
    protected LocalDateTime EndDateTime;

    protected int Step = 1;
    protected String Delimiter = "\t";

    protected double MinValue = 0;
    protected double MaxValue = 1;

    protected HashSet<String> UsingVariables = new HashSet<>();

    public void setBeginDateTime(LocalDateTime beginDateTime) {
        BeginDateTime = beginDateTime;
        CurrentDateTime = BeginDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        EndDateTime = endDateTime;
    }

    public void setStep(int step) {
        Step = step;
    }

    public void setMinValue(double minValue) {
        MinValue = minValue;
    }

    public void setMaxValue(double maxValue) {
        MaxValue = maxValue;
    }

    protected void incDateTime(){
        CurrentDateTime = CurrentDateTime.plusSeconds(Step);
    }

    public abstract String getNext();
    //public abstract StringBuilder getAll();
}
